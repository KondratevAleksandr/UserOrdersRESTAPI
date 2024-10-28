package com.example.UserOrdersRESTAPI.controller;

import com.example.UserOrdersRESTAPI.entity.Order;
import com.example.UserOrdersRESTAPI.entity.User;
import com.example.UserOrdersRESTAPI.service.OrderService;
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


public class OrderControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testGetAllOrders() throws Exception {
        User user1 = new User("user1", "user1@example.com");
        Order order1 = new Order(user1, "Product1", 100.0, "Completed");
        order1.setId(1);

        User user2 = new User("user2", "user2@example.com");
        Order order2 = new Order(user2, "Product2", 200.0, "Pending");
        order2.setId(2);

        List<Order> orders = Arrays.asList(order1, order2);

        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{" +
                        "\"productDetails\":\"Product1\"," +
                        "\"totalAmount\":100.0}," +
                        "{\"productDetails\":\"Product2\"," +
                        "\"totalAmount\":200.0}]"));
    }

    @Test
    public void testGetOrderById() throws Exception {
        User user = new User("User", "user@example.com");
        Order order = new Order(user, "Product", 150.0, "Processing");
        order.setId(1);

        when(orderService.getOrderById(1)).thenReturn(Optional.of(order));

        mockMvc.perform(get("/orders/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{" +
                        "\"id\":1," +
                        "\"productDetails\":\"Product\"," +
                        "\"totalAmount\":150.0," +
                        "\"status\":\"Processing\"," +
                        "\"user\":{" +
                            "\"name\":\"User\"," +
                            "\"email\":\"user@example.com\"}" +
                        "}"));
    }
}
