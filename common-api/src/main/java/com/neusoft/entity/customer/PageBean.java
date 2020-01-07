package com.neusoft.entity.customer;


import java.util.List;

public class PageBean<T> {
    private int totalCount;//总记录数
    private int totalPage;//总页码
    private int currentPage;//当前页码
    private int rows;//每页显示的记录数
    private List<T> list;//每页的数据


    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getRows() {
        return rows;
    }

    public List getList() {
        return list;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setList(List list) {
        this.list = list;
    }

}
