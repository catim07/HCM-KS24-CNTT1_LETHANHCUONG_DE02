package com.moviebooking.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDetailResponse {
    private Long id;
    private Long movieId;
    private String movieTitle;
    private BigDecimal ticketPrice;
    private Integer quantity;
    private BigDecimal lineTotal;
}
