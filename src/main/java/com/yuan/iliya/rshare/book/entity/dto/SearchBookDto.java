package com.yuan.iliya.rshare.book.entity.dto;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/9/17 14:57
 * @since 1.8
 */
public class SearchBookDto {

    private String title;
    private String classify;
    private Integer index;
    private Integer count;
    private String byNew;
    private String byPrice;

    public String getByNew() {
        return byNew;
    }

    public void setByNew(String byNew) {
        this.byNew = byNew;
    }

    public String getByPrice() {
        return byPrice;
    }

    public void setByPrice(String byPrice) {
        this.byPrice = byPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
