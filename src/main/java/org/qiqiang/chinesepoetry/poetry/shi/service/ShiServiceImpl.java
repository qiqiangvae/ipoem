package org.qiqiang.chinesepoetry.poetry.shi.service;

import org.apache.commons.lang3.RandomUtils;
import org.qiqiang.chinesepoetry.poetry.shi.dao.ShiRepository;
import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiServiceImpl implements ShiService {
    private final ShiRepository poemDetailsRepository;

    public ShiServiceImpl(ShiRepository poemDetailsRepository) {
        this.poemDetailsRepository = poemDetailsRepository;
    }

    @Override
    public ShiEntity findById(Long id) {
        return poemDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public ShiEntity insertOne(ShiEntity shiEntity) {
        return poemDetailsRepository.save(shiEntity);
    }

    @Override
    public List<ShiEntity> findByAuthor(String author) {
        return poemDetailsRepository.findByAuthor(author);
    }

    @Override
    public ShiEntity random() {
        long count = poemDetailsRepository.count();
        return this.findById(RandomUtils.nextLong(0, count));
    }
}
