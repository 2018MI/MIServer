package org.chengpx.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON 工具类
 *
 * @author chengpx
 * @date 2018/8/15 17:08
 */
public class JsonUtils {

    private static final Logger S_LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static Gson sGson;

    /**
     * 返回 gson 单例对象
     *
     * @return Gson 对象
     */
    public static Gson getGson() {
        if (sGson == null) {
            synchronized (JsonUtils.class) {
                if (sGson == null) {
                    sGson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();
                }
            }
        }
        return sGson;
    }

}
