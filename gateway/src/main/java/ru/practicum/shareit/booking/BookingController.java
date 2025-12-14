package ru.practicum.shareit.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;

import ru.practicum.shareit.booking.dto.BookItemRequestDto;

@Controller
@RequestMapping(path = "/bookings")
@Validated
public class BookingController {
	private static final Logger log = LoggerFactory.getLogger(BookingController.class);
	private final BookingClient bookingClient;

	@Autowired
	public BookingController(BookingClient bookingClient) {
		this.bookingClient = bookingClient;
	}

	@PostMapping
	public ResponseEntity<Object> bookItem(@RequestHeader("X-Sharer-User-Id") long userId,
										   @RequestBody @Valid BookItemRequestDto requestDto) {
		log.info("Creating booking {}, userId={}", requestDto, userId);

		return bookingClient.bookItem(userId, requestDto);
	}

	@PatchMapping("/{bookingId}")
	public ResponseEntity<Object> update(@PathVariable Long bookingId,
										 @RequestHeader("X-Sharer-User-Id") Long userId,
										 @RequestParam(required = false, name = "approved") String approved) {
		log.info("Поступил запрос на обновление заказа {} с пользователем {} и с одобрением {}", bookingId, userId, approved);

		return bookingClient.updateBooking(bookingId, userId, approved);
	}

	@GetMapping("/{bookingId}")
	public ResponseEntity<Object> getBooking(@RequestHeader("X-Sharer-User-Id") long userId,
											 @PathVariable Long bookingId,
											 @RequestParam(defaultValue = "ALL") String state) {
		log.info("Get booking {}, userId={}", bookingId, userId);

		return bookingClient.getBooking(userId, bookingId, state);
	}

	@GetMapping()
	public ResponseEntity<Object> getByState(@RequestHeader("X-Sharer-User-Id") Long userId,
											 @RequestParam(defaultValue = "ALL") String state) {
		log.info("Поступил запрос на предоставление заказа по статусу {} и пользователем {}", state, userId);

		return bookingClient.getByState(userId, state);
	}

	@GetMapping("/owner")
	public ResponseEntity<Object> getByOwner(@RequestHeader("X-Sharer-User-Id") Long userId,
											 @RequestParam(defaultValue = "ALL") String state) {
		log.info("Поступил запрос на предоставление заказа по пользователю {}", userId);

		return bookingClient.getByOwner(userId, state);
	}

	@DeleteMapping("/{bookingId}")
	public ResponseEntity<Object> delete(@PathVariable Long bookingId) {
		log.info("Поступил запрос на удаление заказа по идентификационному номеру {}", bookingId);

		return bookingClient.delete(bookingId);
	}
}
