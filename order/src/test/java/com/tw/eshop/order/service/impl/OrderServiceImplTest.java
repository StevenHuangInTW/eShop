package com.tw.eshop.order.service.impl;

import com.tw.eshop.order.dao.OrderRepository;
import com.tw.eshop.order.exception.InvalidDataException;
import com.tw.eshop.order.exception.NotExistsOrderException;
import com.tw.eshop.order.model.Order;
import com.tw.eshop.order.service.OrderService;
import com.tw.eshop.order.validator.NotBlankValidator;
import com.tw.eshop.order.validator.OrderValidator;
import com.tw.eshop.order.vo.ValidateResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by qbhuang on 16/05/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderValidator orderValidator;

    @Mock
    private NotBlankValidator notBlankValidator;

    private OrderService orderService;

    @Before
    public void setup() {
        orderService = new OrderServiceImpl(orderRepository, orderValidator, notBlankValidator);
    }

    @Test(expected = InvalidDataException.class)
    public void should_throw_invalid_data_exception_given_invalid_data() {
        Order testOrder = new Order();

        when(orderValidator.validate(testOrder)).thenReturn(new ValidateResult(false));

        orderService.create(testOrder);
    }

    @Test
    public void should_call_save_once_given_normal_order() {
        Order testOrder = new Order();

        when(orderValidator.validate(testOrder)).thenReturn(new ValidateResult(true));

        orderService.create(testOrder);

        verify(orderRepository).save(testOrder);
    }

    @Test(expected = InvalidDataException.class)
    public void should_throw_invalid_data_exception_given_invalid_data_to_delete() {
        String testOrderId = "1234";

        when(notBlankValidator.validate(testOrderId)).thenReturn(new ValidateResult(false));

        orderService.delete(testOrderId);
    }

    @Test
    public void should_call_delete_once_given_normal_order() {
        String testOrderId = "1234";
        Order testOrderToBeDelete = new Order();

        when(notBlankValidator.validate(testOrderId)).thenReturn(new ValidateResult(true));
        when(orderRepository.findOne(testOrderId)).thenReturn(testOrderToBeDelete);

        orderService.delete(testOrderId);

        verify(orderRepository).delete(testOrderToBeDelete);
    }

    @Test(expected = NotExistsOrderException.class)
    public void should_throw_not_exists_order_exception_given_invalid_order_id() {
        String testOrderId = "1234";

        when(notBlankValidator.validate(testOrderId)).thenReturn(new ValidateResult(true));
        when(orderRepository.findOne(testOrderId)).thenReturn(null);

        orderService.delete(testOrderId);
    }
}