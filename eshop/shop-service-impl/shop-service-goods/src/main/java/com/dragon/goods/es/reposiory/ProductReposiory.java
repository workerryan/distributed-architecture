package com.dragon.goods.es.reposiory;

import com.dragon.goods.es.entity.ProductEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ProductReposiory extends ElasticsearchRepository<ProductEntity, Long> {

}
 