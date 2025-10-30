package ru.practicum.shareit.booking.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DaoLocalBookingRepository implements DaoBookingRepository {
    private final BookingMapper mapper = new BookingMapper();
    private final List<Booking> list = new ArrayList<>();
    private Long id = 0L;

    @Override
    public BookingDto create(BookingDto bookingDto) {
        Booking booking = mapper.bookingMapper(bookingDto);

        booking.setId(++id);
        list.add(booking);

        return mapper.dtoMapper(list.getLast());
    }

    @Override
    public BookingDto update(Long bookingId, BookingDto bookingDto) {
        for (Booking booking : list) {
            if (booking.getId().equals(bookingId)) {
                booking.setFeedback(bookingDto.getFeedback() != null ? bookingDto.getFeedback() : booking.getFeedback());
                booking.setDate(bookingDto.getDate() != null ? bookingDto.getDate() : booking.getDate());
                booking.setIsBooking(bookingDto.getIsBooking() != null ? bookingDto.getIsBooking() : booking.getIsBooking());

                return mapper.dtoMapper(booking);
            }
        }

        throw new NotFoundException("Заказ не найден");
    }

    @Override
    public Collection<BookingDto> getAll() {
        return list.stream().map(mapper::dtoMapper).collect(Collectors.toSet());
    }

    @Override
    public BookingDto get(Long bookingId) {
        return list.stream()
                .filter(b -> b.getId().equals(bookingId))
                .map(mapper::dtoMapper)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Заказ не найден"));
    }

    @Override
    public BookingDto delete(Long bookingId) {
        BookingDto bookingDto = get(bookingId);
        list.remove(mapper.bookingMapper(bookingDto));

        return bookingDto;
    }
}
