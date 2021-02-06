package com.example;

import com.example.dao.GoodsDao;
import com.example.dao.SaleDao;
import com.example.domain.Sale;
import com.example.service.BuyGoodsService;
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

    @Test
    public void testService() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        BuyGoodsService buyGoodsService = (BuyGoodsService) ctx.getBean(BuyGoodsService.class);
        buyGoodsService.buy(1001, 10000);
    }

    /**
     * 用来测试是否只有直接在Junit里面使用dao修改数据库不会自动提交
     * <p>
     * 结果：
     * 不是这样的
     */
    @Test
    public void testWeatherDiffInJunit() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");
        SaleDao saleDao = ctx.getBean(SaleDao.class);
        Sale sale = new Sale();
        sale.setGid(1001);
        sale.setNums(2);
        int i = saleDao.insertSale(sale);
    }
}
