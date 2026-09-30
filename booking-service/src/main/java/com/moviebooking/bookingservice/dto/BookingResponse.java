package com.moviebooking.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Long id;
    private String customerName;
    private String customerEmail;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private List<BookingDetailResponse> details;
}
