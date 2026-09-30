package com.moviebooking.bookingservice.service.impl;

import com.moviebooking.bookingservice.dto.*;
import com.moviebooking.bookingservice.entity.Booking;
import com.moviebooking.bookingservice.entity.BookingDetail;
import com.moviebooking.bookingservice.repository.BookingRepository;
import com.moviebooking.bookingservice.service.BookingService;
import com.moviebooking.bookingservice.service.MovieIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final MovieIntegrationService movieIntegrationService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC_BOOKING_CREATED = "booking-created";

    @Override
    @Transactional
    public BookingResponse createBooking(CreateBookingRequest request) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<BookingDetail> detailEntities = new ArrayList<>();

        Booking booking = Booking.builder()
                .customerName(request.getCustomerName())
                .customerEmail(request.getCustomerEmail())
                .status("PENDING")
                .totalAmount(BigDecimal.ZERO)
                .build();

        for (BookingItemRequest item : request.getItems()) {
            MovieDto movie = movieIntegrationService.getMovie(item.getMovieId());

            BigDecimal ticketPrice = movie.getTicketPrice();
            BigDecimal lineTotal = ticketPrice.multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(lineTotal);

            BookingDetail detail = BookingDetail.builder()
                    .movieId(movie.getId())
                    .movieTitle(movie.getTitle())
                    .ticketPrice(ticketPrice)
                    .quantity(item.getQuantity())
                    .lineTotal(lineTotal)
                    .build();

            booking.addDetail(detail);
            detailEntities.add(detail);
        }

        booking.setTotalAmount(totalAmount);
        Booking savedBooking = bookingRepository.save(booking);

        kafkaTemplate.send(TOPIC_BOOKING_CREATED, request.getCustomerEmail());

        return mapToResponse(savedBooking);
    }

    private BookingResponse mapToResponse(Booking booking) {
        List<BookingDetailResponse> details = booking.getDetails().stream()
                .map(d -> BookingDetailResponse.builder()
                        .id(d.getId())
                        .movieId(d.getMovieId())
                        .movieTitle(d.getMovieTitle())
                        .ticketPrice(d.getTicketPrice())
                        .quantity(d.getQuantity())
                        .lineTotal(d.getLineTotal())
                        .build())
                .toList();

        return BookingResponse.builder()
                .id(booking.getId())
                .customerName(booking.getCustomerName())
                .customerEmail(booking.getCustomerEmail())
                .totalAmount(booking.getTotalAmount())
                .status(booking.getStatus())
                .createdAt(booking.getCreatedAt())
                .details(details)
                .build();
    }
}
