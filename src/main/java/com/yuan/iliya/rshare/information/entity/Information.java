package com.yuan.iliya.rshare.information.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/9 17:15
 * @since 1.8
 */
@Entity
@Table(name = "tb_information")
public class Information implements Serializable {

    /**
     * 信息id
     */
    private String id;
    /**
     * 信息类别
     */
    private String classify;
    /**
     * 信息标题
     */
    private String title;
    /**
     * 信息发布日期
     */
    private LocalDate date;
    /**
     * 信息热度
     */
    private Integer publicity;

    /**
     * 信息头部banner的url
     */
    private String imgUrl;

    @Id
    @GenericGenerator(strategy = "uuid",name = "information_uuid")
    @GeneratedValue(generator = "information_uuid")
    @Column(name = "info_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "info_classify",length = 20)
    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @Column(name = "info_title",length = 60)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Column(name = "info_date",length = 10)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Column(name = "info_publicity",length = 10)
    public Integer getPublicity() {
        return publicity;
    }

    public void setPublicity(Integer publicity) {
        this.publicity = publicity;
    }

    @Column(name = "info_image_url",length = 100)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
