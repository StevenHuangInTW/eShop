package com.tw.eshop.order.service;

import com.tw.eshop.order.model.Order;

/**
 * Created by qbhuang on 16/8/19.
 */
public interface OrderService {
    void create(Order order);

    void delete(String id);
}
