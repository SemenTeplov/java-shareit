package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.DefaultUriBuilderFactory;

import ru.practicum.shareit.client.BaseClient;
import ru.practicum.shareit.item.dto.CommentRequestDto;
import ru.practicum.shareit.item.dto.ItemRequestDto;

import java.util.Map;

@Controller
public class ItemClient extends BaseClient {
    private static final String API_PREFIX = "/items";

    @Autowired
    public ItemClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> create(ItemRequestDto itemRequestDto, Long userId) {
        return post("", userId, itemRequestDto);
    }

    public ResponseEntity<Object> addComment(Long itemId, CommentRequestDto comment, Long userId) {
        return post("/" + itemId + "/comment", userId, comment);
    }

    public ResponseEntity<Object> update(Long itemId, ItemRequestDto itemRequestDto, Long userId) {
        return patch("/" + itemId, userId, itemRequestDto);
    }

    public ResponseEntity<Object> getAll(Long userId) {
        return get("", userId);
    }

    public ResponseEntity<Object> get(Long itemId) {
        return get("/" + itemId);
    }

    public ResponseEntity<Object> search(String text, Long itemId) {
        Map<String, Object> parameters = Map.of(
                "text", text
        );

        return get("/search", itemId, parameters);
    }

    public ResponseEntity<Object> delete(Long itemId) {
        return delete("/" + itemId);
    }
}
