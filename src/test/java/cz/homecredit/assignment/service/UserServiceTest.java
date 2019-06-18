package cz.homecredit.assignment.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cz.homecredit.assignment.model.Todo;
import cz.homecredit.assignment.model.User;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SuppressWarnings("unchecked")
public class UserServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetUserDetails_andUserExists_thenRequestConfigurationSet() {
        User user = mock(User.class);
        List<Todo> todoList = mock(List.class);
        when(user.getId()).thenReturn(1L);
        when(user.getName()).thenReturn("NameOfUser");
        when(user.getUsername()).thenReturn("UsernameOfUser");
        when(user.getEmail()).thenReturn("EmailOfUser");

        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        UserService userService = new UserServiceImpl(restTemplateBuilder);

        ResponseEntity userResponseEntity = mock(ResponseEntity.class);
        ResponseEntity todoResponseEntity = mock(ResponseEntity.class);
        when(restTemplate.exchange(any(), any(ParameterizedTypeReference.class))).thenReturn(todoResponseEntity);
        when(restTemplate.exchange(any(), any(Class.class))).thenReturn(userResponseEntity);
        when(todoResponseEntity.getBody()).thenReturn(todoList);
        when(userResponseEntity.getBody()).thenReturn(user);

        User response = userService.getUsersTodos(4);
        assert(response.getId().equals(user.getId()));
        assert(response.getName().equals(user.getName()));
        assert(response.getUsername().equals(user.getUsername()));
        assert(response.getEmail().equals(user.getEmail()));
    }
}