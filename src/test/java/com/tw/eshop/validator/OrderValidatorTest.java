package com.tw.eshop.validator;

import com.tw.eshop.model.Order;
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
    public void shouldFailGivenNullOrderEntity() throws Exception {
        boolean expected = false;

        boolean actual = orderValidator.validate(null).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldFailGivenBlankName(){
        exampleOrder.setName(BLANK_STRING);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }


    @Test
    public void shouldFailGivenEmptyName(){
        exampleOrder.setName(EMPTY_STRING);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldFailGivenNullPrice(){
        exampleOrder.setPrice(null);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldFailGivenNullQty(){
        exampleOrder.setQty(null);

        boolean expected = false;

        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldSuccessGivenAllFieldsValue(){
        boolean expected = true;
        boolean actual = orderValidator.validate(exampleOrder).isValid();

        Assert.assertEquals(expected, actual);
    }
}