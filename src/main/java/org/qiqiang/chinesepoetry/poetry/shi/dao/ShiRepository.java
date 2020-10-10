package org.qiqiang.chinesepoetry.poetry.shi.dao;

import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 诗词 Repository
 * @author qiqiang
 */
public interface ShiRepository extends JpaRepository<ShiEntity, Long> {
    List<ShiEntity> findByAuthor(String author);
}
