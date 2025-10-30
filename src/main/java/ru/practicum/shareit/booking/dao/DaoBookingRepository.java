package ru.practicum.shareit.booking.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.Collection;

@Repository
public interface DaoBookingRepository {
    BookingDto create(BookingDto bookingDto);

    BookingDto update(Long bookingId, BookingDto bookingDto);

    Collection<BookingDto> getAll();

    BookingDto get(Long bookingId);

    BookingDto delete(Long bookingId);
}
