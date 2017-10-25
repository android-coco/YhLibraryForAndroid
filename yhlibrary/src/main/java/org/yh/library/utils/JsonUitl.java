package org.yh.library.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yhlyl on 2017/5/11.
 * Gson处理json数据
 */

public class JsonUitl
{

    /**
     * 将json字符串转化成实体对象
     *
     * @param json
     * @param classOfT
     * @return json
     */
    public static Object stringToObject(String json, Class classOfT)
    {
        Gson mGson = new Gson();
        return mGson.fromJson(json, classOfT);
    }

    /**
     * 将对象转换为json字符串 或者 把list 转化成json
     *
     * @param object
     * @param <T>
     * @return json
     */
    public static <T> String objectToString(T object)
    {
        Gson mGson = new Gson();
        return mGson.toJson(object);
    }

    /**
     * 把json 字符串转化成Objct
     *
     * @param json
     * @param cls
     * @param <T>
     * @return json
     */
    public static <T> T stringToTObject(String json, Class<T> cls)
    {
        Gson mGson = new Gson();
        return mGson.fromJson(json, cls);
    }

    /**
     * 把json 字符串转化成list
     *
     * @param json
     * @param cls
     * @param <T>
     * @return list
     */
    public static <T> List<T> stringToList(String json, Class<T> cls)
    {
        Gson mGson = new Gson();
        List<T> list = new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array)
        {
            list.add(mGson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 将Json数组解析成相应的映射对象列表
     * @param jsonData
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> cls)
    {
        Gson gson = new Gson();
        List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>()
        {
        }.getType());
        return result;
    }

    /**
     * 把json 字符串转化成list
     *
     * @param json
     * @return map
     */
    public static Map<String, Object> stringToMap(String json)
    {
        Gson mGson = new Gson();
        Map<String, Object> retMap = mGson.fromJson(json,
                new TypeToken<Map<String, List<Object>>>()
                {
                }.getType());
        return retMap;
    }
}
