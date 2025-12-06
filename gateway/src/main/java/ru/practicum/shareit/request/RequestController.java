package ru.practicum.shareit.request;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.RequestItemRequestDto;

@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("/requests")
public class RequestController {
    private final RequestClient client;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody RequestItemRequestDto itemRequestDto,
                                         @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на добавление запроса {}", itemRequestDto);

        return client.create(itemRequestDto, userId);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> update(@PathVariable Long itemId,
                                         @RequestBody RequestItemRequestDto itemRequestDto) {
        log.info("Поступил запрос на обновление запроса по идентификационному номеру {}", itemId);

        return client.update(itemId, itemRequestDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllForUser(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на предоставление всех запросов");

        return client.getAllForUser(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllForOtherUsers(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на предоставление всех запросов для пользователя {}", userId);

        return client.getAllForOtherUsers(userId);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> get(@PathVariable Long itemId) {
        log.info("Поступил запрос на предоставление запроса по идентификационному номеру {}", itemId);

        return client.get(itemId);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Object> delete(@PathVariable Long itemId) {
        log.info("Поступил запрос на удаление запроса по идентификационному номеру {}", itemId);

        return client.delete(itemId);
    }
}
