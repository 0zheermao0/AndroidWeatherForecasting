package edu.neu.weatherforecasting.data.model;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String username;
    private String pwd;
    private String chName;
    private Integer sex;
    private String age;

    public User(Integer id, String username, String pwd, String chName) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.chName = chName;
    }

    public User(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getchName() {
        return chName;
    }

    public void setchName(String chName) {
        this.chName = chName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", chName='" + chName + '\'' +
                ", sex=" + sex +
                ", age='" + age + '\'' +
                '}';
    }
}
