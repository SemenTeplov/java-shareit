package ru.practicum.shareit.request;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.practicum.shareit.request.dto.RequestItemRequestDto;

@Validated
@Controller
@RequestMapping("/requests")
public class RequestController {
    private static final Logger log = LoggerFactory.getLogger(RequestController.class);
    private final RequestClient client;

    @Autowired
    public RequestController(RequestClient client) {
        this.client = client;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody RequestItemRequestDto request,
                                         @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на добавление запроса {}", request);

        return client.create(request, userId);
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
