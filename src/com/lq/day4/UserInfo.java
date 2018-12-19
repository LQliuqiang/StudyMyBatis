package com.lq.day4;


public class UserInfo {

    private Integer id;
    private String userName;
    private String password;
    private String info;

    public UserInfo() {
    }

    public UserInfo(Integer id, String userName, String password, String info) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
