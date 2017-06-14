/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tw.eshop.order.service.impl;

import com.tw.eshop.order.dao.OrderRepository;
import com.tw.eshop.order.exception.InvalidDataException;
import com.tw.eshop.order.exception.NotExistsOrderException;
import com.tw.eshop.order.model.Order;
import com.tw.eshop.order.service.OrderService;
import com.tw.eshop.order.validator.Validator;
import com.tw.eshop.order.vo.ValidateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private Validator<Order> orderValidator;

    private Validator<String> notBlankValidator;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, Validator<Order> orderValidator, Validator<String> notBlankValidator) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.notBlankValidator = notBlankValidator;
    }

    @Override
    @Transactional
    public void create(Order order) {
        ValidateResult validateResult = orderValidator.validate(order);
        if (!validateResult.isValid()) {
            throw new InvalidDataException(validateResult.getMsg());
        }

        order.setId(String.valueOf(System.currentTimeMillis()));
        order.setCreateAt(new Date());
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void delete(String id) {
        ValidateResult validateResult = notBlankValidator.validate(id);
        if (!validateResult.isValid()) {
            throw new InvalidDataException(validateResult.getMsg());
        }

        Order existsOrder = orderRepository.findOne(id);
        if (existsOrder == null) {
            throw new NotExistsOrderException("Invalid order id");
        }

        orderRepository.delete(existsOrder);
    }

    @Override
    public List<Order> getAll() {
        Iterable<Order> orderIterable = orderRepository.findAll();
        List<Order> orders = new ArrayList<>();
        orderIterable.forEach(orders::add);
        return orders;
    }
}
