package org.qiqiang.chinesepoetry.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author qiqiang
 * @date 2020-11-20 9:44 上午
 */
@Getter
@Setter
@ToString
public class PageRequest {
    private Boolean need;
    /**
     * 默认1
     */
    private Integer startPage;
    private Integer pageSize;

    public Integer from() {
        return (startPage - 1) * pageSize;
    }

    public Integer end() {
        return startPage * pageSize;
    }
}