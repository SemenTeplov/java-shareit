package ru.practicum.shareit.booking.mapper;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;

public class BookingMapper {
    public BookingDto dtoMapper(Booking booking) {
        BookingDto bookingDto = new BookingDto();

        bookingDto.setId(booking.getId());
        bookingDto.setDate(booking.getDate());
        bookingDto.setIsBooking(booking.getIsBooking());
        bookingDto.setFeedback(booking.getFeedback());

        return bookingDto;
    }

    public Booking bookingMapper(BookingDto bookingDto) {
        Booking booking = new Booking();

        booking.setId(bookingDto.getId());
        booking.setDate(bookingDto.getDate());
        booking.setIsBooking(bookingDto.getIsBooking());
        booking.setFeedback(bookingDto.getFeedback());

        return booking;
    }
}
