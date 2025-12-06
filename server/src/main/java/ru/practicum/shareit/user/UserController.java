package ru.practicum.shareit.user;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        log.info("Поступил запрос на добавление пользователя {}", userDto);

        return service.create(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDto update(@PathVariable Long userId, @RequestBody UserDto userDto) {
        log.info("Поступил запрос на обновление пользователя по идентификационному номеру {} {}", userId, userDto);

        return service.update(userId, userDto);
    }

    @GetMapping
    public Collection<UserDto> getAll() {
        log.info("Поступил запрос на предоставление всех пользователей");

        return service.getAll();
    }

    @GetMapping("/{userId}")
    public UserDto get(@PathVariable Long userId) {
        log.info("Поступил запрос на предоставление пользователя по идентификационному номеру {}", userId);

        return service.get(userId);
    }

    @DeleteMapping("/{userId}")
    public UserDto delete(@PathVariable Long userId) {
        log.info("Поступил запрос на удаление пользователя по идентификационному номеру {}", userId);

        return service.delete(userId);
    }
}
