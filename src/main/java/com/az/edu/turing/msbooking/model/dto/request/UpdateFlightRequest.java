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
public class UpdateFlightRequest {

    private String flightNumber;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private FlightStatus flightStatus;
    private BigDecimal price;
    private Integer availableSeats;
    private City departureCity;
    private City arrivalCity;

}
