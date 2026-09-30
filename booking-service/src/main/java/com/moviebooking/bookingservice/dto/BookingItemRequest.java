package com.moviebooking.bookingservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingItemRequest {
    @NotNull(message = "Mã phim không được để trống")
    private Long movieId;

    @NotNull(message = "Số lượng vé không được để trống")
    @Min(value = 1, message = "Số lượng vé phải lớn hơn hoặc bằng 1")
    private Integer quantity;
}
