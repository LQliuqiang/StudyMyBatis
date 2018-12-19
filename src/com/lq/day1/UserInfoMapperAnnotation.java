package com.lq.day1;

import org.apache.ibatis.annotations.Select;

public interface UserInfoMapperAnnotation {

    //通过id获取UserInfo
    @Select("select * from user_info where id = #{id}")
    UserInfo getUserInfoById(Integer id);

}
