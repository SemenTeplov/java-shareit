package ru.practicum.shareit.booking.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.practicum.shareit.booking.model.Booking;

import java.util.Collection;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query(nativeQuery = true, value =
            "SELECT id, item_id, booker_id, start, finish, status " +
            "FROM bookings " +
            "WHERE booker_id = :userId AND status = 'ALL'" +
            "ORDER BY start")
    Collection<Booking> findAllByBookerId(@Param("userId") Long userId);

    @Query(nativeQuery = true, value =
            "SELECT id, item_id, booker_id, start, finish, status " +
                    "FROM bookings " +
                    "WHERE booker_id = :userId AND status = :status" +
                    "ORDER BY start")
    Collection<Booking> findByBookerId(@Param("userId") Long userId,@Param("status") String status);

    @Query(nativeQuery = true, value =
            "SELECT bookings.id AS id, item_id, booker_id, start, finish, status " +
            "FROM bookings " +
            "JOIN items ON items.id = item_id" +
            "WHERE items.owner_id = :ownerId AND status = 'ALL'" +
            "ORDER BY start")
    Collection<Booking> getAllByOwner(@Param("ownerId") Long ownerId);

    @Query(nativeQuery = true, value =
            "SELECT bookings.id AS id, item_id, booker_id, start, finish, status " +
                    "FROM bookings " +
                    "JOIN items ON items.id = item_id" +
                    "WHERE items.owner_id = :ownerId AND status = :status" +
                    "ORDER BY start")
    Collection<Booking> getByOwner(@Param("ownerId") Long ownerId, @Param("status") String status);

    @Query(nativeQuery = true, value =
            "SELECT id, item_id, booker_id, start, finish, status " +
            "FROM bookings " +
            "WHERE item_id = :itemId " +
            "ORDER BY start")
    Collection<Booking> getByItem(@Param("itemId") Long itemId);

    @Query(nativeQuery = true, value =
            "SELECT id, item_id, booker_id, start, finish, status " +
            "FROM bookings " +
            "WHERE booker_id = :userId AND item_id = :itemId")
    Collection<Booking> getByUserAndItem(@Param("userId") Long userId, @Param("itemId") Long itemId);
}
