package ru.practicum.shareit.request.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.practicum.shareit.request.model.ItemRequest;

import java.util.Collection;

@Repository
public interface RequestRepository extends JpaRepository<ItemRequest, Long> {
    @Query(nativeQuery = true, value =
            "SELECT * requests.id, requests.request, requests.create_date, bookings.item_id" +
            "FROM requests " +
            "JOIN bookings ON bookings.item_id = requests.item_id " +
            "JOIN users ON users.id = bookings.booker_id" +
            "WHERE users.id = :userId " +
            "ORDER BY requests.create_date")
    Collection<ItemRequest> getAllForUser(Long userId);

    @Query(nativeQuery = true, value =
            "SELECT * requests.id, requests.request, requests.create_date, bookings.item_id" +
            "FROM requests " +
            "JOIN bookings ON bookings.item_id = requests.item_id " +
            "JOIN users ON users.id = bookings.booker_id" +
            "WHERE users.id <> :userId " +
            "ORDER BY requests.create_date")
    Collection<ItemRequest> getAllForOtherUsers(Long userId);
}
