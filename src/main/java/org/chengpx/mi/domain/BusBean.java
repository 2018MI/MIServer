package org.chengpx.mi.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 公交巴士
 * <p>
 * create at 2018/4/19 14:53 by chengpx
 */
public class BusBean {

    /**
     * 最大承载量
     */
    public final static Integer MAX_NUM = 120;

    private Integer BusId;
    /**
     * 当前公交车人数
     */
    private Integer BusCapacity;
    /**
     * 公交车是否应该运行
     */
    private Boolean shouldRun;
    /**
     * 下一个公交站点
     */
    private BusStationBean nextBusStation;
    /**
     * 公交路线图
     */
    private List<BusStationBean> busRoute;
    /**
     * 上一个公交站点
     */
    private BusStationBean preBusStation;

    public Integer getBusId() {
        return BusId;
    }

    public void setBusId(Integer busId) {
        BusId = busId;
    }

    public Integer getBusCapacity() {
        return BusCapacity;
    }

    public void setBusCapacity(Integer busCapacity) {
        BusCapacity = busCapacity;
    }

    public Boolean getShouldRun() {
        return shouldRun;
    }

    public void setShouldRun(Boolean shouldRun) {
        this.shouldRun = shouldRun;
    }

    public BusStationBean getNextBusStation() {
        return nextBusStation;
    }

    public void setNextBusStation(BusStationBean nextBusStation) {
        this.nextBusStation = nextBusStation;
    }

    public List<BusStationBean> getBusRoute() {
        if (busRoute == null) {
            return new ArrayList<>();
        }
        return busRoute;
    }

    public void setBusRoute(List<BusStationBean> busRoute) {
        this.busRoute = busRoute;
    }

    public BusStationBean getPreBusStation() {
        return preBusStation;
    }

    public void setPreBusStation(BusStationBean preBusStation) {
        this.preBusStation = preBusStation;
    }

    @Override
    public String toString() {
        return "BusBean{" +
                "BusId=" + BusId +
                ", BusCapacity=" + BusCapacity +
                ", shouldRun=" + shouldRun +
                ", nextBusStation=" + nextBusStation +
                ", busRoute=" + busRoute +
                ", preBusStation=" + preBusStation +
                '}';
    }

}
