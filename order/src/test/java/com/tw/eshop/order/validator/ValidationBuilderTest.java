package com.tw.eshop.order.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by qbhuang on 16/05/2017.
 */

public class ValidationBuilderTest {

    private ValidationBuilder validationBuilder;

    @MockBean
    private BindingResult bindingResult;

    private List<FieldError> fieldErrors;

    @Before
    public void setup() {

        validationBuilder = new ValidationBuilder();

        bindingResult = mock(BindingResult.class);

        fieldErrors = new ArrayList<>();
    }


    @Test
    public void should_return_null_given_no_errors() {

        when(bindingResult.hasFieldErrors()).thenReturn(false);

        String actual = validationBuilder.buildErrorMessage(bindingResult);

        verify(bindingResult,times(1)).hasFieldErrors();

        Assert.assertNull(actual);
    }

    @Test
    public void should_return_error_string_given_field_errors() {

        when(bindingResult.hasFieldErrors()).thenReturn(true);

        fieldErrors.add(new FieldError("OrderEntity", "Price", "Cannot be negative."));
        fieldErrors.add(new FieldError("OrderEntity", "Name", "Cannot be empty."));
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        String actual = validationBuilder.buildErrorMessage(bindingResult);

        String expected = "{\"Price\":\"Cannot be negative.\",\"Name\":\"Cannot be empty.\"}";
        Assert.assertEquals(expected, actual);
    }
}