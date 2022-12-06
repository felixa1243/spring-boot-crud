package com.iqbalnetwork.models.responses;

import org.springframework.data.domain.Page;

public class PagedResponse<T> {
    private Integer page;
    private Integer size;
    private Integer fetchedSize;
    private Long totalSize;
    private Integer totalPage;
    private Iterable<T> content;

    public PagedResponse(Page<T> pagedResult) {
        this.page = pagedResult.getNumber();
        this.size = pagedResult.getSize();
        this.fetchedSize = pagedResult.getNumberOfElements();
        this.totalSize = pagedResult.getTotalElements();
        this.totalPage = pagedResult.getTotalPages();
        this.content = pagedResult.getContent();
    }
}
