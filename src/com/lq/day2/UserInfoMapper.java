package com.lq.day2;


import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {

    //添加一个用户[动态添加表名]
    //一个参数是tabelName ， 另一个参数 是UserInfo 对象， 所以<insert > 标签中不能有parameterType 参数
    void addUserInfo(@Param("tableName")String tableName,@Param("userInfo") UserInfo userInfo);

    //修改一个用户
    boolean updateUserInfo(UserInfo userInfo);

    //删除一个用户
    void deleteUserInfo(Integer id);

    /**
     * 查询数据库的不同方式
     */

    //1[单个参数].通过id查询一个用户（myBatis不会进行特殊的处理）
    UserInfo getUserInfoById(Integer id);

    //2[多个参数使用注解形式].通过id与username查询一个用户 (也就是多参数执行：myBatis会进行特殊的处理，导致不能与传一个参数一样去写数据库语句操作的写法)
    UserInfo getUserInfoByIdAndUsername(@Param("id") Integer id, @Param("username") String username);

    //3[多个参数使用Map集合形式]
    UserInfo getUserInfoByMap(Map<String,Object> map);

    //4.根据username进行模糊查询
    List<UserInfo> getUserInfos(String usernameLike);

    //5.根据id查询数据库中的一条记录，返回的是Map集合类型 (返回一条记录的map；key就是列名，值就是对应的值)
    Map<String,Object> getUserInfoMap(Integer id);

    //6.多条记录封装一个map：Map<Integer,UserInfo>:键是这条记录的主键，值是记录封装后的javaBean
    @MapKey("id") //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key
    Map<String, UserInfo> getEmpByUsernameLikeReturnMap(String usernameLike);

}
