package com.wq.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

/**
 * JSON 相关的工具类, 使用jackson实现
 *
 * Created by xiaolong.lou on 15/10/27.
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ObjectMapper nonNullMapper = new ObjectMapper();

    static {
        // 未找到字段不报错
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        nonNullMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 无 null 的mapper
        nonNullMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 对象转Json，默认输出 null 值
     *
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) {
        return toJsonString(obj, false);
    }

    /**
     * 对象转json
     *
     * @param obj
     * @param nonNull 标识是否要讲null字段输出，true表示不输出，false表示输出null
     * @return
     */
    public static String toJsonString(Object obj, boolean nonNull) {
        if (obj == null) return null;

        try {
            if (nonNull) {
                return nonNullMapper.writeValueAsString(obj);
            } else {
                return mapper.writeValueAsString(obj);
            }

        } catch (Exception e) {
            logger.error("JSON 序列化错误", e);
        }

        return null;
    }

    /**
     * 从字符串中解析出 json 对象
     *
     * @param content
     * @param clazz 如果是数组，可以使用 Object[].class
     * @return
     */
    public static <E> E parseObject(String content, Class<E> clazz) {
        if (StringUtils.isBlank(content)) return null;

        try {
            return mapper.readValue(content, clazz);
        } catch (Exception e) {
            logger.error("JSON 反序列化错误", e);
        }

        return null;
    }

    /**
     * 从字符串中解析出 json 对象
     *
     * @param content
     * @param valueTypeRef 负责类型
     * @return
     */
    public static <E> E parseObject(String content, TypeReference<?> valueTypeRef) {
        if (content == null) return null;
        try {
            return mapper.readValue(content, valueTypeRef);
        } catch (Exception e) {
            logger.error("JSON 反序列化错误", e);
        }

        return null;
    }

    /**
     * 从 json 中解析 List
     *
     * @param content
     * @param listType
     * @return
     */
    public static <E> List<E> parseList(String content, TypeReference<List<E>> listType) {
        if (content == null) {
            return null;
        }
        try {
            return mapper.readValue(content, listType);
        } catch (Exception e) {
            logger.error("JSON 反序列化错误", e);
        }

        return null;
    }

    /**
     * 从 json字符串 中解析 List
     *
     * @param jsonStr 欲转换json字符串
     * @param elementClass List元素class
     * @param <E>
     * @return
     */
    public static <E> List<E> parseList(String jsonStr, Class<E> elementClass) {
        return parseCollection(jsonStr, List.class, elementClass);
    }

    /**
     * 从 json字符串 中解析 集合
     *
     * @param jsonStr 欲转换json字符串
     * @param collectionClass 集合class
     * @param elementClass 集合元素class
     * @param <T> 集合类型
     * @param <E> 集合类型
     * @return
     */
    public static <T extends Collection<E>, E> T parseCollection(String jsonStr, Class<T> collectionClass, Class<E> elementClass) {
        if (StringUtils.isBlank(jsonStr)) return null;

        try {
            return (T) mapper.readValue(jsonStr, getCollectionType(collectionClass, elementClass));
        } catch (Exception e) {
            logger.error("JSON 反序列化错误", e);
        }

        return null;
    }

    /**
     * 获取json节点
     * @param jsonStr
     * @return
     */
    public static JsonNode getJsonNode(String jsonStr){
        if (StringUtils.isBlank(jsonStr)) return null;

        try {
            return mapper.readTree(jsonStr);
        } catch (Exception e) {
            logger.error("JSON 反序列化错误", e);
        }
        return null;
    }

    /**
     * 获取CollectionType
     *
     * @param collectionClass
     * @param elementClass
     * @return
     */
    private static CollectionType getCollectionType(Class<?> collectionClass, Class<?> elementClass) {
        return CollectionType.construct(collectionClass, mapper.constructType(elementClass));
    }
}