package org.qiqiang.ipoem.spider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.qiqiang.ipoem.poem.IPoemDetailsService;
import org.qiqiang.ipoem.poem.PoemDetails;
import org.qiqiang.ipoem.utils.IdUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qiqiang
 * @date 2019-07-10
 */
@Slf4j
@Component
public class SpiderModelPipeline implements PageModelPipeline<PoemSpiderDetails> {
    private final IPoemDetailsService poemDetailsService;

    public SpiderModelPipeline(IPoemDetailsService poemDetailsService) {
        this.poemDetailsService = poemDetailsService;
    }

    @Override
    public void process(PoemSpiderDetails poemSpiderDetails, Task task) {
        PoemDetails poemDetails = new PoemDetails();
        poemDetails.setId(IdUtils.uuid());
        poemDetails.setDynasty(stripDynasty(poemSpiderDetails.getDynasty()));
        poemDetails.setAuthor(StringUtils.strip(poemSpiderDetails.getAuthor()));
        poemDetails.setTitle(StringUtils.strip(poemSpiderDetails.getTitle()));
        String content = poemSpiderDetails.getContent();
        poemDetails.setContent(content);
        poemDetailsService.insertOne(poemDetails);


    }

    private String stripDynasty(String dynasty) {
        return StringUtils.strip(dynasty.replace("[", "").replace("]", ""));
    }
}
