package com.dragon.goods.api.service.impl;

import java.util.List;
import java.util.Random;

import com.dragon.goods.es.entity.ProductEntity;
import com.dragon.goods.es.reposiory.ProductReposiory;
import com.shop.common.core.base.api.BaseApiService;
import com.shop.common.core.base.entity.BaseResponse;
import com.shop.goods.ProductSearchService;
import com.shop.product.dto.ProductDto;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RestController;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@RestController
public class ProductSearchServiceImpl extends BaseApiService<List<ProductDto>> implements ProductSearchService {

	private Random random = new Random();

	@Autowired
	private ProductReposiory productReposiory;

	@Override
	public BaseResponse<List<ProductDto>> search(String name,
												 @PageableDefault(page = 0, value = 10) Pageable pageable) {

		// int i = 1 / 0;
		String user = null;
		System.out.println(user.getBytes());
		// 1.拼接查询条件
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		// 2.模糊查询 name、 subtitle、detail含有 搜索关键字
		builder.must(QueryBuilders.multiMatchQuery(name, "name", "subtitle", "detail"));
		// 3.调用ES接口查询
		Page<ProductEntity> page = productReposiory.search(builder, pageable);
		// 4.获取集合数据
		List<ProductEntity> content = page.getContent();
		// 5.将entity转换dto
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		List<ProductDto> mapAsList = mapperFactory.getMapperFacade().mapAsList(content, ProductDto.class);
		return setResultSuccess(mapAsList);
	}
	// 现在生产环境中 出现错误日志 如何定位

	@Override
	public BaseResponse save(int id) {
		ProductEntity product = new ProductEntity();
		product.setId(id);
		product.setCategoryId(1);
		product.setName("测试文档-" + random.nextInt());

		productReposiory.index(product);

		return setResultSuccess(Boolean.TRUE);
	}
}
