package com.tw.eshop.controller;

import com.tw.eshop.model.Order;
import com.tw.eshop.vo.HttpResponse;
import com.tw.eshop.vo.OrderResult;
import com.tw.eshop.service.OrderService;
import com.tw.eshop.validator.ValidationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by qbhuang on 16/8/17.
 */

@RestController
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private ValidationBuilder validationBuilder;

    @RequestMapping(value = "order", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponse newOrder(@RequestBody @Valid Order orderEntity, BindingResult bindingResult) {
        HttpResponse<Order> result = new HttpResponse<>();
        logger.debug("Attempt to create new order, name = " + orderEntity.getName() + ", price=" + orderEntity.getPrice() + ", qty=" + orderEntity.getQty());
        if (bindingResult.hasErrors()) {
            result.setMessage(validationBuilder.buildErrorMessage(bindingResult));
        } else {

            try {
                orderService.create(orderEntity);
                result.setData(orderEntity);
            } catch (Exception e) {
                result.setMessage(e.getMessage());
            }
        }
        return result;
    }

    @RequestMapping(value = "order", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponse deleteOrder(@RequestParam(name = "orderId") String orderId) {
        HttpResponse<String> result = new HttpResponse<>();
        logger.debug("Attempt to delete order, id = "+ orderId);

            try {
                orderService.delete(orderId);
                result.setData(orderId);
            } catch (Exception e) {
                result.setMessage(e.getMessage());
            }
        return result;
    }
}