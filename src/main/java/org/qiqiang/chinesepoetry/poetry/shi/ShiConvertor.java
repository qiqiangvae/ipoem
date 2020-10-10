package org.qiqiang.chinesepoetry.poetry.shi;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * 转换器
 *
 * @author qiqiang
 */
public class ShiConvertor {

    /**
     * 将文本段落转换成行数
     *
     * @param content
     * @return
     */
    public static List<String> content2Lines(String content) {
        return JSONArray.parseArray(content, String.class);
    }
}
