package com.az.edu.turing.msbooking.service;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import com.az.edu.turing.msbooking.domain.repository.BookingRepository;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.mapper.BookingMapper;
import com.az.edu.turing.msbooking.model.dto.request.CreateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;
import com.az.edu.turing.msbooking.model.enums.BookingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingDto createBooking(CreateBookingRequest createBookingRequest) {
        BookingEntity bookingEntity = bookingRepository.save(bookingMapper.toBookingEntity(createBookingRequest));
        return bookingMapper.toBookingDto(bookingEntity);
    }

    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toBookingDto).collect(Collectors.toList());
    }

    public BookingDto getBookingById(Long bookingId) {
        BookingEntity bookingById = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Booking with id " + bookingId + " not found"));
        return bookingMapper.toBookingDto(bookingById);
    }

    public BookingDto updateBooking(Long id, UpdateBookingRequest updateBookingRequest) {
        if (!bookingRepository.existsById(id)) {
            throw new NotFoundException("Booking with id " + id + " not found");
        }

        return bookingMapper.toBookingDto(bookingRepository.save(bookingMapper
                .toBookingEntity(updateBookingRequest)));
    }

    public void deleteBookingById(Long id) {
        BookingEntity bookingById = bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking with id " + id + " not found"));
        bookingById.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(bookingById);

    }
}
