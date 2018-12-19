package com.lq.day4;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {


    //动态SQL ---测试if与where标签
    List<UserInfo> getUserInfoConditionWhere(UserInfo userInfo);


    //动态SQL ---测试trim标签
    List<UserInfo> getUserInfoConditionTrim(UserInfo userInfo);


    //动态SQL ---测试choose标签
    List<UserInfo> getUserInfoConditionChoose(UserInfo userInfo);


    //动态SQL ---测试set标签
    boolean updateUserInfoConditionSet(UserInfo userInfo);


    //动态SQL ---测试foreach标签：批量查询
    List<UserInfo> getUserInfoConditionForeach(@Param("ids")Integer... ids);

    //动态SQL ---测试foreach标签：批量保存
    List<UserInfo> saveUserInfoConditionForeach(@Param("userInfos")List<UserInfo> userInfos);

    //动态SQL ---测试 内置属性Parameter
    List<UserInfo> getUserInfoTestInnerParameter(UserInfo userInfo);
}
