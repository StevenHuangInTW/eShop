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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by qbhuang on 16/8/17.
 */

@RestController
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private ValidationBuilder validationBuilder;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponse order(@RequestBody @Valid Order orderEntity, BindingResult bindingResult) {
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

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpResponse list() {
        HttpResponse<OrderResult> result = new HttpResponse<>();
        result.setMessage("True");
        return result;
    }
}
