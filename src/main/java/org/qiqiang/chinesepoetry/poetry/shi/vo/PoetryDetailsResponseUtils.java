package org.qiqiang.chinesepoetry.poetry.shi.vo;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import org.qiqiang.chinesepoetry.poetry.shi.ShiConvertor;
import org.qiqiang.chinesepoetry.poetry.shi.model.ShiEntity;
import org.qiqiang.chinesepoetry.utils.PojoUtils;

/**
 * @author qiqiang
 * @date 2020-11-18 2:20 下午
 */
public class PoetryDetailsResponseUtils {
    public static PoetryDetailsResponseVO shi2Response(ShiEntity shiEntity, boolean simple) {
        PoetryDetailsResponseVO responseVO = new PoetryDetailsResponseVO();
        PojoUtils.copyProperties(responseVO, shiEntity, () -> {
            if (simple) {
                shiEntity.setParagraphs(ZhConverterUtil.toSimple(shiEntity.getParagraphs()));
                responseVO.setTitle(ZhConverterUtil.toSimple(shiEntity.getTitle()));
                responseVO.setAuthor(ZhConverterUtil.toSimple(shiEntity.getAuthor()));
                responseVO.setDynasty(ZhConverterUtil.toSimple(shiEntity.getDynasty()));
            }
        });
        responseVO.setLines(ShiConvertor.content2Lines(shiEntity.getParagraphs()));
        return responseVO;
    }
}