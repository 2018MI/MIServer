package org.chengpx.mi.domain;

/**
 * 公交站台
 * <p>
 * create at 2018/4/19 17:26 by chengpx
 */
public class BusStationBean {

    /**
     * 公交站 id
     */
    private Integer BusStationID;
    /**
     * 路程
     */
    private Integer Distance;

    public Integer getBusStationID() {
        return BusStationID;
    }

    public void setBusStationID(Integer busStationID) {
        BusStationID = busStationID;
    }

    public Integer getDistance() {
        return Distance;
    }

    public void setDistance(Integer distance) {
        Distance = distance;
    }

    @Override
    public String toString() {
        return "BusStationBean{" +
                "BusStationID=" + BusStationID +
                ", Distance=" + Distance +
                '}';
    }

}
