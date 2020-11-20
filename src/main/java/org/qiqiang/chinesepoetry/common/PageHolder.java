package org.qiqiang.chinesepoetry.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.qiqiang.chinesepoetry.vo.PageResponse;

import java.util.List;

/**
 * @author qiqiang
 * @date 2020-11-20 9:55 上午
 */
@Getter
@Setter
@ToString
public class PageHolder<T> {
    private PageResponse pageResponse;
    private List<T> data;

    public PageHolder(PageResponse pageResponse, List<T> data) {
        this.pageResponse = pageResponse;
        this.data = data;
    }
}