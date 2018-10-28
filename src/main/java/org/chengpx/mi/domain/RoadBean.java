package org.chengpx.mi.domain;

/**
 * 道路
 * <p>
 * create at 2018/4/20 10:29 by chengpx
 */
public class RoadBean {

    /**
     * 道路编号: 1 - 12
     */
    private Integer RoadId;
    /**
     * 表示道路拥挤状态，包括(1，2，3，4，5)
     *
     * @see StatusEnum
     */
    private Integer Status;
    /**
     * 道路是否应该通行
     */
    private Boolean shouldRun;

    public Integer getRoadId() {
        return RoadId;
    }

    public void setRoadId(Integer roadId) {
        RoadId = roadId;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
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
        return "RoadBean{" +
                "RoadId=" + RoadId +
                ", Status=" + Status +
                ", shouldRun=" + shouldRun +
                '}';
    }

    /**
     * 道路拥挤状态
     */
    public enum StatusEnum {

        /**
         * 1
         */
        ONE(1),
        /**
         * 2
         */
        TWO(2),
        /**
         * 3
         */
        THREE(3),
        /**
         * 4
         */
        FORTY(4),
        /**
         * 5
         */
        FIVE(5);

        private final Integer mValue;

        StatusEnum(Integer value) {
            mValue = value;
        }

        public Integer getValue() {
            return mValue;
        }

    }

}
