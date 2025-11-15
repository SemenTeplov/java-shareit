package ru.practicum.shareit.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.practicum.shareit.item.model.Item;

import java.util.Collection;

@Repository
public interface DaoItemRepository extends JpaRepository<Item, Long> {
    @Query(nativeQuery = true, value =
            "SELECT id, owner_id, name, description, available, comment_id " +
            "FROM items " +
            "WHERE owner_id = :userId")
    Collection<Item>  getAll(@Param("userId") Long userId);

    @Query(nativeQuery = true, value =
            "SELECT id, owner_id, name, description, available, comment_id " +
            "FROM items " +
            "WHERE (description ILIKE %:text% OR name ILIKE %:text%) AND available")
    Collection<Item> search(@Param("text") String text);
}
