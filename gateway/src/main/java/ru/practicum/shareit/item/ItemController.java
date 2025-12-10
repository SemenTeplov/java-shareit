package ru.practicum.shareit.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import ru.practicum.shareit.item.dto.CommentRequestDto;
import ru.practicum.shareit.item.dto.ItemRequestDto;

@Validated
@Controller
@RequestMapping("/items")
public class ItemController {
    private static final Logger log = LoggerFactory.getLogger(ItemController.class);
    private final ItemClient client;

    @Autowired
    public ItemController(ItemClient client) {
        this.client = client;
    }

    @PostMapping
    @JsonIgnoreProperties({"requestId"})
    public ResponseEntity<Object> create(@Valid @RequestBody ItemRequestDto itemRequestDto,
                                         @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на добавление элемента {} с хозяином {}", itemRequestDto, userId);

        return client.create(itemRequestDto, userId);
    }

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> addComment(@PathVariable Long itemId,
                                             @RequestBody CommentRequestDto comment,
                                             @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на добавление комментария {}, для инструмента {} пользователем {}", comment, itemId, userId);

        return client.addComment(itemId, comment, userId);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> update(@PathVariable Long itemId,
                                         @RequestBody ItemRequestDto itemRequestDto,
                                         @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на обновление элемента по идентификационному номеру {}", itemId);

        return client.update(itemId, itemRequestDto, userId);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("Поступил запрос на предоставление всех элементов");

        return client.getAll(userId);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> get(@PathVariable Long itemId) {
        log.info("Поступил запрос на предоставление элемента по идентификационному номеру {}", itemId);

        return client.get(itemId);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestParam String text,
                                         @PathVariable Long itemId) {
        log.info("Поступил запрос на поиск элемента");

        return client.search(text, itemId);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Object> delete(@PathVariable Long itemId) {
        log.info("Поступил запрос на удаление элемента по идентификационному номеру {}", itemId);

        return client.delete(itemId);
    }
}
