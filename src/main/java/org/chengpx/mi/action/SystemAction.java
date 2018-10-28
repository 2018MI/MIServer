package org.chengpx.mi.action;


import org.chengpx.framework.controller.springmvc.BaseController;
import org.chengpx.framework.service.BaseService;
import org.chengpx.framework.service.ServiceException;
import org.chengpx.mi.domain.BusBean;
import org.chengpx.mi.domain.BusStationBean;
import org.chengpx.mi.domain.CarBean;
import org.chengpx.mi.domain.RoadBean;
import org.chengpx.mi.domain.RoadLightBean;
import org.chengpx.mi.domain.parking.ParkingBean;
import org.chengpx.mi.domain.trafficlight.TrafficLightBean;
import org.chengpx.mi.service.bus.BusService;
import org.chengpx.mi.service.busstation.BusStationService;
import org.chengpx.mi.service.car.CarService;
import org.chengpx.mi.service.parking.ParkService;
import org.chengpx.mi.service.road.RoadService;
import org.chengpx.mi.service.roadlight.RoadLightService;
import org.chengpx.mi.service.sense.SenseService;
import org.chengpx.mi.service.trafficlight.TrafficLightService;
import org.chengpx.mi.util.ResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create at 2018/4/19 14:55 by chengpx
 */
@RestController
@RequestMapping(value = "/type/jason/action")
public class SystemAction extends BaseController<Object, Object> {

    @Autowired
    private BusService mBusService;
    @Autowired
    private BusStationService mBusStationService;
    @Autowired
    private CarService mCarService;
    @Autowired
    private RoadService mRoadService;
    @Autowired
    private TrafficLightService mTrafficLightService;
    @Autowired
    private SenseService mSenseService;
    @Autowired
    private ParkService mParkService;
    @Autowired
    private RoadLightService mRoadLightService;

