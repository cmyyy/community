package org.cmy.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;


    public void setPagination(Integer totalPage, Integer page) {
        //总页数
        this.totalPage = totalPage;
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3;i++){
            if (page - i > 0){
                pages.add(0,page - i);
            }
            if (page + i <= totalPage){
                pages.add(page + i);
            }
        }
        //是否展示上一页
        showPrevious = page != 1;
        //是否展示下一页
        showNext = page != totalPage;
        //是否展示第一页
        if (pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        //是否展示尾页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
