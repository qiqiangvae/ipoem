package org.qiqiang.chinesepoetry.poetry.shi.service;

import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;

import java.util.List;

/**
 * 诗词
 * @author qiqiang
 */
public interface ShiService {
    /**
     * 通过 id 查找
     *
     * @param id id
     * @return 诗词
     */
    ShiEntity findById(Long id);

    /**
     * 插入
     *
     * @param shiEntity 诗词
     * @return 诗词
     */
    ShiEntity insertOne(ShiEntity shiEntity);

    /**
     * 通过作者查找
     *
     * @param author 作者
     * @return 诗词集合
     */
    List<ShiEntity> findByAuthor(String author);

    /**
     * 随机返回诗词
     *
     * @return 诗词
     */
    ShiEntity random();
}
