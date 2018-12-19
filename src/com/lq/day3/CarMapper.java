package com.lq.day3;

public interface CarMapper {

    //通过id获取Car
    Car getCar(Integer id);

    //通过id获取Car： 每种车可能对应着多个用户： user1,user3,user4都有宝马车 user2,user5都有奔驰车
    CarPuls getCarPuls(Integer id);

    //分步骤查询
    CarPuls getCarPulsByIdStep(Integer id);

}
