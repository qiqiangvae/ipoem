package org.qiqiang.chinesepoetry;

import com.alibaba.fastjson.JSON;
import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author qiqiang
 * @date 2020-10-12 5:02 下午
 */
public class EsClientTest {
    private RestHighLevelClient client = null;

    @Before
    public void prepare() {
        // 创建Client连接对象
        String[] ips = {"mycloud:9200"};
        HttpHost[] httpHosts = new HttpHost[ips.length];
        for (int i = 0; i < ips.length; i++) {
            httpHosts[i] = HttpHost.create(ips[i]);
        }
        RestClientBuilder builder = RestClient.builder(httpHosts);
        client = new RestHighLevelClient(builder);
    }

    @Test
    public void searchTest() throws IOException {
        SearchRequest request = new SearchRequest("chinese_poetry_shi_new");
        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // SearchRequest 搜索请求
        // SearchSourceBuilder 条件构造
        // HighlightBuilder 构建高亮
        // TermQueryBuilder 精确查询
        // MatchAllQueryBuilder .....
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "李白墓");
        sourceBuilder.query(matchQueryBuilder)
                .timeout(new TimeValue(60, TimeUnit.SECONDS));

        request.source(sourceBuilder);

        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        String text = JSON.toJSONString(searchResponse.getHits(), true);
        System.out.println(ZhConverterUtil.toSimple(text));
        System.out.println("===================================");
        for (SearchHit documentFields : searchResponse.getHits()) {
            System.out.println(ZhConverterUtil.toSimple(JSON.toJSONString(documentFields.getSourceAsMap())));
        }

    }
}