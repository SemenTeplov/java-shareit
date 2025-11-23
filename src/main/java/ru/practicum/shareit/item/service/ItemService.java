package ru.practicum.shareit.item.service;

import org.springframework.stereotype.Service;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Comment;

import java.util.Collection;

@Service
public interface ItemService {
    ItemDto create(ItemDto itemDto, Long userId);

    CommentDto addComment(Long itemId, Comment comment, Long userId);

    ItemDto update(Long itemId, ItemDto itemDto, Long userId);

    Collection<ItemDto> getAll(Long userId);

    ItemDto get(Long itemId);

    Collection<ItemDto> search(String text);

    ItemDto delete(Long itemId);
}
