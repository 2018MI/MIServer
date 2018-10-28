package org.chengpx.mi.util;

import com.google.gson.Gson;

/**
 * create at 2018/4/17 21:29 by chengpx
 */
public class ResponseJson {

    private static Gson sGson;

    static {
        sGson = new Gson();
    }

    /**
     * serverInfo : {"result":"ok"}
     */

    private Object serverInfo;

    public Object getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(Object serverInfo) {
        this.serverInfo = serverInfo;
    }

    public static class ServerInfoBean {

        /**
         * result : ok
         */

        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public static class Builder {

            public static String toJson(String result) {
                ServerInfoBean serverInfoBean = new ServerInfoBean();
                serverInfoBean.setResult(result);
                return sGson.toJson(serverInfoBean);
            }

        }

    }

    public static class Builder {

        public static String toJson(String result) {
            ResponseJson responseJson = new ResponseJson();
            ResponseJson.ServerInfoBean serverInfoBean = new ResponseJson.ServerInfoBean();
            serverInfoBean.setResult(result);
            responseJson.setServerInfo(serverInfoBean);
            return sGson.toJson(responseJson);
        }

        public static ResponseJson toBean(String result) {
            ResponseJson responseJson = new ResponseJson();
            ResponseJson.ServerInfoBean serverInfoBean = new ResponseJson.ServerInfoBean();
            serverInfoBean.setResult(result);
            responseJson.setServerInfo(serverInfoBean);
            return responseJson;
        }

        public static <T> String toJson(T t) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setServerInfo(t);
            return sGson.toJson(responseJson);
        }

        public static <T> ResponseJson toBean(T t) {
            ResponseJson responseJson = new ResponseJson();
            responseJson.setServerInfo(t);
            return responseJson;
        }

    }

}
