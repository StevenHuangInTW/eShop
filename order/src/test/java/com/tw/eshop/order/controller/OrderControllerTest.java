package com.tw.eshop.order.controller;

import com.tw.eshop.order.dao.OrderRepository;
import com.tw.eshop.order.exception.InvalidDataException;
import com.tw.eshop.order.exception.NotExistsOrderException;
import com.tw.eshop.order.model.Order;
import com.tw.eshop.order.service.OrderService;
import com.tw.eshop.order.validator.ValidationBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;

/**
 * Created by qbhuang on 15/05/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = OrderController.class, secure = false)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ValidationBuilder validationBuilder;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void it_should_return_200_response_when_post_an_valid_order_entity() throws Exception {
        String postOrderRequestJson = toIOUtf8String(getClass().getResource("/fixture/OrderControllerTest/post-order-request.json"));
        mvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postOrderRequestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_return_400_response_giving_an_order_with_empty_name() throws Exception {
        String postOrderRequestJson = toIOUtf8String(getClass().getResource("/fixture/OrderControllerTest/post-order-request-with-empty-name.json"));

        mvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postOrderRequestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void it_should_return_400_response_giving_an_order_with_price_less_zero() throws Exception {
        String postOrderRequestJson = toIOUtf8String(getClass().getResource("/fixture/OrderControllerTest/post-order-request-with-price-less-zero.json"));

        doThrow(InvalidDataException.class).when(orderService).create(any(Order.class));

        mvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postOrderRequestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void it_should_return_503_response_when_order_service_throw_exception() throws Exception {
        String postOrderRequestJson = toIOUtf8String(getClass().getResource("/fixture/OrderControllerTest/post-order-request.json"));

        doThrow(Exception.class).when(orderService).create(any(Order.class));

        mvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postOrderRequestJson))
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    public void it_should_return_200_response_when_delete_an_order() throws Exception {
        mvc.perform(delete("/order?orderId=1234"))
                .andExpect(status().isOk());
    }

    @Test
    public void it_should_return_404_response_when_delete_an_order() throws Exception {
        doThrow(NotExistsOrderException.class).when(orderService).delete(any(String.class));
        mvc.perform(delete("/order?orderId=1234"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void it_should_return_503_response_when_delete_an_order() throws Exception {
        doThrow(Exception.class).when(orderService).delete(any(String.class));
        mvc.perform(delete("/order?orderId=1234"))
                .andExpect(status().isServiceUnavailable());
    }

    public static String toIOUtf8String(URL fileUrl) {
        String content = null;
        try {
            content = org.apache.commons.io.IOUtils.toString(fileUrl.openStream(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
        return content;
    }
}