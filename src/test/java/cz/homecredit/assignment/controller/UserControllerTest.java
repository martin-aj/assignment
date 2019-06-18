package cz.homecredit.assignment.controller;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.homecredit.assignment.model.Todo;
import cz.homecredit.assignment.model.User;
import cz.homecredit.assignment.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void whenGetUserDetails_andUserExists_thenRequestConfigurationSet() throws Exception {
        User user = initUser();

        when(userService.getUsersTodos(anyLong()))
                .thenReturn(user);

        MvcResult mvcResult = this.mockMvc.perform(get("/user/" + 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        User responseMvc = objectMapper
                .readValue(mvcResult.getResponse().getContentAsString(), User.class);
        assertEquals(responseMvc.getId(), user.getId());
        assertEquals(responseMvc.getName(), user.getName());
        assertEquals(responseMvc.getUsername(), user.getUsername());
        assertEquals(responseMvc.getEmail(), user.getEmail());
        assertEquals(responseMvc.getTodos().size(), user.getTodos().size());
        assertEquals(responseMvc.getTodos().toString(), user.getTodos().toString());
    }

    private User initUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Martin Jakubik");
        user.setUsername("martin_j");
        user.setEmail("rthrth.test@ggrtgsd.com");
        Todo todo1 = new Todo();
        todo1.setCompleted(Boolean.FALSE);
        todo1.setTitle("Title1");
        Todo todo2 = new Todo();
        todo2.setCompleted(Boolean.TRUE);
        todo2.setTitle("Todo title");
        List<Todo> todoList = new ArrayList<>();
        todoList.add(todo1);
        todoList.add(todo2);
        user.setTodos(todoList);

        return user;
    }
}