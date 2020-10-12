package org.qiqiang.chinesepoetry.poetry.shi;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
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
    public PoetryDetailsResponseVO random(Boolean simple) {
        ShiEntity poem = poemDetailsService.random();
        PoetryDetailsResponseVO dto = new PoetryDetailsResponseVO();
        BeanUtils.copyProperties(poem, dto);
        if (Boolean.TRUE.equals(simple)) {
            poem.setParagraphs(ZhConverterUtil.toSimple(poem.getParagraphs()));
            dto.setTitle(ZhConverterUtil.toSimple(poem.getTitle()));
            dto.setAuthor(ZhConverterUtil.toSimple(poem.getAuthor()));
            dto.setDynasty(ZhConverterUtil.toSimple(poem.getDynasty()));
        }
        dto.setLines(ShiConvertor.content2Lines(poem.getParagraphs()));
        return dto;
    }
}
