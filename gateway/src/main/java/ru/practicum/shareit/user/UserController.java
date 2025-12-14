package ru.practicum.shareit.user;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.practicum.shareit.user.dto.UserRequestDto;

@Validated
@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserClient client;

    @Autowired
    public UserController(UserClient client) {
        this.client = client;
    }

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
