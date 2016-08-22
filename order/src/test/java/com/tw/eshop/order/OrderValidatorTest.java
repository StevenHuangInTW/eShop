package com.tw.eshop.order;

import com.tw.eshop.web.model.Order;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by qbhuang on 16/8/18.
 */
public class OrderValidatorTest {

    public static final String TEST_ORDER = "TEST_ORDER";
    public static final double TEST_PRICE = 10d;
    public static final int TEST_QTY = 100;
    public static final String BLANK_STRING = "         ";
    public static final String EMPTY_STRING = "";
    private Validator<Order> orderValidator;
    private  Order exampleOrder;

    @Before
    public void setUp() throws Exception {
        orderValidator = new OrderValidator();
        exampleOrder = new Order();

        exampleOrder.setName(TEST_ORDER);
        exampleOrder.setPrice(TEST_PRICE);
        exampleOrder.setQty(TEST_QTY);
    }

    @Test
    public void should_fail_given_null_order_entity() throws Exception {
        boolean expected = false;

        boolean actual = orderValidator.validate(null).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_fail_given_blank_name(){
        exampleOrder.setName(BLANK_STRING);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }


    @Test
    public void should_fail_given_empty_name(){
        exampleOrder.setName(EMPTY_STRING);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_fail_given_null_price(){
        exampleOrder.setPrice(null);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_fail_given_negative_price(){
        exampleOrder.setPrice(-1d);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_fail_given_zero_price(){
        exampleOrder.setPrice(0d);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_fail_given_negative_qty(){
        exampleOrder.setQty(-1);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_fail_given_zero_qty(){
        exampleOrder.setQty(0);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_fail_given_null_qty(){
        exampleOrder.setQty(null);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_success_given_all_fields_value(){
        boolean expected = true;
        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected, actual);
    }
}