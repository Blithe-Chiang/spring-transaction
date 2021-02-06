package com.example.service.impl;

import com.example.dao.GoodsDao;
import com.example.dao.SaleDao;
import com.example.domain.Goods;
import com.example.domain.Sale;
import com.example.exceptions.NotEnoughException;
import com.example.service.BuyGoodsService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Setter
public class BuyGoodsServiceImpl implements BuyGoodsService {
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    SaleDao saleDao;

    //给业务层方法加入事务属性
    @Transactional(propagation = Propagation.REQUIRED,
            rollbackFor = {NotEnoughException.class, NullPointerException.class})
    @Override
    public void buy(Integer goodsId, Integer amount) {
        Sale sale = new Sale();
        sale.setGid(goodsId);
        sale.setNums(amount);
        saleDao.insertSale(sale);
        Goods goods = goodsDao.selectGoods(goodsId);
        if (goods == null) {
            throw new NullPointerException("无此商品");
        }
        if (goods.getAmount() < amount) {
            throw new NotEnoughException("库存不够: 剩余-" + goods.getAmount() + ", 需要-" + amount);
        }
        goods = new Goods();
        goods.setAmount(amount);
        goods.setId(goodsId);
        goodsDao.updateGoods(goods);
    }
}
