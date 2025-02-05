package com.az.edu.turing.msbooking.domain.entity;

import com.az.edu.turing.msbooking.model.enums.City;
import com.az.edu.turing.msbooking.model.enums.FlightStatus;
import com.az.edu.turing.msbooking.model.enums.Role;
import com.az.edu.turing.msbooking.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class FlightEntity extends BaseEntity {

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Column(name = "departure_date_time", nullable = false)
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_date_time", nullable = false)
    private LocalDateTime arrivalDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private FlightStatus flightStatus;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Enumerated(EnumType.STRING)
    @Column(name = "departure_city", nullable = false)
    private City departureCity;

    @Enumerated(EnumType.STRING)
    @Column(name = "arrival_city", nullable = false)
    private City arrivalCity;

}
