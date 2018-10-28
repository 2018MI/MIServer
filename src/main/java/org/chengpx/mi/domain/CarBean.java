package org.chengpx.mi.domain;

/**
 * create at 2018/4/17 13:08 by chengpx
 */
public class CarBean {

    /**
     * 小车编号：1-15
     */
    private Integer CarId;
    /**
     * Start：启动, Stop：停止
     */
    private String CarAction;
    /**
     * 账户余额
     */
    private Integer Balance;
    /**
     * 充值金额
     */
    private Integer Money;
    /**
     * 小车速度
     */
    private Integer CarSpeed;
    /**
     * 小车是否应该运行
     */
    private Boolean shouldRun;

    public Integer getMoney() {
        return Money;
    }

    public void setMoney(Integer money) {
        Money = money;
    }

    public Integer getCarId() {
        return CarId;
    }

    public void setCarId(Integer carId) {
        CarId = carId;
    }

    public String getCarAction() {
        return CarAction == null ? "" : CarAction;
    }

    public void setCarAction(String carAction) {
        CarAction = carAction;
    }

    public Integer getBalance() {
        return Balance;
    }

    public void setBalance(Integer balance) {
        Balance = balance;
    }

    public Integer getCarSpeed() {
        return CarSpeed;
    }

    public void setCarSpeed(Integer carSpeed) {
        CarSpeed = carSpeed;
    }

    public Boolean getShouldRun() {
        return shouldRun;
    }

    public void setShouldRun(Boolean shouldRun) {
        this.shouldRun = shouldRun;
    }

    @Override
    public String toString() {
        return "CarBean{" +
                "CarId=" + CarId +
                ", CarAction='" + CarAction + '\'' +
                ", Balance=" + Balance +
                ", Money=" + Money +
                ", CarSpeed=" + CarSpeed +
                ", shouldRun=" + shouldRun +
                '}';
    }

    /**
     * 小车动作类型
     */
    public enum CarActionEnum {

        /**
         * 启动
         */
        START("Start"),
        /**
         * 关闭
         */
        STOP("Stop");

        private final String mValue;

        CarActionEnum(String value) {
            mValue = value;
        }

        public String getValue() {
            return mValue == null ? "" : mValue;
        }

    }

}
