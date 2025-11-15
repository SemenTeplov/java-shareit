package ru.practicum.shareit.booking;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.practicum.shareit.booking.dto.BookingDataDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    public BookingDto create(@Valid @RequestBody BookingDataDto bookingDataDto, @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на добавление заказа {} пользователем {}", bookingDataDto, userId);
        return service.create(bookingDataDto, userId);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto update(@PathVariable Long bookingId, @RequestHeader("X-Sharer-User-Id") Long userId, @RequestParam Boolean approved) {
        log.info("Поступил запрос на обновление заказа {} с пользователем {} и с одобрением {}", bookingId, userId, approved);
        return service.update(bookingId, userId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingDto get(@PathVariable Long bookingId,
                          @RequestHeader("X-Sharer-User-Id") Long userId,
                          @RequestParam(defaultValue = "ALL") String state) {
        log.info("Поступил запрос на предоставление заказа по идентификационному номеру {}", bookingId);
        return service.get(bookingId, userId, state);
    }

    @GetMapping()
    public Collection<BookingDto> getByState(@RequestHeader("X-Sharer-User-Id") Long userId, @RequestParam(defaultValue = "ALL") String state) {
        log.info("Поступил запрос на предоставление заказа по статусу {} и пользователем {}", state, userId);
        return service.getByState(userId, state);
    }

    @GetMapping("/owner")
    public Collection<BookingDto> getByOwner(@RequestHeader("X-Sharer-User-Id") Long userId, @RequestParam(defaultValue = "ALL") String state) {
        log.info("Поступил запрос на предоставление заказа по пользователю {}", userId);
        return service.getByOwner(userId, state);
    }

    @DeleteMapping("/{bookingId}")
    public BookingDto delete(@PathVariable Long bookingId) {
        log.info("Поступил запрос на удаление заказа по идентификационному номеру {}", bookingId);
        return service.delete(bookingId);
    }
}
