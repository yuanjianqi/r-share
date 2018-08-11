package com.yuan.iliya.rshare.user.entity;

import com.yuan.iliya.rshare.information.entity.Information;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * All Rights Reserved, Designed By Iliya Kaslana
 * Copyright ©2018
 *
 * @author Iliya Kaslana
 * @version 1.0
 * @date 2018/8/11 13:15
 * @since 1.8
 */
@Entity
@Table(name = "tb_user_informations")
public class UserInformations implements Serializable {


    private User user;
    private Information information;

    public UserInformations() {
    }

    public UserInformations(User user, Information information) {
        this.user = user;
        this.information = information;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @Id
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @Id
    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        {return true;}
        if (o == null || getClass() != o.getClass())
        {return false;}

        UserInformations that = (UserInformations) o;

        if (!getUser().equals(that.getUser()))
        {return false;}
        return getInformation().equals(that.getInformation());
    }

    @Override
    public int hashCode() {
        int result = getUser().hashCode();
        result = 31 * result + getInformation().hashCode();
        return result;
    }
}
