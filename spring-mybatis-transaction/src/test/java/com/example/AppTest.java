package com.example;

import com.example.dao.GoodsDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testStart() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        GoodsDao goodsDao = ctx.getBean(GoodsDao.class);
        System.out.println(goodsDao);
    }
}
