package ru.practicum.shareit.user;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.practicum.shareit.user.dto.UserRequestDto;

@Slf4j
@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserClient client;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UserRequestDto userDto) {
        log.info("Поступил запрос на добавление пользователя {}", userDto);

        return client.create(userDto);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> update(@PathVariable Long userId, @RequestBody UserRequestDto userDto) {
        log.info("Поступил запрос на обновление пользователя по идентификационному номеру {} {}", userId, userDto);

        return client.update(userId, userDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        log.info("Поступил запрос на предоставление всех пользователей");

        return client.getAll();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> get(@PathVariable Long userId) {
        log.info("Поступил запрос на предоставление пользователя по идентификационному номеру {}", userId);

        return client.get(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> delete(@PathVariable Long userId) {
        log.info("Поступил запрос на удаление пользователя по идентификационному номеру {}", userId);

        return client.delete(userId);
    }
}
