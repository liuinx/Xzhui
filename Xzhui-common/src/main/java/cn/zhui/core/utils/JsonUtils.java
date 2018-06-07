package cn.zhui.core.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by LIN on 2018/06/07.
 */
public class JsonUtils {

    private static final String lock = "lock";
    private static ObjectMapper objectMapper = null;

    public static ObjectMapper getObjectMapper() {
        if (objectMapper != null) {
            return objectMapper;
        }
        synchronized (lock) {
            if (objectMapper != null) {
                return  objectMapper;
            }
            objectMapper = (ObjectMapper) (SpringContextHolder.getOneBean(ObjectMapper.class) == null ? new ObjectMapper() : SpringContextHolder.getOneBean(ObjectMapper.class));
        }
        return objectMapper;
    }

    /**
     * 把对象转为Json字符串
     */
    public static String writeValueAsString(Object object) throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(object);
    }

    /**
     * 将字符串转为对象
     */
    public static <T> T convertValue(String fromValue,Class<T> toValueType) throws JsonParseException, IOException {
        getObjectMapper().configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        return  getObjectMapper().readValue(fromValue, toValueType);
    }

    /**
     * 将Json字符串转为列表
     */
    public static <T> T convertConllection(String fromValue, Class<T> conllectionClass, Class<?> subCollectionClass, Class<?>... elementClasses) throws IOException{
        JavaType javaType = getObjectMapper().getTypeFactory().constructParametrizedType(conllectionClass, subCollectionClass, elementClasses);
        return getObjectMapper().readValue(fromValue, javaType);
    }

    public static JavaType getConllectionType(Class<?> collectionClass, Class<?> subCollectionClass, Class<?>... elementClasses){
        return getObjectMapper().getTypeFactory().constructParametrizedType(collectionClass, subCollectionClass, elementClasses);
    }
}
