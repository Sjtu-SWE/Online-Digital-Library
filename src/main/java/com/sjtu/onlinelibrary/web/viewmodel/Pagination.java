package com.sjtu.onlinelibrary.web.viewmodel;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-8-3
 * Time: 下午2:43
 */
public class Pagination implements IPagination {
    public static final int DEFAULT_PAGE_SIZE = 10;
    private int totalCount;
    private int pageIndex;
    private int pageSize = DEFAULT_PAGE_SIZE; //default page size

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
        return (int) Math.ceil(totalCount / (double)pageSize);
    }

    public boolean hasPreviousPage() {
        return pageIndex > 1;
    }


    public boolean hasNextPage() {
        return pageIndex * pageSize < totalCount;
    }
}
