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

package com.tw.eshop.service;

import com.tw.eshop.dao.OrderRepository;
import com.tw.eshop.model.Order;
import com.tw.eshop.validator.Validator;
import com.tw.eshop.vo.ValidateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

    @Autowired
    private Validator<Order> orderValidator;

    @Autowired
    private  Validator<String> notBlankValidator;

	@Transactional
	public void create(Order order) {
        ValidateResult validateResult = orderValidator.validate(order);
		if(!validateResult.isValid()){
            throw new RuntimeException(validateResult.getMsg());
        }

        order.setId(String.valueOf(System.currentTimeMillis()));
        order.setCreateAt(new Date());
        orderRepository.save(order);
    }

	@Transactional
    public void delete(String id) {
    	ValidateResult validateResult = notBlankValidator.validate(id);
	    if(!validateResult.isValid()){
            throw new RuntimeException(validateResult.getMsg());
        }
	    orderRepository.delete(id);
    }
}
