package ru.practicum.shareit.booking.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.booking.model.Booking;

import java.util.Collection;

@Repository
public interface DaoBookingRepository {
    Booking create(Booking booking);

    Booking update(Long bookingId, Booking originBooking);

    Collection<Booking> getAll();

    Booking get(Long bookingId);

    Booking delete(Long bookingId);
}
