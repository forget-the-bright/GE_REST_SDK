package io.github.forget_the_bright.ge.config;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * TODO
 *
 * @author wanghao(helloworlwh @ 163.com)
 * @since 2025/4/14 上午9:08
 */
public class EnumDeSerialzer implements ObjectDeserializer {
    @SneakyThrows
    @Override
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object fieldName) {
        if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            if (clazz.isEnum()) {
                String jsonString = defaultJSONParser.parseObject(String.class);
                if (jsonString == null) {
                    return null;
                }
                Field field = clazz.getDeclaredField("value");
                if (field != null) {
                    Object[] enumConstants = clazz.getEnumConstants();
                    for (Object enumConstant : enumConstants) {
                        if (enumConstant.toString().equals(jsonString)) {
                            return (T) enumConstant;
                        }
                    }
                }
                return (T) Enum.valueOf((Class<Enum>) clazz, jsonString);
            }
        }
        return null;
    }

    /**
     * 获取 FastJSON 的快速匹配令牌。
     *
     * @return 快速匹配令牌，当前实现返回 0
     */
    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
