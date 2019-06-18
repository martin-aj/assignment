package cz.homecredit.assignment.service;

import static org.springframework.http.HttpMethod.GET;

import cz.homecredit.assignment.model.Todo;
import cz.homecredit.assignment.model.User;
import java.net.URI;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public UserServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public User getUsersTodos(long userId) {
        final URI userUri = UriComponentsBuilder.fromUri(URI.create(BASE_URL))
                .path("/users/" + userId)
                .build()
                .toUri();
        final RequestEntity<?> requestUserEntity = new RequestEntity<>(GET, userUri);
        User response = restTemplate.exchange(requestUserEntity, User.class).getBody();

        final URI todoUri = UriComponentsBuilder.fromUri(URI.create(BASE_URL))
                .path("/todos/")
                .queryParam("userId", userId)
                .build()
                .toUri();
        final RequestEntity<?> requestTodoEntity = new RequestEntity<>(GET, todoUri);
        List<Todo> todos = restTemplate.exchange(requestTodoEntity, new ParameterizedTypeReference<List<Todo>>() {}).getBody();

        if (response != null) {
            response.setTodos(todos);
        }

        return response;
    }
}
