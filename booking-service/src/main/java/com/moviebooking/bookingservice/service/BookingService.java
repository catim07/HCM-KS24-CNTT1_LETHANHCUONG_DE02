package com.moviebooking.bookingservice.service;

import com.moviebooking.bookingservice.dto.BookingResponse;
import com.moviebooking.bookingservice.dto.CreateBookingRequest;

public interface BookingService {
    BookingResponse createBooking(CreateBookingRequest request);
}
