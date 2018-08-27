package com.lzl.config.mybatis;

import com.github.pagehelper.PageInfo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的分页类
 * @Author: lzl
 * @Date: 2018/08/27 21:25
 */
public class Pg<T> implements Serializable {

    /**
     * 第几页
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 总共页码数
     */
    private Long total;

    private List<T> list;

    private Pg(){
        this.pageSize = 1;
        this.pageNum = 1;
        this.total = 1L;
        this.list = new ArrayList<>();
    }

    private Pg(@NotNull Integer pageNum, @NotNull Integer pageSize,@NotNull Long total,@NotNull List<T> list){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }

    public static <T> Pg<T> ofPageInfo(PageInfo<T> pageInfo){
        return new Pg<>(pageInfo.getPageNum(),pageInfo.getPageSize(),
                pageInfo.getTotal(),pageInfo.getList());
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

