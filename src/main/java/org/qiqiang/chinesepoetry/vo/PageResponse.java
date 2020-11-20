package org.qiqiang.chinesepoetry.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author qiqiang
 * @date 2020-11-20 9:34 上午
 */
@Getter
@ToString
public class PageResponse {
    private Integer currentIndex;
    private Integer totalPageSize;
    private Long totalDataSize;
    private Integer pageSize;

    public PageResponse(int currentIndex, int pageSize, long totalDataSize) {
        this.currentIndex = currentIndex;
        this.pageSize = pageSize;
        this.totalDataSize = totalDataSize;
        this.totalPageSize = (int) Math.ceil(totalDataSize / (double) pageSize);
    }

    public PageResponse(PageRequest pageRequest, long totalDataSize) {
        if (pageRequest == null) {
            this.currentIndex = 0;
            this.pageSize = 10;
        } else {
            this.currentIndex = pageRequest.getStartPage() == null ? 0 : pageRequest.getStartPage();
            this.pageSize = pageRequest.getPageSize() == null ? 10 : pageRequest.getStartPage();
        }
        this.totalDataSize = totalDataSize;
        this.totalPageSize = (int) Math.ceil(totalDataSize / (double) pageRequest.getPageSize());
    }
}