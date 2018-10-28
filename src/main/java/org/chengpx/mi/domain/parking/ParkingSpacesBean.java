package org.chengpx.mi.domain.parking;

/**
 * 车位
 * <p>
 * create at 2018/4/20 13:09 by chengpx
 */
public class ParkingSpacesBean {

    private Integer id;
    /**
     * 所属停车场
     */
    private ParkingBean parkingBean;
    /**
     * 车位状态
     * @see StatusEnum
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ParkingBean getParkingBean() {
        return parkingBean;
    }

    public void setParkingBean(ParkingBean parkingBean) {
        this.parkingBean = parkingBean;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ParkingSpacesBean{" +
                "id=" + id +
                ", parkingBean=" + parkingBean +
                ", status=" + status +
                '}';
    }

    /**
     * 车位状态
     */
    public enum StatusEnum {

        /**
         * 可用
         */
        AVAILABLE(1),
        /**
         * 不可用
         */
        UNAVAILABLE(0);

        private final Integer mValue;

        StatusEnum(Integer value) {
            mValue = value;
        }

        public Integer getValue() {
            return mValue;
        }

    }

}
