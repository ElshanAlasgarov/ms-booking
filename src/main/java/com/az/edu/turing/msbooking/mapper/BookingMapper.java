package com.az.edu.turing.msbooking.mapper;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateBookingRequest;
import com.az.edu.turing.msbooking.model.dto.response.BookingDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingEntity toBookingEntity(CreateBookingRequest createBookingRequest);

    BookingDto toBookingDto(BookingEntity bookingEntity);

    BookingEntity toBookingEntity(UpdateBookingRequest updateBookingRequest);

}
