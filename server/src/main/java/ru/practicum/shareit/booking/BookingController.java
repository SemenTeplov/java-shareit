package ru.practicum.shareit.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.practicum.shareit.booking.dto.BookingDataDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/bookings")
public class BookingController {
    private static final Logger log = LoggerFactory.getLogger(BookingController.class);
    private final BookingService service;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    public BookingDto create(@RequestBody BookingDataDto bookingDataDto,
                             @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на добавление заказа {} пользователем {}", bookingDataDto, userId);

        return service.create(bookingDataDto, userId);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto update(@PathVariable Long bookingId,
                             @RequestHeader("X-Sharer-User-Id") Long userId,
                             @RequestParam Boolean approved) {
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
    public Collection<BookingDto> getByState(@RequestHeader("X-Sharer-User-Id") Long userId,
                                             @RequestParam(defaultValue = "ALL") String state) {
        log.info("Поступил запрос на предоставление заказа по статусу {} и пользователем {}", state, userId);
        return service.findByBookerId(userId, state);
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
