package com.yuan.iliya.rshare.user.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *  用户基本信息
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/7/31 16:46
 * @since 1.8
 */
@Entity
@Table(name = "tb_user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class User implements Serializable {

    /**
     * 用户id
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户性别
     * 男:true
     * 女:false
     */
    private Boolean gender;
    /**
     * 用户学号
     */
    private String studentNumber;
    /**
     * 用户位置信息
     */
    private Location location;
    /**
     * 用户信誉值
     */
    private Long credibility = 10000L;
    /**
     * 用户联系方式
     */
    private Contact contact;

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    /**
     * 测试用方法
     * @return
     */
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                ", studentNumber='" + studentNumber + '\'' +
                ", location=" + location +
                ", credibility=" + credibility +
                ", contact=" + contact +
                ", informations=" +
                '}';
    }




    @Id
    @GenericGenerator(strategy = "uuid",name = "user_uuid")
    @GeneratedValue(generator = "user_uuid")
    @Column(name = "user_id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "user_name",length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "user_gender",length = 1)
    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    @Column(name = "user_student_number",length = 12)
    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Embedded
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Column(name = "user_credibility",length = 4)
    public Long getCredibility() {
        return credibility;
    }

    public void setCredibility(Long credibility) {
        this.credibility = credibility;
    }

    @Embedded
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
