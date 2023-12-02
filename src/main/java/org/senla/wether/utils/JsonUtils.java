package org.senla.wether.utils;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

    public static final Gson gson = new Gson();

    public static <T> List<T> parseJsonArray(String jsonArray, Class<T[]> model) {
        return Arrays.asList(gson.fromJson(jsonArray, model));
    }

    public static <T> T parseJsonObject(String jsonObject, Class<T> model) {
        return gson.fromJson(jsonObject, model);
    }

    public static <T> String listToJson(List<T> objectList) {
        return gson.toJson(objectList);
    }

    public static <T> String objToJson(T object) {
        return gson.toJson(object);
    }
}
