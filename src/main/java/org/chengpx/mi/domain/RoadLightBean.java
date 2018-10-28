package org.chengpx.mi.domain;

/**
 * 路灯
 * <p>
 * create at 2018/4/21 11:16 by chengpx
 */
public class RoadLightBean {

    /**
     * 路灯 id
     */
    private Integer RoadLightId;
    /**
     * 路灯开关状态
     *
     * @see StatusEnum
     */
    private String Status;
    /**
     * 路灯控制动作
     *
     * @see StatusEnum
     */
    private String Action;
    /**
     * 路灯控制模式
     */
    private String ControlMode;
    /**
     * 路灯是否应该运行
     */
    private Boolean shouldRun;

    public Integer getRoadLightId() {
        return RoadLightId;
    }

    public void setRoadLightId(Integer roadLightId) {
        RoadLightId = roadLightId;
    }

    public String getStatus() {
        return Status == null ? "" : Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAction() {
        return Action == null ? "" : Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getControlMode() {
        return ControlMode == null ? "" : ControlMode;
    }

    public void setControlMode(String controlMode) {
        ControlMode = controlMode;
    }

    public Boolean getShouldRun() {
        return shouldRun;
    }

    public void setShouldRun(Boolean shouldRun) {
        this.shouldRun = shouldRun;
    }

    @Override
    public String toString() {
        return "RoadLightBean{" +
                "RoadLightId=" + RoadLightId +
                ", Status='" + Status + '\'' +
                ", Action='" + Action + '\'' +
                ", ControlMode='" + ControlMode + '\'' +
                ", shouldRun=" + shouldRun +
                '}';
    }

    /**
     * 路灯开启状态
     */
    public enum StatusEnum {

        /**
         * 打开
         */
        OPEN("Close"),
        /**
         * 关闭
         */
        CLOSE("Open");

        private final String mValue;

        StatusEnum(String value) {
            mValue = value;
        }

        public String getValue() {
            return mValue == null ? "" : mValue;
        }

    }

    /**
     * 路灯控制模式
     */
    public enum ControlModeEnum {

        /**
         * 自动模式
         */
        AUTO("Auto"),
        /**
         * 手动模式
         */
        MANUAL("Manual");

        private final String mValue;

        ControlModeEnum(String value) {
            mValue = value;
        }

        public String getValue() {
            return mValue == null ? "" : mValue;
        }

    }

}
