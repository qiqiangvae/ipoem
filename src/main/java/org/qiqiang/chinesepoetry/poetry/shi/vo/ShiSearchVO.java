package org.qiqiang.chinesepoetry.poetry.shi.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author qiqiang
 * @date 2020-11-20 1:54 下午
 */
@Getter
@Setter
@ToString
public class ShiSearchVO {
    private String search;
    /**
     * "title", "author", "paragraphs"
     */
    private String searchTarget;
    private Boolean simple;
}