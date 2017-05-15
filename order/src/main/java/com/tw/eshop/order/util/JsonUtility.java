package com.tw.eshop.order.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

/**
 * Created by qbhuang on 16/8/17.
 */
public class JsonUtility {

    private JsonUtility() {

    }

    public static <T> String convertToString(T t, Class<T> classType, boolean condensed) throws IOException {
        if (t == null)
            return null;
        if (classType == null) {
            throw new NullPointerException("Target classType cannot be null.");
        }
        ObjectMapper mapper = new ObjectMapper();
        //Enable Jackson to handle the unquoted field name.
        mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        if (condensed) {
            mapper.disable(SerializationFeature.INDENT_OUTPUT);
        }
        mapper.registerModule(new JavaTimeModule());
        String content = null;
        try {
            content = mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new JsonConvertException(e);
        }
        return content;
    }

    public static <T> String convertToString(T t, Class<T> classType) throws IOException {
        return convertToString(t, classType, false);
    }
}