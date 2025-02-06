package com.az.edu.turing.msbooking.service;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import com.az.edu.turing.msbooking.domain.entity.FlightEntity;
import com.az.edu.turing.msbooking.domain.entity.UserEntity;
import com.az.edu.turing.msbooking.domain.repository.BookingRepository;
import com.az.edu.turing.msbooking.domain.repository.FlightRepository;
import com.az.edu.turing.msbooking.domain.repository.UserRepository;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.exception.UnauthorizedException;
import com.az.edu.turing.msbooking.mapper.BookingMapper;
import com.az.edu.turing.msbooking.model.dto.request.CreateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;
import com.az.edu.turing.msbooking.model.enums.BookingStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.az.edu.turing.msbooking.model.enums.Role.ADMIN;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    public BookingDto createBooking(CreateBookingRequest createBookingRequest, String role) {
        checkIfAdmin(role);
        FlightEntity flight = flightRepository.findById(createBookingRequest.getFlightId())
                .orElseThrow(() -> new NotFoundException("Flight not found"));
        UserEntity user = userRepository.findById(createBookingRequest.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        return bookingMapper.toBookingDto(bookingRepository
                .save(bookingMapper.toBookingEntity(createBookingRequest,flight,user)));
    }

    public List<BookingDto> getAllBookings(String role) {
        checkIfAdmin(role);
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toBookingDto).collect(Collectors.toList());
    }

    public BookingDto getBookingById(Long bookingId) {
        BookingEntity bookingById = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking with specified id not found"));
        return bookingMapper.toBookingDto(bookingById);
    }

    public BookingDto updateBooking(Long id, UpdateBookingRequest updateBookingRequest, String role) {
        checkIfAdmin(role);
        if (!bookingRepository.existsById(id)) {
            throw new NotFoundException("Booking with id " + id + " not found");
        }

        return bookingMapper.toBookingDto(bookingRepository.save(bookingMapper
                .toBookingEntity(updateBookingRequest)));

    }

    public void deleteBookingById(Long id, String role) {
        checkIfAdmin(role);
        BookingEntity bookingById = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with specified id not found"));
        bookingById.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(bookingById);

    }

    private void checkIfAdmin(String role) {
        if (!ADMIN.name().equalsIgnoreCase(role)) {
            log.error("Unauthorized operation: User is not admin.");
            throw new UnauthorizedException("This operation can only be performed by administrators.");
        }
    }
}
