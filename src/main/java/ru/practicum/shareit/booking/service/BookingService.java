package ru.practicum.shareit.booking.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.booking.dto.BookingDataDto;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.Collection;

@Service
public interface BookingService {
    BookingDto create(BookingDataDto bookingDataDto, Long userId);

    BookingDto update(Long bookingId, Long userId, Boolean approved);

    BookingDto get(Long bookingId, Long userId, String state);

    Collection<BookingDto> getAll(String state, Long userId);

    Collection<BookingDto> getByState(Long userId, String state);

    Collection<BookingDto> getByOwner(Long ownerId, String state);

    BookingDto delete(Long bookingId);
}
