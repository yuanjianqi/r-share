package com.yuan.iliya.rshare.user.entity;

import com.yuan.iliya.rshare.information.entity.Information;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/21 12:16
 * @since 1.8
 */
@Entity
@Table(name = "tb_user_release_information")
public class UserReleaseInformation implements Serializable {

    private User user;
    private Information information;

    public UserReleaseInformation() {
    }

    public UserReleaseInformation(User user, Information information) {
        this.user = user;
        this.information = information;
    }

    @Id
    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @ManyToOne
    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;

        }
        if (o == null || getClass() != o.getClass()){
            return false;

        }
        UserReleaseInformation that = (UserReleaseInformation) o;
        return Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getInformation(), that.getInformation());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUser(), getInformation());
    }
}
