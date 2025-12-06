package ru.practicum.shareit.booking.mapper;

import ru.practicum.shareit.booking.dto.BookingDataDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;

public class BookingMapper {
    public static BookingDto dtoMapper(Booking booking, UserDto user, ItemDto item) {
        BookingDto bookingDto = new BookingDto();

        bookingDto.setId(booking.getId());
        bookingDto.setStart(booking.getStart());
        bookingDto.setEnd(booking.getEnd());
        bookingDto.setItem(item);
        bookingDto.setBooker(user);
        bookingDto.setStatus(booking.getStatus());

        return bookingDto;
    }

    public static Booking bookingMapper(BookingDataDto bookingDataDto) {
        Booking booking = new Booking();

        booking.setStart(bookingDataDto.getStart());
        booking.setEnd(bookingDataDto.getEnd());
        booking.setItemId(bookingDataDto.getItemId());
        booking.setStatus(bookingDataDto.getStatus());

        return booking;
    }
}
