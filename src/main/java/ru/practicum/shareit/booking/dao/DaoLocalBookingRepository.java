package ru.practicum.shareit.booking.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class DaoLocalBookingRepository implements DaoBookingRepository {
    private final List<Booking> list = new ArrayList<>();
    private Long id = 0L;

    @Override
    public Booking create(Booking booking) {
        booking.setId(++id);
        list.add(booking);

        return list.getLast();
    }

    @Override
    public Booking update(Long bookingId, Booking originBooking) {
        for (Booking booking : list) {
            if (booking.getId().equals(bookingId)) {
                booking.setFeedback(originBooking.getFeedback() != null ? originBooking.getFeedback() : booking.getFeedback());
                booking.setDate(originBooking.getDate() != null ? originBooking.getDate() : booking.getDate());
                booking.setIsBooking(originBooking.getIsBooking() != null ? originBooking.getIsBooking() : booking.getIsBooking());

                return booking;
            }
        }

        throw new NotFoundException("Заказ не найден");
    }

    @Override
    public Collection<Booking> getAll() {
        return list;
    }

    @Override
    public Booking get(Long bookingId) {
        return list.stream()
                .filter(b -> b.getId().equals(bookingId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Заказ не найден"));
    }

    @Override
    public Booking delete(Long bookingId) {
        Booking booking = get(bookingId);
        list.remove(booking);

        return booking;
    }
}
