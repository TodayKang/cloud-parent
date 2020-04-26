package com.cloud.api.base;

import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> implements Serializable {
    private static final long serialVersionUID = -3541229317102319727L;

    // 当前页（必要参数，最少为1，默认1）
    private Long currentPage;

    // 每页大小（必要参数，默认1000）
    private Long pageSize;

    // 总条数（默认为空）
    private Long total;

    // 总页数
    private Long pageTotal;

    // 当前页查询数据
    @Setter
    private Collection<? extends T> list;

    public Long getPageTotal() {
        if (total != null && pageSize != null) {
            this.pageTotal = (total % pageSize == 0) ? total / pageSize : (total / pageSize + 1);
            return this.pageTotal;
        }
        return 0L;
    }

    public PageVO(Long currentPage, Long pageSize, Long total, Collection<? extends T> list) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

}
