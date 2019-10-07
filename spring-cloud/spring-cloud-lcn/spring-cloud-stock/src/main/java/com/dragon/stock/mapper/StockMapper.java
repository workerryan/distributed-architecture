package com.dragon.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.stock.entity.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author wanglei
 * @since 1.0.0
 */
public interface StockMapper extends BaseMapper<Stock> {

    @Select("select `stock` from `stock` where sku_id = #{skuId}")
    Integer getStockBySkuId(@Param("skuId") int skuId);

    @Update("update  `stock` set `stock` = #{stock} where sku_id = #{skuId}")
    Integer updateStock(@Param("skuId") int skuId, @Param("stock") int stock);
}
