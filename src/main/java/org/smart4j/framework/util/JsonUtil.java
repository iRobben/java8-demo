package org.smart4j.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Json工具类
 * @author zhangrh
 * @date 2016/11/24 0024
 */
public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper  OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO 转为 json
     */
    public static <T> String toJson(T obj){
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.error("convert POJO to json failure",e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将json 转为 POJO
     */
    public static <T> T fomJson(String json,Class<T> type){
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            LOGGER.error("convert POJO to json failure",e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
