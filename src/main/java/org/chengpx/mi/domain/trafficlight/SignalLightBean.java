package org.chengpx.mi.domain.trafficlight;

/**
 * 红绿灯信号灯
 * <p>
 * create at 2018/4/18 10:57 by chengpx
 */
public class SignalLightBean {

    private Integer id;
    /**
     * 信号灯所属红绿灯
     */
    private TrafficLightBean trafficLightBean;
    /**
     * 红绿灯的时间，采用倒数形式显示
     */
    private Integer Time;
    /**
     * 信号灯类型:
     * @see TrafficLightBean.StatusEnum
     */
    private String type;

    public TrafficLightBean getTrafficLightBean() {
        return trafficLightBean;
    }

    public void setTrafficLightBean(TrafficLightBean trafficLightBean) {
        this.trafficLightBean = trafficLightBean;
    }

    public Integer getTime() {
        return Time;
    }

    public void setTime(Integer time) {
        Time = time;
    }

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SignalLightBean{" +
                "id=" + id +
                ", trafficLightBean=" + trafficLightBean +
                ", Time=" + Time +
                ", type='" + type + '\'' +
                '}';
    }

}
