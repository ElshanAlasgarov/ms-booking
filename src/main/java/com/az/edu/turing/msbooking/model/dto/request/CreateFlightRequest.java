package com.az.edu.turing.msbooking.model.dto.request;

import com.az.edu.turing.msbooking.model.enums.City;
import com.az.edu.turing.msbooking.model.enums.FlightStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlightRequest {

    @NotBlank
    private String flightNumber;

    @NotNull
    @Future(message = "Departure date must be in the future")
    private LocalDateTime departureDateTime;

    @NotNull
    @Future(message = "Arrival date must be in the future")
    private LocalDateTime arrivalDateTime;

    @NotNull
    private FlightStatus flightStatus;

    @NotNull
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull
    @Min(value = 1, message = "There must be at least 1 available seat")
    private Integer availableSeats;

    @NotNull
    private City departureCity;

    @NotNull
    private City arrivalCity;

}
