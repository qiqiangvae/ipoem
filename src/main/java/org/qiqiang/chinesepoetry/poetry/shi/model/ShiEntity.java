package org.qiqiang.chinesepoetry.poetry.shi.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 诗歌详情
 * @author qiqiang
 */
@Data
@Table(name = "chinese_poetry_shi")
@Entity
public class ShiEntity {
    @Id
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 朝代
     */
    private String dynasty;
    /**
     * 作者
     */
    private String author;
    /**
     * 段落
     */
    private String paragraphs;

    /**
     * 入库时间
     */
    private Date createTime;
}
