package org.qiqiang.chinesepoetry.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiqiang
 * @date 2020-10-12 6:11 下午
 */
@Configuration
public class EsConfig {

    @Value("${poetry.es.address}")
    private String[] esAddress;

    @Bean
    public RestHighLevelClient shiSearchClient() {
        // 创建Client连接对象
        HttpHost[] httpHosts = new HttpHost[esAddress.length];
        for (int i = 0; i < esAddress.length; i++) {
            httpHosts[i] = HttpHost.create(esAddress[i]);
        }
        RestClientBuilder builder = RestClient.builder(httpHosts);
        return new RestHighLevelClient(builder);
    }
}