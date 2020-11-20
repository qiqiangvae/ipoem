package org.qiqiang.chinesepoetry.poetry.shi.service;

import org.qiqiang.chinesepoetry.common.PageHolder;
import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;
import org.qiqiang.chinesepoetry.poetry.shi.vo.ShiSearchDTO;
import org.qiqiang.chinesepoetry.vo.PageRequest;

/**
 * 诗词
 *
 * @author qiqiang
 */
public interface ShiSearchService {

    /**
     * 通过作者查找
     *
     * @param shiSearchDTO 搜索条件
     * @param pageRequest 分页信息
     * @return 诗词集合
     */
    PageHolder<ShiEntity> search(ShiSearchDTO shiSearchDTO, PageRequest pageRequest);
}
