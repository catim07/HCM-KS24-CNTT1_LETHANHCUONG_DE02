package com.moviebooking.bookingservice.repository;

import com.moviebooking.bookingservice.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long> {
}