    /**
     * 设置自动手动控制模式
     *
     * @return
     */
    @PostMapping(value = "/SetRoadLightControlMode.do")
    public ResponseJson setRoadLightControlMode(@RequestBody RoadLightBean reqRoadLightBean) {
        LOGGER.debug("客户端 Json: " + reqRoadLightBean);
        if (reqRoadLightBean != null) {
            String controlMode = reqRoadLightBean.getControlMode();
            if (controlMode == null || "".equals(controlMode)) {
                return ResponseJson.Builder.toBean(reqRoadLightBean);
            } else {
                try {
                    mRoadLightService.setRoadLightControlMode(controlMode);
                    return ResponseJson.Builder.toBean("ok");
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 手动打开关闭指定路灯
     *
     * @return
     */
    @PostMapping(value = "/SetRoadLightStatusAction.do")
    public ResponseJson setRoadLightStatusAction(@RequestBody RoadLightBean reqRoadLightBean) {
        LOGGER.debug("客户端 Json: " + reqRoadLightBean);
        if (reqRoadLightBean != null) {
            Integer roadLightId = reqRoadLightBean.getRoadLightId();
            String action = reqRoadLightBean.getAction();
            if (roadLightId == null || action == null || "".equals(action)) {
                return ResponseJson.Builder.toBean(reqRoadLightBean);
            } else {
                try {
                    mRoadLightService.setRoadLightStatusAction(roadLightId, action);
                    return ResponseJson.Builder.toBean("ok");
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 查询当前路灯的状态
     *
     * @return
     */
    @PostMapping(value = "/GetRoadLightStatus.do")
    public ResponseJson getRoadLightStatus(@RequestBody RoadLightBean reqRoadLightBean) {
        LOGGER.debug("客户端 Json: " + reqRoadLightBean);
        if (reqRoadLightBean != null) {
            Integer roadLightId = reqRoadLightBean.getRoadLightId();
            if (roadLightId == null) {
                return ResponseJson.Builder.toBean(reqRoadLightBean);
            } else {
                try {
                    RoadLightBean respRoadLightBean = new RoadLightBean();
                    respRoadLightBean.setStatus(mRoadLightService.getRoadLightStatus(roadLightId));
                    return ResponseJson.Builder.toBean(respRoadLightBean);
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 查询当前空闲车位
     *
     * @return
     */
    @PostMapping(value = "/GetParkFree.do")
    public ResponseJson getParkFree() {
        try {
            List<Map<String, Integer>> mapList = mParkService.getParkFree();
            return ResponseJson.Builder.toBean(mapList);
        } catch (ServiceException e) {
            e.printStackTrace();
            return ResponseJson.Builder.toBean(e.getMessage());
        }
    }

    /**
     * 查询当前费率信息
     *
     * @return
     */
    @PostMapping(value = "/GetParkRate.do")
    public ResponseJson getParkRate() {
        try {
            return ResponseJson.Builder.toBean(mParkService.getParkRate());
        } catch (ServiceException e) {
            e.printStackTrace();
            return ResponseJson.Builder.toBean(e.getMessage());
        }
    }

    /**
     * 停车场费率设置
     *
     * @return
     */
    @PostMapping(value = "/SetParkRate.do")
    public ResponseJson setParkRate(@RequestBody ParkingBean reqParkingBean) {
        LOGGER.debug("客户端 Json: " + reqParkingBean);
        if (reqParkingBean != null) {
            String rateType = reqParkingBean.getRateType();
            Integer money = reqParkingBean.getMoney();
            if (rateType == null || "".equals(rateType) || money == null) {
                return ResponseJson.Builder.toBean(reqParkingBean);
            } else {
                try {
                    mParkService.setParkRate(rateType, money);
                    return ResponseJson.Builder.toBean("ok");
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 查询公交车的容量
     *
     * @return
     */
    @PostMapping(value = "/GetBusCapacity.do")
    public ResponseJson getBusCapacity(@RequestBody BusBean reqBusBean) {
        LOGGER.debug("客户端 Json: " + reqBusBean);
        if (reqBusBean != null) {
            Integer busId = reqBusBean.getBusId();
            if (busId == null) {
                return ResponseJson.Builder.toBean(reqBusBean);
            } else {
                try {
                    BusBean respBusBean = new BusBean();
                    respBusBean.setBusCapacity(mBusService.getBusCapacity(busId));
                    return ResponseJson.Builder.toBean(respBusBean);
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 站台信息查询
     *
     * @return
     */
    @PostMapping(value = "/GetBusstationInfo.do")
    public ResponseJson getBusstationInfo(@RequestBody BusStationBean reqBusStationBean) {
        LOGGER.debug("客户端 Json: " + reqBusStationBean);
        if (reqBusStationBean != null) {
            Integer busStationID = reqBusStationBean.getBusStationID();
            if (busStationID == null) {
                return ResponseJson.Builder.toBean(reqBusStationBean);
            } else {
                try {
                    return ResponseJson.Builder.toBean(mBusStationService.getBusstationInfo(busStationID));
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 查询小车当前速度
     *
     * @return
     */
    @PostMapping(value = "/GetCarSpeed.do")
    public ResponseJson getCarSpeed(@RequestBody CarBean reqCarBean) {
        LOGGER.debug("客户端 Json: " + reqCarBean);
        if (reqCarBean != null) {
            Integer carId = reqCarBean.getCarId();
            if (carId == null) {
                return ResponseJson.Builder.toBean(reqCarBean);
            } else {
                try {
                    CarBean respCarBean = new CarBean();
                    respCarBean.setCarSpeed(mCarService.getCarSpeed(carId));
                    return ResponseJson.Builder.toBean(respCarBean);
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 充值小车账户
     *
     * @return
     */
    @PostMapping(value = "/SetCarAccountRecharge.do")
    public ResponseJson setCarAccountRecharge(@RequestBody CarBean reqCarBean) {
        LOGGER.debug("客户端 Json: " + reqCarBean);
        if (reqCarBean != null) {
            if (reqCarBean.getCarId() == null || reqCarBean.getMoney() == null) {
                return ResponseJson.Builder.toBean(reqCarBean);
            } else {
                try {
                    mCarService.setCarAccountRecharge(reqCarBean.getCarId(), reqCarBean.getMoney());
                    return ResponseJson.Builder.toBean("ok");
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 查询小车账户余额
     *
     * @return
     */
    @PostMapping(value = "/GetCarAccountBalance.do")
    public ResponseJson getCarAccountBalance(@RequestBody CarBean reqCarBean) {
        LOGGER.debug("客户端 Json: " + reqCarBean);
        if (reqCarBean != null) {
            if (reqCarBean.getCarId() == null) {
                return ResponseJson.Builder.toBean(reqCarBean);
            } else {
                try {
                    CarBean respCarBean = new CarBean();
                    respCarBean.setBalance(mCarService.getCarAccountBalance(reqCarBean.getCarId()));
                    return ResponseJson.Builder.toBean(respCarBean);
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 设置小车动作
     *
     * @return
     */
    @PostMapping(value = "/SetCarMove.do")
    public ResponseJson setCarMove(@RequestBody CarBean reqCarBean) {
        LOGGER.debug("客户端 Json: " + reqCarBean);
        if (reqCarBean != null) {
            Integer carId = reqCarBean.getCarId();
            String carAction = reqCarBean.getCarAction();
            if (carId == null || carAction == null || "".equals(carAction)) {
                return ResponseJson.Builder.toBean(reqCarBean);
            } else {
                try {
                    mCarService.setCarMove(carId, carAction);
                    return ResponseJson.Builder.toBean("ok");
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 查询小车动作
     * <p>
     * 客户端 json: {"CarId": 1}
     * 服务端返回 json: {"serverInfo":{"CarAction":"Start"}} Start 启动, Stop 停止
     *
     * @return
     */
    @PostMapping(value = "/GetCarMove.do")
    public ResponseJson getCarMove(@RequestBody CarBean reqCarBean) {
        LOGGER.debug("客户端 Json: " + reqCarBean);
        if (reqCarBean != null) {
            Integer carId = reqCarBean.getCarId();
            if (carId == null) {
                return ResponseJson.Builder.toBean(reqCarBean);
            } else {
                try {
                    CarBean respCarBean = new CarBean();
                    respCarBean.setCarAction(mCarService.getCarMove(carId));
                    return ResponseJson.Builder.toBean(respCarBean);
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 查询道路的拥挤状态
     *
     * @return
     */
    @PostMapping(value = "/GetRoadStatus.do")
    public ResponseJson getRoadStatus(@RequestBody RoadBean reqRoadBean) {
        LOGGER.debug("客户端 Json: " + reqRoadBean);
        if (reqRoadBean != null) {
            Integer roadId = reqRoadBean.getRoadId();
            if (roadId == null) {
                return ResponseJson.Builder.toBean(reqRoadBean);
            } else {
                try {
                    RoadBean respRoadBean = new RoadBean();
                    respRoadBean.setStatus(mRoadService.getRoadStatus(roadId));
                    return ResponseJson.Builder.toBean(respRoadBean);
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 查询光照传感器阀值
     *
     * @return
     */
    @PostMapping(value = "/GetLightSenseValve.do")
    public ResponseJson getLightSenseValve() {
        try {
            return ResponseJson.Builder.toBean(mSenseService.getLightSenseValve());
        } catch (ServiceException e) {
            e.printStackTrace();
            return ResponseJson.Builder.toBean(e.getMessage());
        }
    }

    /**
     * 查询所有传感器的当前值
     *
     * @return
     */
    @PostMapping(value = "/GetAllSense.do")
    public ResponseJson getAllSense() {
        String respJson = null;
        try {
            return ResponseJson.Builder.toBean(mSenseService.getAllSense());
        } catch (ServiceException e) {
            e.printStackTrace();
            return ResponseJson.Builder.toBean(e.getMessage());
        }
    }

    /**
     * 查询单个传感器的当前值
     *
     * @return
     */
    @PostMapping(value = "/GetSenseByName.do")
    public ResponseJson getSenseByName(@RequestBody Map reqMap) {
        LOGGER.debug("客户端 Json: " + reqMap);
        if (reqMap != null) {
            Object senseName = reqMap.get("SenseName");
            if (senseName == null) {
                return ResponseJson.Builder.toBean(reqMap);
            } else {
                String sSenseName = obj2Str(senseName);
                HashMap<String, Integer> respMap = new HashMap<>();
                try {
                    respMap.put(sSenseName, mSenseService.getSenseByName(sSenseName));
                    return ResponseJson.Builder.toBean(respMap);
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 查询红绿灯的配置信息
     *
     * @return
     */
    @PostMapping(value = "/GetTrafficLightConfigAction.do")
    public ResponseJson getTrafficLightConfigAction(@RequestBody TrafficLightBean reqTrafficLightBean) {
        LOGGER.debug("客户端 Json: " + reqTrafficLightBean);
        if (reqTrafficLightBean != null) {
            String trafficLightId = reqTrafficLightBean.getTrafficLightId();
            if (trafficLightId == null || "".equals(trafficLightId)) {
                return ResponseJson.Builder.toBean(reqTrafficLightBean);
            } else {
                try {
                    return ResponseJson.Builder.toBean(mTrafficLightService.getTrafficLightConfigAction(trafficLightId));
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 设置红绿灯的配置信息
     *
     * @return
     */
    @PostMapping(value = "/SetTrafficLightConfig.do")
    public ResponseJson setTrafficLightConfig(@RequestBody Map reqMap) {
        LOGGER.debug("客户端 Json: " + reqMap);
        if (reqMap != null) {
            Object trafficLightId = reqMap.get("TrafficLightId");
            Object redTime = reqMap.get("RedTime");
            Object greenTime = reqMap.get("GreenTime");
            Object yellowTime = reqMap.get("YellowTime");
            if (trafficLightId == null || redTime == null || greenTime == null || yellowTime == null) {
                return ResponseJson.Builder.toBean(reqMap);
            } else {
                try {
                    mTrafficLightService.setTrafficLightConfig(
                            obj2Str(trafficLightId),
                            obj2Int(redTime),
                            obj2Int(yellowTime),
                            obj2Int(greenTime)
                    );
                    return ResponseJson.Builder.toBean("ok");
                } catch (ClassCastException | ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 查询红绿灯的当前状态
     *
     * @return
     */
    @PostMapping(value = "/GetTrafficLightNowStatus.do")
    public ResponseJson getTrafficLightNowStatus(@RequestBody TrafficLightBean reqTrafficLightBean) {
        LOGGER.debug("客户端 Json: " + reqTrafficLightBean);
        if (reqTrafficLightBean != null) {
            String trafficLightId = reqTrafficLightBean.getTrafficLightId();
            if (trafficLightId == null || "".equals(trafficLightId)) {
                return ResponseJson.Builder.toBean(reqTrafficLightBean);
            } else {
                try {
                    return ResponseJson.Builder.toBean(mTrafficLightService.getTrafficLightNowStatus(trafficLightId));
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }

    /**
     * 设置红绿灯的当前状态
     *
     * @return
     */
    @PostMapping(value = "/SetTrafficLightNowStatus.do")
    public ResponseJson setTrafficLightNowStatus(@RequestBody Map reqMap) {
        LOGGER.debug("客户端 Json: " + reqMap);
        if (reqMap != null) {
            Object trafficLightId = reqMap.get("TrafficLightId");
            Object status = reqMap.get("Status");
            Object time = reqMap.get("Time");
            if (trafficLightId == null || status == null || time == null) {
                return ResponseJson.Builder.toBean(reqMap);
            } else {
                try {
                    mTrafficLightService.setTrafficLightNowStatus(
                            obj2Str(trafficLightId),
                            obj2Str(status),
                            obj2Int(time)
                    );
                    return ResponseJson.Builder.toBean("ok");
                } catch (ServiceException e) {
                    e.printStackTrace();
                    return ResponseJson.Builder.toBean(e.getMessage());
                }
            }
        }
        return null;
    }


    @Override
    public BaseService<Object, Object> getBaseService() {
        return null;
    }

    private int obj2Int(Object obj) throws ServiceException {
        int i;
        if (obj instanceof String) {
            String str = (String) obj;
            if ("".equals(str)) {
                throw new ServiceException(obj + " is \"\"");
            }
            i = Integer.parseInt(str);
        } else if (obj instanceof Double) {
            i = (int) (double) obj;
        } else {
            i = (int) obj;
        }
        return i;
    }

    private String obj2Str(Object obj) {
        String str;
        if (obj instanceof Integer) {
            str = ((int) obj) + "";
        } else if (obj instanceof Double) {
            str = ((int) (double) obj) + "";
        } else {
            str = (String) obj;
        }
        return str;
    }

}
