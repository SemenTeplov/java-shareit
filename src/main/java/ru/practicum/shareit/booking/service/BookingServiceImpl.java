package ru.practicum.shareit.booking.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.booking.dao.DaoBookingRepository;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.Collection;

@Service
public class BookingServiceImpl implements BookingService {
    private final DaoBookingRepository repository;

    public BookingServiceImpl(DaoBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public BookingDto create(BookingDto bookingDto) {
        return repository.create(bookingDto);
    }

    @Override
    public BookingDto update(Long bookingId, BookingDto bookingDto) {
        return repository.update(bookingId, bookingDto);
    }

    @Override
    public Collection<BookingDto> getAll() {
        return repository.getAll();
    }

    @Override
    public BookingDto get(Long bookingId) {
        return repository.get(bookingId);
    }

    @Override
    public BookingDto delete(Long bookingId) {
        return repository.delete(bookingId);
    }
}
