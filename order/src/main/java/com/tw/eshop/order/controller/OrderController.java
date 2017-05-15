package com.tw.eshop.order.controller;

import com.tw.eshop.order.exception.InvalidDataException;
import com.tw.eshop.order.exception.NotExistsOrderException;
import com.tw.eshop.order.model.Order;
import com.tw.eshop.order.service.OrderService;
import com.tw.eshop.order.validator.ValidationBuilder;
import com.tw.eshop.order.vo.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> newOrder(@RequestBody @Valid Order orderEntity, BindingResult bindingResult) {
        HttpResponse<Order> result = new HttpResponse<>();
        logger.debug("Attempt to create new order, name = " + orderEntity.getName() + ", price=" + orderEntity.getPrice() + ", qty=" + orderEntity.getQty());
        if (bindingResult.hasErrors()) {
            result.setMessage(validationBuilder.buildErrorMessage(bindingResult));
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        try {
            orderService.create(orderEntity);
            result.setData(orderEntity);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (InvalidDataException e) {
            result.setMessage(e.getMessage());
            logger.warn(e.getMessage(), e);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            logger.warn(e.getMessage(), e);
            return new ResponseEntity<>(result, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "order", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpResponse> deleteOrder(@RequestParam(name = "orderId") String orderId) {
        HttpResponse<String> result = new HttpResponse<>();
        if (logger.isDebugEnabled()) {
            logger.debug("Attempt to delete order, id = " + orderId);
        }

        try {
            orderService.delete(orderId);
            result.setData(orderId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotExistsOrderException e) {
            result.setMessage(e.getMessage());
            logger.warn(e.getMessage(), e);
            return new ResponseEntity(result, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            logger.warn(e.getMessage(), e);
            return new ResponseEntity<>(result, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}