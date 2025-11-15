package ru.practicum.shareit.request;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.service.RequestService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping(path = "/requests")
public class ItemRequestController {
    private final RequestService service;

    @Autowired
    public ItemRequestController(RequestService service) {
        this.service = service;
    }

    @PostMapping
    public ItemRequestDto create(@Valid @RequestBody ItemRequestDto itemRequestDto) {
        log.info("Поступил запрос на добавление запроса {}", itemRequestDto);
        return service.create(itemRequestDto);
    }

    @PatchMapping("/{itemId}")
    public ItemRequestDto update(@PathVariable Long itemId, @RequestBody ItemRequestDto itemRequestDto) {
        log.info("Поступил запрос на обновление запроса по идентификационному номеру {}", itemId);
        return service.update(itemId, itemRequestDto);
    }

    @GetMapping
    public Collection<ItemRequestDto> getAll() {
        log.info("Поступил запрос на предоставление всех запросов");
        return service.getAll();
    }

    @GetMapping("/{itemId}")
    public ItemRequestDto get(@PathVariable Long itemId) {
        log.info("Поступил запрос на предоставление запроса по идентификационному номеру {}", itemId);
        return service.get(itemId);
    }

    @DeleteMapping("/{itemId}")
    public ItemRequestDto delete(@PathVariable Long itemId) {
        log.info("Поступил запрос на удаление запроса по идентификационному номеру {}", itemId);
        return service.delete(itemId);
    }
}
