package ru.practicum.shareit.booking.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.booking.dao.DaoBookingRepository;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final DaoBookingRepository repository;

    public BookingServiceImpl(DaoBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public BookingDto create(BookingDto bookingDto) {
        return BookingMapper.dtoMapper(repository.create(BookingMapper.bookingMapper(bookingDto)));
    }

    @Override
    public BookingDto update(Long bookingId, BookingDto bookingDto) {
        return BookingMapper.dtoMapper(repository.update(bookingId, BookingMapper.bookingMapper(bookingDto)));
    }

    @Override
    public Collection<BookingDto> getAll() {
        return repository.getAll().stream().map(BookingMapper::dtoMapper).collect(Collectors.toSet());
    }

    @Override
    public BookingDto get(Long bookingId) {
        return BookingMapper.dtoMapper(repository.get(bookingId));
    }

    @Override
    public BookingDto delete(Long bookingId) {
        return BookingMapper.dtoMapper(repository.delete(bookingId));
    }
}
