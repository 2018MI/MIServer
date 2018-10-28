package org.chengpx.mi.util;

/**
 * create at 2018/4/17 17:17 by chengpx
 */
public class Constant {

    /**
     * 运行周期
     */
    public static class Cyscle {

        /**
         * 公交车每次到达下一站所需时长
         */
        public static final Integer BUS_ARRIVAL = 1000 * 60;

        /**
         * 小车奔跑每次时长
         */
        public static final Integer CAR_RUN = 1000 * 60 * 3;

        /**
         * 停车位每次按小时停车最大停车时长
         */
        public static final Integer PARKING_HOUR = 1000 * 10;

        /**
         * 停车位每次按次停车最大停车时长
         */
        public static final Integer PARKING_COUNT = 1000 * 30;

        /**
         * 路灯工作周期
         */
        public static final Integer ROADLIGHT_RUN = 1000 * 10;

        /**
         * 道路状态变化周期
         */
        public static final Integer ROAD_STATUS = 1000 * 10;

        /**
         * 传感器工作周期
         */
        public static final Integer SENSE_RUN = 5000;

    }

}
