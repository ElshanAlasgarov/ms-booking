package com.az.edu.turing.msbooking.domain.repository;

import com.az.edu.turing.msbooking.domain.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

}
