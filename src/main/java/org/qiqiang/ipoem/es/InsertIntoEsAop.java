package org.qiqiang.ipoem.es;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.qiqiang.ipoem.poem.PoemDetails;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
@Aspect
public class InsertIntoEsAop {

    private final RestHighLevelClient restHighLevelClient;

    public InsertIntoEsAop(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }


    @Pointcut(value = "@annotation(InsertIntoEs)")
    public void aop() {

    }

    @AfterReturning(pointcut = "aop()", returning = "object")
    public void after(Object object) {
        try {
            if (object instanceof ESDocument) {
                PoemDetails poemDetails = (PoemDetails) object;
                UpdateRequest updateRequest = new UpdateRequest("poem", "doc", poemDetails.getId())
                        .upsert(JSONObject.toJSONString(poemDetails), XContentType.JSON);
                restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
