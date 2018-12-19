package com.lq.day3;

import java.util.List;

public class CarPuls {

    private Integer id;
    private String type;
    private String price;
    private List<UserInfo> userInfos;

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CarPuls{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", userInfos=" + userInfos +
                '}';
    }
}
