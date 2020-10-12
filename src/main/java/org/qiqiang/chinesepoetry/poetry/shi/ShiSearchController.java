package org.qiqiang.chinesepoetry.poetry.shi;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;
import org.qiqiang.chinesepoetry.poetry.shi.service.ShiSearchService;
import org.qiqiang.chinesepoetry.poetry.vo.PoetryDetailsResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShiSearchController {
    private final ShiSearchService shiSearchService;

    public ShiSearchController(ShiSearchService shiSearchService) {
        this.shiSearchService = shiSearchService;
    }

    @GetMapping("search/shi")
    public List<PoetryDetailsResponseVO> search(String search, Boolean simple) {
        List<ShiEntity> list = shiSearchService.search(search);
        List<PoetryDetailsResponseVO> result = new ArrayList<>(list.size());
        for (ShiEntity shiEntity : list) {
            PoetryDetailsResponseVO dto = new PoetryDetailsResponseVO();
            BeanUtils.copyProperties(shiEntity, dto);
            if (Boolean.TRUE.equals(simple)) {
                shiEntity.setParagraphs(ZhConverterUtil.toSimple(shiEntity.getParagraphs()));
                dto.setTitle(ZhConverterUtil.toSimple(shiEntity.getTitle()));
                dto.setAuthor(ZhConverterUtil.toSimple(shiEntity.getAuthor()));
                dto.setDynasty(ZhConverterUtil.toSimple(shiEntity.getDynasty()));
            }
            dto.setLines(ShiConvertor.content2Lines(shiEntity.getParagraphs()));
            result.add(dto);
        }
        return result;
    }
}
