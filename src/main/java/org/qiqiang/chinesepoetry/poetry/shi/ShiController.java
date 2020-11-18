package org.qiqiang.chinesepoetry.poetry.shi;

import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;
import org.qiqiang.chinesepoetry.poetry.shi.service.ShiSearchService;
import org.qiqiang.chinesepoetry.poetry.shi.service.ShiService;
import org.qiqiang.chinesepoetry.poetry.vo.PoetryDetailsResponseUtils;
import org.qiqiang.chinesepoetry.poetry.vo.PoetryDetailsResponseVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShiController {
    private final ShiService poemDetailsService;
    private final ShiSearchService shiSearchService;

    public ShiController(ShiService poemDetailsService, ShiSearchService shiSearchService) {
        this.poemDetailsService = poemDetailsService;
        this.shiSearchService = shiSearchService;
    }

    /**
     * 根据id查找诗
     *
     * @param id id
     * @return 诗
     */
    @GetMapping("/shi/{id}")
    public PoetryDetailsResponseVO findById(@PathVariable Long id, Boolean simple) {
        ShiEntity shiEntity = poemDetailsService.findById(id);
        return PoetryDetailsResponseUtils.shi2Response(shiEntity, Boolean.TRUE.equals(simple));
    }

    /**
     * 随机获取一首诗
     *
     * @param simple 是否返回简体字
     * @return 诗
     */
    @GetMapping("/random/shi")
    public PoetryDetailsResponseVO random(Boolean simple) {
        ShiEntity shiEntity = poemDetailsService.random();
        return PoetryDetailsResponseUtils.shi2Response(shiEntity, Boolean.TRUE.equals(simple));
    }

    /**
     * 搜索诗
     */
    @GetMapping("shi")
    public List<PoetryDetailsResponseVO> search(String search, Boolean simple) {
        List<ShiEntity> list = shiSearchService.search(search);
        List<PoetryDetailsResponseVO> result = new ArrayList<>(list.size());
        for (ShiEntity shiEntity : list) {
            PoetryDetailsResponseVO dto = PoetryDetailsResponseUtils.shi2Response(shiEntity, Boolean.TRUE.equals(simple));
            result.add(dto);
        }
        return result;
    }
}
