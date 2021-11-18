package edu.neu.weatherforecasting.data.model;

public class User {
    private Integer id;
    private String username;
    private String pwd;
    private String chName;

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
}
