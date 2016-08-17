package com.tw.eshop.dao;

import com.tw.eshop.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by qbhuang on 16/8/18.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order,String> {
}
