package ru.practicum.shareit.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dao.DaoUserRepository;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final DaoUserRepository repository;

    @Autowired
    public UserServiceImpl(DaoUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDto create(UserDto userDto) {
        if (isDoubleEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Такая электронная почта уже есть ");
        }

        return UserMapper.dtoMapper(repository.save(UserMapper.userMapper(userDto)));
    }

    @Override
    public UserDto update(Long userId, UserDto userDto) {
        if (isDoubleEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Такая электронная почта уже есть ");
        }

        return UserMapper.dtoMapper(repository.save(updateUser(userDto, userId)));
    }

    @Override
    public Collection<UserDto> getAll() {
        return repository.findAll().stream().map(UserMapper::dtoMapper).collect(Collectors.toSet());
    }

    @Override
    public UserDto get(Long userId) {
        return UserMapper.dtoMapper(repository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден")));
    }

    @Override
    public UserDto delete(Long userId) {
        Optional<User> user = repository.findById(userId);

        if (user.isPresent()) {
            repository.delete(user.get());

            return UserMapper.dtoMapper(user.get());
        }

        return null;
    }

    private boolean isDoubleEmail(String email) {
        return repository.findAll().stream().anyMatch(u -> u.getEmail().equals(email));
    }

    private User updateUser(UserDto userDto, Long userId) {
        User user = repository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));

        user.setName(userDto.getName() == null ? user.getName() : userDto.getName());
        user.setEmail(userDto.getEmail() == null ? user.getEmail() : userDto.getEmail());

        return user;
    }
}
