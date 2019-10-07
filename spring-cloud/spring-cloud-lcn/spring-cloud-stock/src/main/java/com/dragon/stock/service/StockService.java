package com.dragon.stock.service;

import com.codingapi.tx.annotation.TxTransaction;
import com.dragon.stock.entity.Stock;
import com.dragon.stock.mapper.StockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wanglei
 * @since 1.0.0
 */
@Service
public class StockService {
    private static Logger logger = LoggerFactory.getLogger(StockService.class);

    private final StockMapper stockMapper;

    public StockService(StockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }

    public boolean insert(Integer skuId){
        Stock stock = new Stock();
        stock.setSkuId(skuId);
        stock.setStock(100);
        int insert = stockMapper.insert(stock);
        return insert == 1;
    }

    @TxTransaction
    @Transactional(rollbackFor = Exception.class)
    public boolean decrStock(Integer skuId){
        logger.info("扣减 " + skuId + " 的库存") ;
        Integer stock = stockMapper.getStockBySkuId(skuId);
        if(stock == null || stock < 1){
            logger.error("库存不足");
            return false;
        }

        stock--;

        Integer result = stockMapper.updateStock(skuId, stock);

        return result == 1;
    }
}
