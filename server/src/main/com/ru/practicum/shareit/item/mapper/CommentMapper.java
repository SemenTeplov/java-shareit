package ru.practicum.shareit.item.mapper;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.user.model.User;

public class CommentMapper {
    public static CommentDto dtoMapper(Comment comment, User user) {
        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setItem(comment.getItem());
        commentDto.setAuthorName(user.getName());
        commentDto.setCreated(comment.getCreated());

        return commentDto;
    }

    public static Comment commentMapper(CommentDto commentDto, User user) {
        Comment comment = new Comment();

        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setItem(commentDto.getItem());
        comment.setAuthor(user.getId());
        comment.setCreated(commentDto.getCreated());

        return comment;
    }
}
