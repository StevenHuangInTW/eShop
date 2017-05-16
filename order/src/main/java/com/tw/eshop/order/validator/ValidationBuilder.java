package com.tw.eshop.order.validator;

import com.tw.eshop.order.util.JsonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qbhuang on 16/8/17.
 */

@Component
public class ValidationBuilder {
    private Logger logger = LoggerFactory.getLogger(ValidationBuilder.class);

    public String buildErrorMessage(BindingResult bindingResult) {
        StringBuilder builder = new StringBuilder();
        if (bindingResult.hasFieldErrors()) {
            Map<String, String> map = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            try {
                builder.append(JsonUtility.convertToString(map, Map.class));
                return builder.toString();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }
}
