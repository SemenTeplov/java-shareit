package ru.practicum.shareit.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.practicum.shareit.item.model.Comment;

import java.util.List;

@Repository
public interface DaoCommentRepository extends JpaRepository<Comment, Long> {
    @Query(nativeQuery = true, value =
        "SELECT * " +
        "FROM comments " +
        "WHERE item = :itemId")
    List<Comment> getCommentsByItemId(@Param("itemId") Long itemId);
}
