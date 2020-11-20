package org.qiqiang.chinesepoetry.poetry.shi;

import org.qiqiang.chinesepoetry.common.PageHolder;
import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;
import org.qiqiang.chinesepoetry.poetry.shi.service.ShiSearchService;
import org.qiqiang.chinesepoetry.poetry.shi.service.ShiService;
import org.qiqiang.chinesepoetry.poetry.shi.vo.*;
import org.qiqiang.chinesepoetry.vo.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 诗搜索
 *
 * @author qiqiang
 */
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
    public ShiPageVO search(ShiSearchVO shiSearchVO, PageRequest pageRequest) {
        ShiSearchDTO shiSearchDTO = new ShiSearchDTO();
        shiSearchDTO.setSearch(shiSearchVO.getSearch());
        shiSearchDTO.setSearchTarget(shiSearchVO.getSearchTarget());
        shiSearchDTO.setSimple(shiSearchVO.getSimple());
        PageHolder<ShiEntity> pageHolder = shiSearchService.search(shiSearchDTO, pageRequest);
        List<ShiEntity> list = pageHolder.getData();
        ShiPageVO result = new ShiPageVO();
        List<PoetryDetailsResponseVO> data = new ArrayList<>(list.size());
        for (ShiEntity shiEntity : list) {
            PoetryDetailsResponseVO dto = PoetryDetailsResponseUtils.shi2Response(shiEntity, Boolean.TRUE.equals(shiSearchVO.getSimple()));
            data.add(dto);
        }
        result.setData(data);
        result.setPage(pageHolder.getPageResponse());
        return result;
    }
}
