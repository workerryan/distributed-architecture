package com.dragon.stock.controller;

import com.dragon.stock.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class StockController {
    private final Logger logger = LoggerFactory.getLogger(StockController.class)
;    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping("/insert")
    public boolean insert(Integer skuId){
        return stockService.insert(skuId);
    }


    @RequestMapping("/descStock")
    public boolean descStock(Integer skuId){
        logger.info("descStock " + skuId);
        return stockService.decrStock(skuId);
    }
}
