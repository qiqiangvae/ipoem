package org.qiqiang.chinesepoetry.poetry.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 返回前端数据类型
 * @author qiqiang
 */
@Data
public class PoetryDetailsResponseVO {
    private String id;
    private String title;
    private String dynasty;
    private String author;
    private List<String> lines;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
