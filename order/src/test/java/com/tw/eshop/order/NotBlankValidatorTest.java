package com.tw.eshop.order;

import com.tw.eshop.order.validator.NotBlankValidator;
import com.tw.eshop.order.validator.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by qbhuang on 16/8/18.
 */
public class NotBlankValidatorTest {

    public static final String TEST_STRING = "TEST STRING";
    public static final String EMPTY_STRING = "";
    public static final String BLANK_STRING = "     ";
    public static final String TEST_DATA_WITH_BLANK_AROUND = "  TEST_DATA ";
    private Validator<String> notBlankValidator;

    @Before
    public  void setup(){
        notBlankValidator = new NotBlankValidator();
    }


    @Test
    public void should_success_given_string() throws Exception {
        boolean expected = true;

        boolean actual = notBlankValidator.validate(TEST_STRING).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_fail_given_null_string() throws Exception {
        boolean expected = false;

        boolean actual = notBlankValidator.validate(null).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_fail_given_empty_string() throws Exception {
        boolean expected = false;

        boolean actual = notBlankValidator.validate(EMPTY_STRING).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_fail_given_blank_string() throws Exception {
        boolean expected = false;

        boolean actual = notBlankValidator.validate(BLANK_STRING).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void should_success_given_string_With_blank_around() throws Exception {
        boolean expected = true;

        boolean actual = notBlankValidator.validate(TEST_DATA_WITH_BLANK_AROUND).isValid();

        Assert.assertEquals(expected,actual);
    }
}