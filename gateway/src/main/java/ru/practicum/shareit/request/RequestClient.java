package ru.practicum.shareit.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import ru.practicum.shareit.client.BaseClient;
import ru.practicum.shareit.request.dto.RequestItemRequestDto;

@Component
public class RequestClient extends BaseClient {
    private static final String API_PREFIX = "/requests";

    @Autowired
    public RequestClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> create(RequestItemRequestDto itemRequestDto, Long userId) {
        return post("", userId, itemRequestDto);
    }

    public ResponseEntity<Object> update(Long itemId, RequestItemRequestDto itemRequestDto) {
        return patch("/" + itemId, itemRequestDto);
    }

    public ResponseEntity<Object> getAllForUser(Long userId) {
        return get("", userId);
    }

    public ResponseEntity<Object> getAllForOtherUsers(Long userId) {
        return get("/all", userId);
    }

    public ResponseEntity<Object> get(Long itemId) {
        return get("/" + itemId);
    }

    public ResponseEntity<Object> delete(Long itemId) {
        return delete("/" + itemId);
    }
}
