package org.qiqiang.chinesepoetry.poetry.shi.service;

import com.alibaba.fastjson.JSON;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 诗词
 *
 * @author qiqiang
 */
@Service
public class ShiSearchServiceImpl implements ShiSearchService {

    @Autowired
    private RestHighLevelClient shiSearchClient;
    @Value("${poetry.es.index}")
    private String index;


    @Override
    public List<ShiEntity> search(String search) {
        SearchRequest request = new SearchRequest(index);
        List<ShiEntity> list = new ArrayList<>();
        try {
            //构建搜索条件
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            if (search == null) {
                search = "";
            }
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(search, "author", "title", "paragraphs");
            sourceBuilder.query(multiMatchQueryBuilder)
                    .timeout(new TimeValue(60, TimeUnit.SECONDS));
            request.source(sourceBuilder);
            SearchResponse searchResponse = shiSearchClient.search(request, RequestOptions.DEFAULT);
            for (SearchHit documentFields : searchResponse.getHits()) {
                ShiEntity shiEntity = JSON.parseObject(JSON.toJSONString(documentFields.getSourceAsMap()), ShiEntity.class);
                list.add(shiEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
