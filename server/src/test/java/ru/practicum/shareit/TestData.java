package java.ru.practicum.shareit;

import ru.practicum.shareit.booking.dto.BookingDataDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

public class TestData {
    public static BookingDataDto getBookingDataDto() {
        BookingDataDto bookingDto = new BookingDataDto();

        bookingDto.setItemId(1L);
        bookingDto.setStatus(Status.APPROVED);
        bookingDto.setStart(LocalDateTime.now());
        bookingDto.setEnd(LocalDateTime.now().plusDays(1));

        return bookingDto;
    }

    public static Item getItem() {
        Item item = new Item();

        item.setId(1L);
        item.setName("Some item");
        item.setDescription("Test some text");
        item.setOwnerId(1L);
        item.setAvailable(true);

        return item;
    }

    public static ItemDto getItemDto() {
        ItemDto item = new ItemDto();

        item.setId(1L);
        item.setName("Some item");
        item.setDescription("Test some text");
        item.setOwnerId(1L);
        item.setAvailable(true);

        return item;
    }

    public static User getUser() {
        User user = new User();

        user.setId(1L);
        user.setName("Some name");
        user.setEmail("email@mail.com");

        return user;
    }

    public static UserDto getUserDto() {
        UserDto user = new UserDto();

        user.setId(1L);
        user.setName("Some name");
        user.setEmail("email@mail.com");

        return user;
    }

    public static Booking getBookingWithoutId() {
        Booking booking = new Booking();

        booking.setBookerId(1L);
        booking.setItemId(1L);
        booking.setStatus(Status.APPROVED);
        booking.setStart(LocalDateTime.now());
        booking.setEnd(LocalDateTime.now().plusDays(1));

        return booking;
    }
}
