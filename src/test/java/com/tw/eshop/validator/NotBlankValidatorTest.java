package com.tw.eshop.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by qbhuang on 16/8/18.
 */
public class NotBlankValidatorTest {

    public static final String TEST_STRING = "TEST STRING";
    public static final String EMPTY_STRING = "";
    public static final String BLANK_STRING = "     ";
    public static final String TEST_DATA_WITH_BLANK_AROUD = "  TEST_DATA ";
    private Validator<String> notBlankValidator;

    @Before
    public  void setup(){
        notBlankValidator = new NotBlankValidator();
    }


    @Test
    public void shouldSuccessGivenString() throws Exception {
        boolean expected = true;

        boolean actual = notBlankValidator.validate(TEST_STRING).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldFailGivenNullString() throws Exception {
        boolean expected = false;

        boolean actual = notBlankValidator.validate(null).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldFailGivenEmptyString() throws Exception {
        boolean expected = false;

        boolean actual = notBlankValidator.validate(EMPTY_STRING).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldFailGivenBlankString() throws Exception {
        boolean expected = false;

        boolean actual = notBlankValidator.validate(BLANK_STRING).isValid();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void shouldSuccessGivenStringWithBlankArround() throws Exception {
        boolean expected = true;

        boolean actual = notBlankValidator.validate(TEST_DATA_WITH_BLANK_AROUD).isValid();

        Assert.assertEquals(expected,actual);
    }
}