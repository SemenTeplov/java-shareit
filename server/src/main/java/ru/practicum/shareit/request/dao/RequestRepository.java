package ru.practicum.shareit.request.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;

@Repository
public interface RequestRepository extends JpaRepository<ItemRequest, Long> {
    @Query(nativeQuery = true, value =
            "SELECT * " +
            "FROM requests " +
            "WHERE requests.user_id = :userId " +
            "ORDER BY requests.create_date")
    Collection<ItemRequest> getAllForUser(@Param("userId") Long userId);

    @Query(nativeQuery = true, value =
            "SELECT * " +
            "FROM requests " +
            "WHERE requests.user_id <> :userId " +
            "ORDER BY requests.create_date")
    Collection<ItemRequest> getAllForOtherUsers(@Param("userId") Long userId);
}
