package com.dragon.es.controller;

import com.dragon.es.entity.User;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author wanglei
 * @since 1.0.0
 */
@RestController
public class EsController {
    private final RestHighLevelClient restHighLevelClient;

    public EsController(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @RequestMapping("/addUser")
    public User addUser(@RequestBody User user) throws IOException {
        Map<String, Object> map = new HashMap<>(4);
        map.put("id", user.getId());
        map.put("name", user.getName());
        map.put("age", user.getAge());
        map.put("sex", user.getSex());
        map.put("weigh", user.getWeigh());
        restHighLevelClient.index(new IndexRequest("user").id(user.getId()).source(map), RequestOptions.DEFAULT);
        return user;
    }

    @RequestMapping("/findUser")
    public User findUser(String id) throws IOException{
        SearchResponse search = restHighLevelClient.search(new SearchRequest("user")
                .source(new SearchSourceBuilder().query(QueryBuilders.matchQuery("_id", id))), RequestOptions.DEFAULT);
        SearchHit[] hits = search.getHits().getHits();

        return null;
    }
}
