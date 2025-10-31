package ru.practicum.shareit.booking;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    public BookingDto create(@Valid @RequestBody BookingDto bookingDto) {
        log.info("Поступил запрос на добавление заказа {}", bookingDto);
        return service.create(bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto update(@PathVariable Long bookingId, @RequestBody BookingDto bookingDto) {
        log.info("Поступил запрос на обновление заказа по идентификационному номеру {}", bookingId);
        return service.update(bookingId, bookingDto);
    }

    @GetMapping
    public Collection<BookingDto> getAll() {
        log.info("Поступил запрос на предоставление всех заказов");
        return service.getAll();
    }

    @GetMapping("/{bookingId}")
    public BookingDto get(@PathVariable Long bookingId) {
        log.info("Поступил запрос на предоставление заказа по идентификационному номеру {}", bookingId);
        return service.get(bookingId);
    }

    @DeleteMapping("/{bookingId}")
    public BookingDto delete(@PathVariable Long bookingId) {
        log.info("Поступил запрос на удаление заказа по идентификационному номеру {}", bookingId);
        return service.delete(bookingId);
    }
}
