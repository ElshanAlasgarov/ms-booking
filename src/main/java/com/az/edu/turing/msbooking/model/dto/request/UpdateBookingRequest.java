package com.az.edu.turing.msbooking.model.dto.request;

import com.az.edu.turing.msbooking.model.enums.BookingStatus;
import com.az.edu.turing.msbooking.model.enums.PaymentStatus;
import com.az.edu.turing.msbooking.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingRequest {

    private LocalDateTime bookingDate;
    private String seatNumber;
    private BookingStatus bookingStatus;
    private PaymentStatus paymentStatus;
    private RoomType roomType;

}
