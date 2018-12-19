package com.lq.day3;


import java.util.List;

public interface UserInfoMapper {


    //resultMap 表与表之间的联合查询封装结果集的两种方式
    UserInfo getUserInfoAndCar(Integer id);

    //分布查询   也就是一步一步的查询
    UserInfo getUserInfo(Integer id);

    //根据carId查询出对应所有的UserInfo
    List<UserInfo> getUserInfosByCarId(Integer carId);
}
