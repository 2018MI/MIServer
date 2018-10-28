package org.chengpx.mi.dao.bus;

import org.chengpx.mi.cfg.MIServerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author chengpx
 * @date 2018/10/28 14:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MIServerConfig.class)// springboot 程序入口类
public class BusDaoTest {

    @Autowired
    private BusDao busDao;

    @Test
    public void select() {
        busDao.select();
    }

}