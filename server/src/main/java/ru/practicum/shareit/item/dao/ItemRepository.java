package ru.practicum.shareit.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(nativeQuery = true, value =
            "SELECT * " +
            "FROM items " +
            "WHERE owner_id = :userId")
    Collection<Item>  getAll(@Param("userId") Long userId);

    @Query(nativeQuery = true, value =
            "SELECT * " +
            "FROM items " +
            "WHERE (description ILIKE %:text% OR name ILIKE %:text%) AND available")
    Collection<Item> search(@Param("text") String text);

    @Query(nativeQuery = true, value =
            "SELECT * " +
            "FROM items " +
            "WHERE items.request_id = :id")
    Collection<Item> searchByRequestId(@Param("id") Long id);
}
