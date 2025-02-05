package com.az.edu.turing.msbooking.domain.entity;

import com.az.edu.turing.msbooking.model.enums.BookingStatus;
import com.az.edu.turing.msbooking.model.enums.PaymentStatus;
import com.az.edu.turing.msbooking.model.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings")
public class BookingEntity extends BaseEntity {

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus bookingStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "flight_id", nullable = false)
//    private FlightEntity flight;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false)
//    private UserEntity user;
}
