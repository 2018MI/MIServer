package org.chengpx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射工具类
 *
 * @author chengpx
 */
public class ReflectUtils {

    private static final Logger S_LOGGER = LoggerFactory.getLogger(ReflectUtils.class);

    private ReflectUtils() {
    }

    /**
     * 获得超类的参数类型，取第一个参数类型
     *
     * @param <T>   类型参数
     * @param clazz 超类类型
     */

    public static <T> Class<T> getActualTypeArg(Class clazz) {
        return getActualTypeArg(clazz, 0);
    }

    /**
     * 根据索引获得超类的参数类型
     *
     * @param clazz 超类类型
     * @param index 索引
     */
    public static <T> Class<T> getActualTypeArg(Class clazz, int index) {
        if (ObjectUtils.hasNullVal(clazz)) {
            return null;
        }
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return null;
        }
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return null;
        }
        if (!(params[index] instanceof Class)) {
            return null;
        }
        return (Class<T>) params[index];
    }

    /**
     * 获取字段的类型
     *
     * @param aClass 类字节码对象
     * @param field  字段名称
     * @return 字段的类型
     */
    public static Class getFieldType(Class aClass, String field) {
        if (ObjectUtils.hasNullVal(aClass, field)) {
            return null;
        }
        try {
            Field declaredField = aClass.getDeclaredField(field);
            declaredField.setAccessible(true);
            return declaredField.getType();
        } catch (Exception e) {
            e.printStackTrace();
            S_LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行一个方法
     *
     * @param obj            对象
     * @param methodName     方法名
     * @param parameterTypes 方法形参列表
     * @return 方法对象
     */
    public static Method getMethod(Object obj, String methodName, Class<?>... parameterTypes) {
        if (ObjectUtils.hasNullVal(obj, methodName)) {
            return null;
        }
        try {
            Method method = obj.getClass().getMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method;
        } catch (Exception e) {
            e.printStackTrace();
            S_LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
