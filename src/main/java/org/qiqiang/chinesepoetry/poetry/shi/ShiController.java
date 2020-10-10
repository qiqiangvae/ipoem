package org.qiqiang.chinesepoetry.poetry.shi;

import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;
import org.qiqiang.chinesepoetry.poetry.shi.service.ShiService;
import org.qiqiang.chinesepoetry.poetry.vo.PoetryDetailsResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiController {
    private final ShiService poemDetailsService;

    public ShiController(ShiService poemDetailsService) {
        this.poemDetailsService = poemDetailsService;
    }

    @GetMapping("/shi/{id}")
    public ShiEntity findById(@PathVariable Long id) {
        return poemDetailsService.findById(id);
    }

    @GetMapping("/random/shi")
    public PoetryDetailsResponseVO random() {
        ShiEntity poem = poemDetailsService.random();
        PoetryDetailsResponseVO dto = new PoetryDetailsResponseVO();
        BeanUtils.copyProperties(poem, dto);
        dto.setLines(ShiConvertor.content2Lines(poem.getParagraphs()));
        return dto;
    }
}
