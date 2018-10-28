package org.chengpx.mi.listener;


import org.chengpx.mi.BusSystem;
import org.chengpx.mi.CarSystem;
import org.chengpx.mi.RoadLightSystem;
import org.chengpx.mi.RoadSystem;
import org.chengpx.mi.SenseSystem;
import org.chengpx.mi.TrafficLightSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 交通系统
 * <p>
 * create at 2018/4/18 14:45 by chengpx
 */
@Component
public class TrafficSystemListener {

    private static Logger sLogger = LoggerFactory.getLogger(TrafficSystemListener.class);
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private RoadSystem roadSystem;
    @Autowired
    private SenseSystem senseSystem;
    @Autowired
    private TrafficLightSystem trafficLightSystem;
    @Autowired
    private BusSystem busSystem;
    @Autowired
    private CarSystem carSystem;
    @Autowired
    private RoadLightSystem roadLightSystem;

    @EventListener
    public void start(ContextRefreshedEvent contextRefreshedEvent) {
        //        ApplicationContext webApplicationContext = contextRefreshedEvent.getApplicationContext();
        //        threadPoolTaskExecutor = webApplicationContext.getBean(ThreadPoolTaskExecutor.class);
        //        roadSystem = webApplicationContext.getBean(RoadSystem.class);
        //        senseSystem = webApplicationContext.getBean(SenseSystem.class);
        //        trafficLightSystem = webApplicationContext.getBean(TrafficLightSystem.class);
        //        busSystem = webApplicationContext.getBean(BusSystem.class);
        //        carSystem = webApplicationContext.getBean(CarSystem.class);
        //        roadLightSystem = webApplicationContext.getBean(RoadLightSystem.class);

        roadSystem.start();
        senseSystem.start();
        trafficLightSystem.start();
        busSystem.start();
        carSystem.start();
        roadLightSystem.start();
    }

    @EventListener
    public void stop(ContextClosedEvent contextClosedEvent) {
        roadSystem.stop();
        senseSystem.stop();
        trafficLightSystem.stop();
        busSystem.stop();
        carSystem.stop();
        roadLightSystem.stop();
        threadPoolTaskExecutor.shutdown();
    }


    //    @Override
    //    public void contextInitialized(ServletContextEvent sce) {
    //        ApplicationContext webApplicationContext = SpringUtils.getWebApplicationContext(sce.getServletContext());
    //        assert webApplicationContext != null;
    //        threadPoolTaskExecutor = webApplicationContext.getBean(ThreadPoolTaskExecutor.class);
    //        roadLightSystem = webApplicationContext.getBean(RoadLightSystem.class);
    //        senseSystem = webApplicationContext.getBean(SenseSystem.class);
    //        trafficLightSystem = webApplicationContext.getBean(TrafficLightSystem.class);
    //        busSystem = webApplicationContext.getBean(BusSystem.class);
    //        carSystem = webApplicationContext.getBean(CarSystem.class);
    //        roadLightSystem = webApplicationContext.getBean(RoadLightSystem.class);
    //
    //        roadSystem.start();
    //        senseSystem.start();
    //        trafficLightSystem.start();
    //        busSystem.start();
    //        carSystem.start();
    //        roadLightSystem.start();
    //    }
    //
    //    @Override
    //    public void contextDestroyed(ServletContextEvent sce) {
    //        roadSystem.stop();
    //        senseSystem.stop();
    //        trafficLightSystem.stop();
    //        busSystem.stop();
    //        carSystem.stop();
    //        roadLightSystem.stop();
    //        threadPoolTaskExecutor.shutdown();
    //    }

}
