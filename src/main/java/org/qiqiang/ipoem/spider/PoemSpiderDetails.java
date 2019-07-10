package org.qiqiang.ipoem.spider;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@ToString
@Data
@TargetUrl("http://www.shicimingju.com/chaxun/list/\\d*.html")
@HelpUrl("http://www.shicimingju.com(/chaxun/zuozhe/\\d.html|/category/all.*.html)")
public class PoemSpiderDetails {
    @ExtractBy("//h1[@class='shici-title']/text()")
    private String title;
    @ExtractBy("//div[@class='shici-info']/text()")
    private String dynasty;
    @ExtractBy("//div[@class='shici-info']/a/text()")
    private String author;
    @ExtractBy("//div[@class='shici-content']/text()")
    private String content;
}
