package org.qiqiang.chinesepoetry.poetry.shi.service;

import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;

import java.util.List;

/**
 * 诗词
 *
 * @author qiqiang
 */
public interface ShiSearchService {

    /**
     * 通过作者查找
     *
     * @param search 作者
     * @return 诗词集合
     */
    List<ShiEntity> search(String search);
}
