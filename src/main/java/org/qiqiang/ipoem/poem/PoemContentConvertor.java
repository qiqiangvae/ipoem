package org.qiqiang.ipoem.poem;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class PoemContentConvertor {

    /**
     * 将文本段落转换成行数，按 。 ！ ； 分割
     *
     * @param content
     * @return
     */
    public static List<String> content2Lines(String content) {
        List<String> lines = new LinkedList<>();
        String[] split = StringUtils.split(content, "。");
        lines.addAll(new ArrayList<>(Arrays.asList(split)));
        lines = lines.stream()
                .filter(StringUtils::isNotBlank)
                .map(value -> StringUtils.strip(value) + "。")
                .collect(Collectors.toList());
        return lines;
    }
}
