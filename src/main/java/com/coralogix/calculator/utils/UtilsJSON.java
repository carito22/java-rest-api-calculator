package com.coralogix.calculator.utils;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilsJSON {
    private static ObjectMapper om = new ObjectMapper();

    public static String objetoToJson(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> jsonToMap(String json) {
        try {
            return om.readValue(json, new TypeReference<Map<String, Object>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToObjeto(Class<T> clase, Object json) {
        try {
            return (T) om.readValue(objetoToJson(json), clase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
