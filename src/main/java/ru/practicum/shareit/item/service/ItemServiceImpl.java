package ru.practicum.shareit.item.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.practicum.shareit.booking.dao.DaoBookingRepository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dao.DaoCommentRepository;
import ru.practicum.shareit.item.dao.DaoItemRepository;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.CommentMapper;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.dao.DaoUserRepository;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final DaoItemRepository repository;
    private final DaoUserRepository userRepository;
    private final DaoCommentRepository commentRepository;
    private final DaoBookingRepository bookingRepository;

    @Autowired
    public ItemServiceImpl(DaoItemRepository repository,
                           DaoUserRepository userRepository,
                           DaoCommentRepository commentRepository,
                           DaoBookingRepository bookingRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public ItemDto create(ItemDto itemDto, Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Такого пользователя нет"));

        itemDto.setOwnerId(userId);

        return ItemMapper.dtoMapper(repository.save(ItemMapper.itemMapper(itemDto)));
    }

    @Override
    public CommentDto addComment(Long itemId, Comment comment, Long userId) {
        repository.findById(itemId).orElseThrow(() -> new NotFoundException("Такого инструмента нет"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Такого пользователя нет"));

        Booking booking = bookingRepository.getByUserAndItem(userId, itemId).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Такого заказа нет"));

        if (!booking.getStatus().equals(Status.APPROVED)) {
            throw new IllegalArgumentException("Пользователь не может оставить комментарий");
        }

        comment.setItem(itemId);
        comment.setAuthor(userId);

        commentRepository.save(comment);

        return CommentMapper.dtoMapper(commentRepository.findAll().getLast(), user);
    }

    @Override
    public ItemDto update(Long itemId, ItemDto itemDto, Long userId) {
        return ItemMapper.dtoMapper(repository.save(updateItem(itemId, itemDto, userId)));
    }

    @Override
    public Collection<ItemDto> getAll(Long userId) {
        return repository.getAll(userId).stream()
                .map(i -> getItemWithComments(ItemMapper.dtoMapper(i)))
                .collect(Collectors.toSet());
    }

    @Override
    public ItemDto get(Long itemId) {
        return getItemWithComments(ItemMapper
                .dtoMapper(repository.findById(itemId).orElseThrow(() -> new NotFoundException("Такого предмета нет"))));
    }

    @Override
    public Collection<ItemDto> search(String text) {
        if (text.isBlank()) {
            return Set.of();
        }

        return repository.search(text).stream()
                .map(ItemMapper::dtoMapper)
                .collect(Collectors.toSet());
    }

    @Override
    public ItemDto delete(Long itemId) {
        Optional<Item> item = repository.findById(itemId);

        if (item.isPresent()) {
            repository.delete(item.get());

            return ItemMapper.dtoMapper(item.get());
        }

        return null;
    }

    private Item updateItem(Long itemId, ItemDto itemDto, Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Такого пользователя нет"));
        Item item = repository.findById(itemId).orElseThrow(() -> new NotFoundException("Такого инструмента нет"));

        item.setName(itemDto.getName() == null ? item.getName() : itemDto.getName());
        item.setDescription(itemDto.getDescription() == null ? item.getDescription() : itemDto.getDescription());
        item.setAvailable(itemDto.getAvailable() == null ? item.getAvailable() : itemDto.getAvailable());

        return item;
    }

    private ItemDto getItemWithComments(ItemDto itemDto) {
        List<Booking> bookings = bookingRepository.getByItem(itemDto.getId()).stream().toList();

        itemDto.setComments(commentRepository.getCommentsByItemId(itemDto.getId()));

        return itemDto;
    }
}
