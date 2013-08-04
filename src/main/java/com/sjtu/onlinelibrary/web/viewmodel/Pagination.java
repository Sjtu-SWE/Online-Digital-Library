package com.sjtu.onlinelibrary.web.viewmodel;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午2:43
 */
public class Pagination implements IPagination {
    private int totalCount;
    private int pageIndex;
    private int pageSize= 10; //default page size

    @Override
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(final int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(final int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getTotalPage() {
        return (totalCount % pageSize == 0) ? totalCount / pageSize : totalCount / pageSize + 1;
    }

    public boolean hasPreviousPage() {
        return pageIndex > 0;
    }


    public boolean hasNextPage() {
        return ((pageIndex + 1) * pageSize) < totalCount;
    }
}
