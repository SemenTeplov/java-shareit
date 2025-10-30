package ru.practicum.shareit.booking.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.Collection;

@Service
public interface BookingService {
    BookingDto create(BookingDto bookingDto);

    BookingDto update(Long bookingId, BookingDto bookingDto);

    Collection<BookingDto> getAll();

    BookingDto get(Long bookingId);

    BookingDto delete(Long bookingId);
}
