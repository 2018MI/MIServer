package org.chengpx.mi.domain.trafficlight;

/**
 * 交通灯
 * <p>
 * create at 2018/4/18 10:51 by chengpx
 */
public class TrafficLightBean {

    /**
     * 红绿灯编号: 1-7, 因每个路口红绿灯设置相同，此处的 ID 为路口 ID
     */
    private String TrafficLightId;
    private String Status;
    /**
     * 是否应该工作
     */
    private Boolean shouldRun;

    public String getTrafficLightId() {
        return TrafficLightId == null ? "" : TrafficLightId;
    }

    public void setTrafficLightId(String trafficLightId) {
        TrafficLightId = trafficLightId;
    }

    public String getStatus() {
        return Status == null ? "" : Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Boolean getShouldRun() {
        return shouldRun;
    }

    public void setShouldRun(Boolean shouldRun) {
        this.shouldRun = shouldRun;
    }

    @Override
    public String toString() {
        return "TrafficLightBean{" +
                "TrafficLightId='" + TrafficLightId + '\'' +
                ", Status='" + Status + '\'' +
                ", shouldRun=" + shouldRun +
                '}';
    }

    /**
     * 交通灯当前状态灯类型
     */
    public enum StatusEnum {

        /**
         * 红灯
         */
        RED("Red"),
        /**
         * 黄灯
         */
        YELLOW("Yellow"),
        /**
         * 绿灯
         */
        GREEN("Green");

        private final String mValue;

        StatusEnum(String value) {
            mValue = value;
        }

        public String getValue() {
            return mValue == null ? "" : mValue;
        }

    }

}
