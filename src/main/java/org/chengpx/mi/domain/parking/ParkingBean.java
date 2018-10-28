package org.chengpx.mi.domain.parking;

/**
 * 停车场
 * <p>
 * create at 2018/4/20 13:06 by chengpx
 */
public class ParkingBean {

    private Integer id;
    /**
     * Hour:按小时计费, Count：按次计费
     */
    private String RateType;
    /**
     * 费率
     */
    private Integer Money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRateType() {
        return RateType == null ? "" : RateType;
    }

    public void setRateType(String rateType) {
        RateType = rateType;
    }

    public Integer getMoney() {
        return Money;
    }

    public void setMoney(Integer money) {
        Money = money;
    }

    @Override
    public String toString() {
        return "ParkingBean{" +
                "id=" + id +
                ", RateType='" + RateType + '\'' +
                ", Money=" + Money +
                '}';
    }

    /**
     * 费率类型
     */
    public enum RateTypeEnum {

        /**
         * Hour:按小时计费,
         */
        HOUR("Hour"),
        /**
         * Count：按次计费
         */
        COUNT("Count");

        private final String mValue;

        RateTypeEnum(String value) {
            mValue = value;
        }

        public String getValue() {
            return mValue == null ? "" : mValue;
        }
    }

}
