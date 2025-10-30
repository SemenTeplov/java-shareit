package ru.practicum.shareit.user.dao;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DaoLocalUserRepository implements DaoUserRepository {
    private final UserMapper mapper = new UserMapper();
    private final List<User> list = new ArrayList<>();
    private Long id = 0L;

    @Override
    public UserDto create(UserDto userDto) {
        if (isDoubleEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Такая электронная почта уже есть ");
        }

        User user = mapper.userMapper(userDto);
        user.setId(++id);

        list.add(user);

        return mapper.dtoMapper(list.getLast());
    }

    @Override
    public UserDto update(Long userId, UserDto userDto) {
        if (isDoubleEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Такая электронная почта уже есть ");
        }

        for (User user : list) {
            if (user.getId().equals(userId)) {
                user.setName(userDto.getName() != null ? userDto.getName() : user.getName());
                user.setEmail(userDto.getEmail() != null ? userDto.getEmail() : user.getEmail());

                return mapper.dtoMapper(user);
            }
        }

        throw new NotFoundException("Пользователь не найден");
    }

    @Override
    public Collection<UserDto> getAll() {
        return list.stream().map(mapper::dtoMapper).collect(Collectors.toSet());
    }

    @Override
    public UserDto get(Long userId) {
        return list.stream()
                .filter(u -> u.getId().equals(userId))
                .map(mapper::dtoMapper)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }

    @Override
    public UserDto delete(Long userId) {
        UserDto userDto = get(userId);
        list.remove(mapper.userMapper(userDto));

        return userDto;
    }

    private boolean isDoubleEmail(String email) {
        return list.stream().anyMatch(u -> u.getEmail().equals(email));
    }
}
