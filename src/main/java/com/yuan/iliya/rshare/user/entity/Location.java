package com.yuan.iliya.rshare.user.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/7/31 16:53
 * @since 1.8
 */
@Embeddable
public class Location implements Serializable {

    /**
     * 所在学校  默认重庆大学
     */
    private String university = "重庆大学";
    /**
     * 所在学院
     */
    private String college;
    /**
     * 所在专业
     */
    private String specialty;
    /**
     * 所在寝室
     */
    private String domitory;

    @Column(name = "user_university",columnDefinition = "varchar(20) default '重庆大学'",nullable = true)
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Column(name = "user_college",length = 20)
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Column(name = "user_specialty",length = 30)
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Column(name = "user_domitory",length = 30)
    public String getDomitory() {
        return domitory;
    }

    public void setDomitory(String domitory) {
        this.domitory = domitory;
    }

    @Override
    public String toString() {
        return "Location{" +
                "university='" + university + '\'' +
                ", college='" + college + '\'' +
                ", specialty='" + specialty + '\'' +
                ", domitory='" + domitory + '\'' +
                '}';
    }
}
