package com.piotgrochowiecki.eriderent.dto.mapper;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

@CommonsLog
public abstract class BaseMapper {

    public static <T, K> K map(T inputType, Class<K> outputTypeClass) {
        try {
            K outputType = createInstance(outputTypeClass);
            BeanUtils.copyProperties(inputType, outputType);
            return outputType;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            log.error("Could not convert " + inputType.getClass().getSimpleName() + " into " +
                    outputTypeClass.getSimpleName());
            throw new RuntimeException(e);
        }
    }

    private static <T> T createInstance(Class<T> inputType) throws InstantiationException, IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        return inputType.getDeclaredConstructor()
                .newInstance();
    }

}
