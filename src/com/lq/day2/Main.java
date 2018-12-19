package com.lq.day2;

import com.lq.Util;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {


    /**
     * 增删查改 内置注解 查询返回值集合(查询多条)
     * @param args
     */
    public static void main(String[] args) {
        //MyBatis允许直接定义增删改的返回值类型  Long，Boolean，Integer
        //sqlSessionFactory.openSession(true) 这种是自动提交
        SqlSession sqlSession = null;
        try {
            SqlSessionFactory sqlSessionFactory = Util.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession(true);
            UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
            //查
//            UserInfo userInfo = userInfoMapper.getUserInfoById(2);
//            System.out.println(userInfo.toString());
            //添加-----插入数据后也能拿到当前自增记录的的id值
            UserInfo userInfo = new UserInfo(11, "zly3", "333", "fffee");
            userInfoMapper.addUserInfo("user_info",userInfo);
            System.out.println(userInfo.getId());
            //修改
//            boolean b = userInfoMapper.updateUserInfo(new UserInfo(4, "kobe", "45678", "爱好篮球"));
//            if (b){
//                System.out.println("修改成功");
//            }else {
//                System.out.println("修改失败");
//            }
            //删除
//            userInfoMapper.deleteUserInfo(3);

            //传递多个参数查询 (第一种方式)
//            UserInfo userInfo = userInfoMapper.getUserInfoByIdAndUsername(2, "liuqiang");

            //传递多个参数查询 (第二种方式)
//            Map<String, Object> map = new HashMap<>();
//            map.put("tableName","user_info");
//            map.put("id",2);
//            map.put("username","liuqiang");
//            UserInfo userInfo = userInfoMapper.getUserInfoByMap(map);
//            System.out.println(userInfo.toString());

             //根据username进行模糊查询  得到List<UserInfo>
//            List<UserInfo> userInfos = userInfoMapper.getUserInfos("%z%");
//            System.out.println(userInfos);

            //根据id查询数据库中的一条记录，返回的是Map集合类型 (返回一条记录的map；key就是列名，值就是对应的值)
//            Map<String, Object> userInfoMap = userInfoMapper.getUserInfoMap(3);
//            System.out.println(userInfoMap);

             //多条记录封装一个map：Map<Integer,UserInfo>:键是这条记录的主键，值是记录封装后的javaBean
//            Map<String, UserInfo> userInfoMaps = userInfoMapper.getEmpByUsernameLikeReturnMap("%l%");
//            System.out.println(userInfoMaps);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
    }


}
