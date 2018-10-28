package org.chengpx.mi;


import org.chengpx.mi.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 传感器系统
 * <p>
 * create at 2018/4/19 8:31 by chengpx
 */
@Component
public class SenseSystem {

    private static Logger sLogger = LoggerFactory.getLogger(SenseSystem.class);
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 传感器实时数据集合
     */
    private Map<String, Integer> mDataMap;
    /**
     * 所有传感器集合
     */
    private Map<String, BaseSense> mBaseSenseMap;

    public Map<String, Integer> getDataMap() {
        mDataMap.put("pm2.5", mBaseSenseMap.get("pm2.5").getValue());
        mDataMap.put("co2", mBaseSenseMap.get("co2").getValue());
        mDataMap.put("LightIntensity", mBaseSenseMap.get("LightIntensity").getValue());
        mDataMap.put("humidity", mBaseSenseMap.get("humidity").getValue());
        mDataMap.put("temperature", mBaseSenseMap.get("temperature").getValue());
        return mDataMap;
    }

    /**
     * 启动传感器系统
     */
    public void start() {
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("SenseSystem start");
        }
        mDataMap = Collections.synchronizedMap(new HashMap<>());
        mBaseSenseMap = Collections.synchronizedMap(new HashMap<>());
        mBaseSenseMap.put("pm2.5", new Pm2_5());
        mBaseSenseMap.put("co2", new Co2());
        mBaseSenseMap.put("LightIntensity", new LightIntensity());
        mBaseSenseMap.put("humidity", new Humidity());
        mBaseSenseMap.put("temperature", new Temperature());
    }

    /**
     * 停止传感系统
     */
    public void stop() {
        for (Map.Entry<String, BaseSense> senseEntry : mBaseSenseMap.entrySet()) {
            senseEntry.getValue().setShouldRun(false);
        }
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("SenseSystem stop");
        }
    }

    public Map<String, BaseSense> getBaseSenseMap() {
        if (mBaseSenseMap == null) {
            return new HashMap<>();
        }
        return mBaseSenseMap;
    }

    /**
     * pm2.5
     * <p>
     * 0 至 50 为一级优，51 至 100 为二级良，101 至 150 为三级轻度污染，151 至 200 为四级中度污染，201 至 300 为五级重度污染和 300 以上为六级严重污染
     */
    private class Pm2_5 extends BaseSense {

        public Pm2_5() {
            startRange = 0;
            endRange = 300;
        }

        @Override
        public String toString() {
            return "Pm2_5{" +
                    "value=" + value +
                    ", endRange=" + endRange +
                    ", startRange=" + startRange +
                    ", shouldRun=" + shouldRun +
                    '}';
        }

    }

    /**
     * co2
     * <p>
     * 350～450ppm：同一般室外环境
     * ·350～1000ppm：空气清新，呼吸顺畅。
     * ·>10,00ppm：感觉空气浑浊，并开始觉得昏昏欲睡。
     * ·>20,00ppm：感觉头痛、嗜睡、呆滞、注意力无法集中、心跳加速、轻度恶心。
     * ·>50,00ppm：可能导致严重缺氧，造成永久性脑损伤、昏迷、甚至死亡
     */
    private class Co2 extends BaseSense {

        public Co2() {
            startRange = 350;
            endRange = 7000;
        }

        @Override
        public String toString() {
            return "Co2{" +
                    "value=" + value +
                    ", endRange=" + endRange +
                    ", startRange=" + startRange +
                    ", shouldRun=" + shouldRun +
                    '}';
        }

    }

    /**
     * 光强度
     * <p>
     * 照度标准值按 0.5、1、3、5、10、15、20、30、50、75、100、150、 200、300、500、750、1000、1500、2000、3000 、5000lx 分级
     */
    public class LightIntensity extends BaseSense {

        /**
         * 阈值下限
         * 夏季在阳光直接照射下，光照强度可达6万～10万lx，没有太阳的室外0.1万～1万lx，夏天明朗的室内100～550lx，夜间满月下为0.2lx
         */
        private Integer Down;
        /**
         * 阈值上限
         */
        private Integer Up;

        public LightIntensity() {
            startRange = 1;
            endRange = 5000;
            Down = 100;
            Up = 1000;
        }

        public Integer getDown() {
            return Down;
        }

        public void setDown(Integer down) {
            Down = down;
        }

        public Integer getUp() {
            return Up;
        }

        public void setUp(Integer up) {
            Up = up;
        }

        @Override
        public String toString() {
            return "LightIntensity{" +
                    "Down=" + Down +
                    ", Up=" + Up +
                    ", value=" + value +
                    ", endRange=" + endRange +
                    ", startRange=" + startRange +
                    ", shouldRun=" + shouldRun +
                    '}';
        }

    }

    /**
     * 湿度
     */
    private class Humidity extends BaseSense {

        public Humidity() {
            startRange = 20;
            endRange = 80;
        }

        @Override
        public String toString() {
            return "Humidity{" +
                    "value=" + value +
                    ", endRange=" + endRange +
                    ", startRange=" + startRange +
                    ", shouldRun=" + shouldRun +
                    '}';
        }

    }

    /**
     * 温度
     */
    private class Temperature extends BaseSense {

        public Temperature() {
            startRange = 0;
            endRange = 37;
        }

        @Override
        public String toString() {
            return "Temperature{" +
                    "value=" + value +
                    ", endRange=" + endRange +
                    ", startRange=" + startRange +
                    ", shouldRun=" + shouldRun +
                    '}';
        }

    }

    public class BaseSense {

        protected int value;
        protected int endRange;
        protected int startRange;
        protected boolean shouldRun;
        protected Logger mLogger = LoggerFactory.getLogger(getClass());

        public BaseSense() {
            threadPoolTaskExecutor.execute(new Runnable() {

                private Random mRandom;

                @Override
                public void run() {
                    mRandom = new Random();
                    shouldRun = true;
                    if (mLogger.isDebugEnabled()) {
                        mLogger.debug(BaseSense.this.toString() + " start");
                    }
                    while (shouldRun) {
                        try {
                            // 5000
                            Thread.sleep(mRandom.nextInt(Constant.Cyscle.SENSE_RUN));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        setValue(startRange + mRandom.nextInt(endRange - startRange));
                        if (mLogger.isDebugEnabled()) {
                            mLogger.debug(BaseSense.this.toString());
                        }
                    }
                    if (mLogger.isDebugEnabled()) {
                        mLogger.debug(BaseSense.this.toString() + " stop");
                    }
                }

            });
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public boolean isShouldRun() {
            return shouldRun;
        }

        public void setShouldRun(boolean shouldRun) {
            this.shouldRun = shouldRun;
        }

        @Override
        public String toString() {
            return "BaseSense{" +
                    "value=" + value +
                    ", endRange=" + endRange +
                    ", startRange=" + startRange +
                    ", shouldRun=" + shouldRun +
                    '}';
        }

    }

}
