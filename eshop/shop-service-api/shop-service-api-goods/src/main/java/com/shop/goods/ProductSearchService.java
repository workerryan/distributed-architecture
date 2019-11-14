package com.shop.goods;

import java.util.List;

import com.shop.common.core.base.entity.BaseResponse;
import com.shop.product.dto.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *  商品搜索服务接口
 */
public interface ProductSearchService {

	@GetMapping("/search")
	BaseResponse<List<ProductDto>> search(String name, @PageableDefault(page = 0, value = 10) Pageable pageable);

	@GetMapping("/save")
	BaseResponse save(int id);
}
