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
 * @date 2018/8/7 16:56
 * @since 1.8
 */
@Embeddable
public class Contact implements Serializable {

    /**
     * 邮箱
     */
    private String email;
    /**
     * qq号
     */
    private String qqNumber;
    /**
     * 手机号码
     */
    private String mobile;

    @Column(name = "user_email",length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "user_qq",length = 11)
    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    @Column(name = "user_mobile",length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "email='" + email + '\'' +
                ", qqNumber='" + qqNumber + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
