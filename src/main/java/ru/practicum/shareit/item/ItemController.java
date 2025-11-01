package ru.practicum.shareit.item;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ItemDto create(@Valid @RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на добавление элемента {}", itemDto);
        return itemService.create(itemDto, userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@PathVariable Long itemId, @RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на обновление элемента по идентификационному номеру {}", itemId);
        return itemService.update(itemId, itemDto, userId);
    }

    @GetMapping
    public Collection<ItemDto> getAll(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на предоставление всех элементов");
        return itemService.getAll(userId);
    }

    @GetMapping("/{itemId}")
    public ItemDto get(@PathVariable Long itemId) {
        log.info("Поступил запрос на предоставление элемента по идентификационному номеру {}", itemId);
        return itemService.get(itemId);
    }

    @GetMapping("/search")
    public Collection<ItemDto> search(@RequestParam String text) {
        log.info("Поступил запрос на поиск элемента");
        return itemService.search(text);
    }

    @DeleteMapping("/{itemId}")
    public ItemDto delete(@PathVariable Long itemId) {
        log.info("Поступил запрос на удаление элемента по идентификационному номеру {}", itemId);
        return itemService.delete(itemId);
    }
}
