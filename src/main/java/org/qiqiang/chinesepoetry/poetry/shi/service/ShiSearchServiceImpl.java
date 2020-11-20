package org.qiqiang.chinesepoetry.poetry.shi.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.qiqiang.chinesepoetry.common.PageHolder;
import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;
import org.qiqiang.chinesepoetry.poetry.shi.vo.ShiSearchDTO;
import org.qiqiang.chinesepoetry.vo.PageRequest;
import org.qiqiang.chinesepoetry.vo.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
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
    public PageHolder<ShiEntity> search(ShiSearchDTO shiSearchDTO, PageRequest pageRequest) {
        SearchRequest request = new SearchRequest(index);
        List<ShiEntity> list = new ArrayList<>();
        PageResponse pageResponse = null;
        try {
            //构建搜索条件
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            MultiMatchQueryBuilder multiMatchQueryBuilder;
            if (StringUtils.isNotBlank(shiSearchDTO.getSearch())) {
                String[] searchTargets = new String[]{"title", "author", "paragraphs"};
                if (StringUtils.isNotBlank(shiSearchDTO.getSearchTarget())) {
                    searchTargets = StringUtils.split(shiSearchDTO.getSearchTarget(), ",");
                }
                multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(shiSearchDTO.getSearch(), searchTargets);
                sourceBuilder.query(multiMatchQueryBuilder);
            }
            sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
            if (Boolean.TRUE.equals(pageRequest.getNeed())) {
                sourceBuilder.from(pageRequest.from());
                sourceBuilder.size(pageRequest.getPageSize());
            }
            request.source(sourceBuilder);
            SearchResponse searchResponse = shiSearchClient.search(request, RequestOptions.DEFAULT);
            pageResponse = new PageResponse(pageRequest, searchResponse.getHits().getTotalHits().value);
            for (SearchHit documentFields : searchResponse.getHits()) {
                ShiEntity shiEntity = JSON.parseObject(JSON.toJSONString(documentFields.getSourceAsMap()), ShiEntity.class);
                list.add(shiEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new PageHolder<>(pageResponse, list);
    }
}
