package com.tw.eshop.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by qbhuang on 16/8/17.
 */
public class JsonUtility{

    public static <T> String convertToString(T t, Class<T> classType, boolean condensed) throws JsonConvertException, IOException {
        if (t == null)
            return null;
        if (classType == null){
            throw new NullPointerException("Target classType cannot be null.");
        }
        ObjectMapper mapper = new ObjectMapper();
        //Enable Jackson to handle the unquoted field name.
        mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        if (condensed){
            mapper.disable(SerializationFeature.INDENT_OUTPUT);
        }
        mapper.registerModule(new JavaTimeModule());
        String content = null;
        try {
            content = mapper.writeValueAsString(t);
        }catch (JsonProcessingException e){
            throw new JsonConvertException(e,t,classType);
        }
        return content;
    }
    public static <T> String convertToString(T t, Class<T> classType) throws JsonConvertException, IOException {
        return  convertToString(t, classType, false);
    }
}