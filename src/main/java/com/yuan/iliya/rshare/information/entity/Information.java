package com.yuan.iliya.rshare.information.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@SuppressWarnings("all")
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
    private Date date;
    /**
     * 信息热度
     */
    private Integer publicity = 0;

    /**
     * 信息图片的url
     */
    private List<String> imgUrls = new ArrayList<>();

    /**
     * 信息内容
     */
    private String informationBody;

    /**
     * 信息的详细类别
     */
    private String detailClassify;

    /**
     * 信息是否审核通过
     */
    private Boolean state = INFORMATION_INVALID;

    public static Boolean INFORMATION_VALID = true;
    public static Boolean INFORMATION_INVALID = false;
    public Information() {
    }

    public Information(String id) {
        this.id = id;
    }

    @Column(name = "info_detail_classify",length = 6)
    public String getDetailClassify() {
        return detailClassify;
    }

    public void setDetailClassify(String detailClassify) {
        this.detailClassify = detailClassify;
    }

    @Column(name = "info_body",length = 65535)
    public String getInformationBody() {
        return informationBody;
    }

    public void setInformationBody(String informationBody) {
        this.informationBody = informationBody;
    }


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


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "info_date",length = 20)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "info_publicity",length = 10)
    public Integer getPublicity() {
        return publicity;
    }

    public void setPublicity(Integer publicity) {
        this.publicity = publicity;
    }

    @ElementCollection
    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    @Column(name = "info_state",length = 1)
    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
