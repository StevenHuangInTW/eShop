package com.tw.eshop.order.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by qbhuang on 16/05/2017.
 */
public class JsonUtilityTest {

    @Test
    public void should_return_null_given_null_as_source() throws IOException {
        String actual = JsonUtility.convertToString(null, Map.class);
        Assert.assertNull(actual);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_null_point_exception_give_null_class_type() throws IOException {
        JsonUtility.convertToString(new HashMap<String, String>(), null);
    }

    @Test
    public void should_return_json_given_map_values() throws IOException {
        String  expected = "{\"name\":\"testName\"}";
        Map<String, String> testInputMap = new HashMap<>();
        testInputMap.put("name","testName");
        String actual = JsonUtility.convertToString(testInputMap, Map.class);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void should_return_json_given_map_values_with_condensed() throws IOException {
        String  expected = "{\"name\":\"testName\"}";
        Map<String, String> testInputMap = new HashMap<>();
        testInputMap.put("name","testName");
        String actual = JsonUtility.convertToString(testInputMap, Map.class, true);
        Assert.assertEquals(expected, actual);
    }
}