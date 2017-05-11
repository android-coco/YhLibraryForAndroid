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
 */

public class JsonUitl
{
    private static Gson mGson = new Gson();

    /**
     * 将json字符串转化成实体对象
     *
     * @param json
     * @param classOfT
     * @return
     */
    public static Object stringToObject(String json, Class classOfT)
    {
        return mGson.fromJson(json, classOfT);
    }

    /**
     * 将对象转换为json字符串 或者 把list 转化成json
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String objectToString(T object)
    {
        return mGson.toJson(object);
    }

    /**
     * 把json 字符串转化成list
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> stringToList(String json, Class<T> cls)
    {
        List<T> list = new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array)
        {
            list.add(mGson.fromJson(elem, cls));
        }
        return list;
    }

    /**
     * 把json 字符串转化成list
     *
     * @param json
     * @return
     */
    public static Map<String, Object> stringToMap(String json)
    {
        Map<String, Object> retMap = mGson.fromJson(json,
                new TypeToken<Map<String, List<Object>>>()
                {
                }.getType());
        return retMap;
    }
}
