package com.az.edu.turing.msbooking.model.dto.request;

import com.az.edu.turing.msbooking.model.enums.BookingStatus;
import com.az.edu.turing.msbooking.model.enums.PaymentStatus;
import com.az.edu.turing.msbooking.model.enums.RoomType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookingRequest {

    @NotNull(message = "Booking date cannot be null")
    @PastOrPresent(message = "Booking date must be in the past or present")
    private LocalDateTime bookingDate;

    @NotNull(message = "Seat number cannot be null")
    @Size(min = 1, max = 10, message = "Seat number must be between 1 and 10 characters")
    private String seatNumber;

    @NotNull(message = "Booking status cannot be null")
    private BookingStatus bookingStatus;

    @NotNull(message = "Payment status cannot be null")
    private PaymentStatus paymentStatus;

    @NotNull(message = "Room type cannot be null")
    private RoomType roomType;

}
