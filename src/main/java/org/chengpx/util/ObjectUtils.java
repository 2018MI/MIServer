package org.chengpx.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

/**
 * 对象工具类
 *
 * @author chengpx
 * @date 2018/7/16 10:53
 */
public class ObjectUtils {

    private static final Logger S_LOGGER = LoggerFactory.getLogger(ObjectUtils.class);

    private ObjectUtils() {
    }

    /**
     * 是否不含有空值
     *
     * @param params 数据数组
     * @return 不含有返回 true, 否则返回 false
     */
    public static boolean nonNullVal(Object... params) {
        return !hasNullVal(params);
    }

    /**
     * 是否含有空值
     *
     * @param params 数据数组
     * @return 含有返回 true, 否则返回 false
     */
    public static boolean hasNullVal(Object... params) {
        for (Object param : params) {
            if (null == param) {
                return true;
            }
            if (param instanceof String) {
                String obj = (String) param;
                if (StringUtils.isBlank(obj)) {
                    S_LOGGER.debug(param + "(String) length is 0");
                    return true;
                }
            }
            if (param instanceof Object[]) {
                Object[] obj = (Object[]) param;
                if (obj.length < 1) {
                    S_LOGGER.debug(param + "(Object[]) length is 0");
                    return true;
                }
            }
            if (param instanceof Collection) {
                Collection obj = (Collection) param;
                if (obj.size() < 1) {
                    S_LOGGER.debug(param + "(Collection) size is 0");
                    return true;
                }
            }
            if (param instanceof Map) {
                Map obj = (Map) param;
                if (obj.size() < 1) {
                    S_LOGGER.debug(param + "(Map) size is 0");
                    return true;
                }
            }
        }
        return false;
    }

}
