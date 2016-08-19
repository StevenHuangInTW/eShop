package com.tw.eshop.service;

import com.tw.eshop.model.Order;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qbhuang on 16/8/19.
 */
public interface OrderService {
    void create(Order order);

    void delete(String id);
}
