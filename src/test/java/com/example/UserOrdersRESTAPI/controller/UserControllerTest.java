package com.example.UserOrdersRESTAPI.controller;

import com.example.UserOrdersRESTAPI.entity.User;
import com.example.UserOrdersRESTAPI.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetAllUsersSummary() throws Exception {
        User user1 = new User("User1", "user1@example.com");
        user1.setId(1);
        User user2 = new User("User2", "user2@example.com");
        user2.setId(2);
        List<User> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{" +
                        "\"id\":1," +
                        "\"name\":\"User1\"," +
                        "\"email\":\"user1@example.com\"}," +
                        " {\"id\":2" +
                        ",\"name\":\"User2\"," +
                        "\"email\":\"user2@example.com\"}]"));
    }

    @Test
    public void testGetUserDetails() throws Exception {

        User user = new User("User", "user@example.com");
        user.setId(1);

        when(userService.getUserById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{" +
                        "\"id\":1," +
                        "\"name\":\"User\"," +
                        "\"email\":\"user@example.com\"}"));
    }
}
