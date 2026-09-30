package com.moviebooking.bookingservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookingRequest {

    @NotBlank(message = "Tên khách hàng không được để trống")
    private String customerName;

    @NotBlank(message = "Email khách hàng không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String customerEmail;

    @NotEmpty(message = "Danh sách vé không được để trống")
    @Valid
    private List<BookingItemRequest> items;
}
