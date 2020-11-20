package org.qiqiang.chinesepoetry.poetry.shi.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.qiqiang.chinesepoetry.vo.PageResponse;

import java.util.List;

/**
 * @author qiqiang
 * @date 2020-11-20 9:53 上午
 */
@Getter
@Setter
@ToString
public class ShiPageVO {
    private List<PoetryDetailsResponseVO> data;
    private PageResponse page;
}