package org.qiqiang.chinesepoetry.poetry.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 返回前端数据类型
 */
@Data
public class PoetryDetailsResponseVO {
    private String id;
    private String title;
    private String dynasty;
    private String author;
    private List<String> lines;
    private String type;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
