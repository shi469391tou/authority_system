package com.hopu.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 用户表
 */
@TableName("t_user")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String userName; // 用户名
    private String password; // 密码
    private String salt; // 加密用户的盐
    private String nickname; // 昵称
    private String tel;  // 手机号码
    private Integer sex; // 性别：1、男；-1、女
    private String email; // 邮箱
    private String status;  // 状态：on、可用；其它、禁用


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
        "userName=" + userName +
        ", password=" + password +
        ", salt=" + salt +
        ", nickname=" + nickname +
        ", tel=" + tel +
        ", sex=" + sex +
        ", email=" + email +
        ", status=" + status +
        "}";
    }
}
