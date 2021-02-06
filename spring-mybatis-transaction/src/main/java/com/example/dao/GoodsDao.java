package com.example.dao;

import com.example.domain.Goods;

public interface GoodsDao {
    int updateGoods(Goods goods);

    Goods selectGoods(Integer goodsId);
}
