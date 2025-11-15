package ru.practicum.shareit.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.practicum.shareit.booking.dao.DaoBookingRepository;
import ru.practicum.shareit.booking.dto.BookingDataDto;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.NotValidationException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.user.service.UserService;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final DaoBookingRepository repository;
    private final ItemService itemService;
    private final UserService userService;

    @Autowired
    public BookingServiceImpl(DaoBookingRepository repository, ItemService itemService, UserService userService) {
        this.repository = repository;
        this.itemService = itemService;
        this.userService = userService;
    }

    @Override
    public BookingDto create(BookingDataDto bookingDataDto, Long userId) {
        userService.get(userId);

        if (!itemService.get(bookingDataDto.getItemId()).getAvailable()) {
            throw new NotValidationException("Инструмент не доступен");
        }

        Booking booking = BookingMapper.bookingMapper(bookingDataDto);
        booking.setBookerId(userId);
        booking.setStatus(Status.WAITING);

        return BookingMapper.dtoMapper(repository.save(booking), userService.get(booking.getBookerId()), itemService.get(booking.getItemId()));
    }

    @Override
    public BookingDto update(Long bookingId, Long userId, Boolean approved) {
        Booking booking = repository.findById(bookingId).orElseThrow(() -> new NotFoundException("Заказ не найден"));
        ItemDto itemDto = itemService.get(booking.getItemId());

        try {
            userService.get(userId);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Ввод не верен");
        }

        if (itemDto.getOwnerId().equals(userId)) {
            if (approved != null && approved) {
                booking.setStatus(Status.APPROVED);
            } else {
                booking.setStatus(Status.REJECTED);
            }
        } else {
            throw new IllegalArgumentException("Изменение статуса запрещено");
        }

        return BookingMapper.dtoMapper(repository.save(booking), userService.get(booking.getBookerId()), itemService.get(booking.getItemId()));
    }

    @Override
    public BookingDto get(Long bookingId, Long userId, String state) {
        Booking booking = repository.findById(bookingId).orElseThrow(() -> new NotFoundException("Заказ не найден"));
        userService.get(userId);

        return BookingMapper.dtoMapper(booking, userService.get(booking.getBookerId()), itemService.get(booking.getItemId()));
    }

    @Override
    public Collection<BookingDto> getAll(String state, Long userId) {
        return repository.findAll().stream()
                .map(b -> BookingMapper.dtoMapper(b, userService.get(b.getBookerId()), itemService.get(b.getItemId())))
                .collect(Collectors.toSet());
    }

    public Collection<BookingDto> getByState(Long userId, String state) {
        return repository.getByState(userId).stream()
                .filter(b -> state.equals("ALL") || b.getStatus().equals(Status.valueOf(state)))
                .map(b -> BookingMapper.dtoMapper(b, userService.get(b.getBookerId()), itemService.get(b.getItemId())))
                .collect(Collectors.toSet());
    }

    public Collection<BookingDto> getByOwner(Long userId, String state) {
        return repository.getByOwner(userId).stream()
                .map(b -> BookingMapper.dtoMapper(b, userService.get(b.getBookerId()), itemService.get(b.getItemId())))
                .filter(b -> state.equals("ALL") || b.getStatus().equals(Status.valueOf(state)))
                .collect(Collectors.toSet());
    }

    @Override
    public BookingDto delete(Long bookingId) {
        Optional<Booking> booking = repository.findById(bookingId);

        if (booking.isPresent()) {
            repository.delete(booking.get());

            return BookingMapper.dtoMapper(booking.get(), userService.get(booking.get().getBookerId()), itemService.get(booking.get().getItemId()));
        }

        return null;
    }
}
