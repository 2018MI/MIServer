package org.chengpx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统资源工具类
 *
 * @author chengpx
 * @date 2018/7/4 13:19
 */
public class ResUtils {

    private static final Logger S_LOGGER = LoggerFactory.getLogger(ResUtils.class);

    private ResUtils() {
    }

    /**
     * 对实现了 AutoCloseable 接口( Connection, ResultSet, PreparedStatement, Statement...)的资源进行关闭
     *
     * @param callableArr AutoCloseable 接口资源
     */
    public static void close(AutoCloseable... callableArr) {
        for (AutoCloseable autoCloseable : callableArr) {
            if (autoCloseable == null) {
                continue;
            }
            try {
                autoCloseable.close();
            } catch (Exception e) {
                S_LOGGER.error(e.getMessage());
            }
        }
    }

}
