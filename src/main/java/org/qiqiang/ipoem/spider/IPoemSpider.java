package org.qiqiang.ipoem.spider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

/**
 * 爬虫启动器
 *
 * @author qiqiang
 * @date 2019-07-10
 */
@Component
public class IPoemSpider implements ApplicationRunner {
    @Value("${ipoem.spider.enable}")
    private boolean spiderEnable;
    private final SpiderModelPipeline spiderModelPipeline;
    private static Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public IPoemSpider(SpiderModelPipeline spiderModelPipeline) {
        this.spiderModelPipeline = spiderModelPipeline;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (spiderEnable) {
            String startUrl = "http://www.shicimingju.com/category/all";
            OOSpider.create(site, spiderModelPipeline, PoemSpiderDetails.class)
                    .thread(5)
                    .addUrl(startUrl)
                    .run();
        }
    }
}
