package com.moviebooking.movieservice.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse implements Serializable {
    private Long id;
    private String title;
    private BigDecimal ticketPrice;
    private String description;
    private Integer durationInMinutes;
}
