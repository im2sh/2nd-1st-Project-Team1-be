package com.gdsc.canigraduate.util;

import com.gdsc.canigraduate.domain.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Arrays;

public class BaseEnumConverterFactory implements ConverterFactory<String, Enum<? extends BaseEnum>> {

    @Override
    public <T extends Enum<? extends BaseEnum>> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnumsConverter<>(targetType);
    }

    private static final class StringToEnumsConverter<T extends Enum<? extends BaseEnum>> implements Converter<String, T> {
        private final Class<T> enumType;
        private final boolean constantEnum;

        public StringToEnumsConverter(Class<T> enumType) {
            this.enumType = enumType;
            this.constantEnum = Arrays.stream(enumType.getInterfaces()).anyMatch(i -> i == BaseEnum.class);
        }


        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }

            T[] constants = enumType.getEnumConstants();
            for (T c : constants) {
                if (constantEnum) {
                    if (((BaseEnum) c).getValue().equals(source.trim())) {
                        return c;
                    }
                } else {
                    if (c.name().equals(source.trim())) {
                        return c;
                    }
                }
            }
            return null;
        }
    }
}
