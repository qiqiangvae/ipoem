package org.qiqiang.ipoem.es;

import org.apache.http.HttpHost;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("127.0.0.1", 9200)
        ).setRequestConfigCallback(
                requestConfigBuilder -> requestConfigBuilder
                        //连接超时（默认为1秒）
                        .setConnectTimeout(5000)
                        //套接字超时（默认为30秒）
                        .setSocketTimeout(60000)
        ).setHttpClientConfigCallback(httpClientBuilder -> {
            return httpClientBuilder.setDefaultIOReactorConfig(
                    IOReactorConfig.custom().setIoThreadCount(1).build());//线程数
        });
        return new RestHighLevelClient(restClientBuilder);
    }
}
