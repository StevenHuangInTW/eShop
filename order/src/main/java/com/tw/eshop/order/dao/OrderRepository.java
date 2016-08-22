package com.tw.eshop.order.dao;

import com.tw.eshop.order.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by qbhuang on 16/8/18.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order,String> {
}
