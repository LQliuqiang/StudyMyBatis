package com.lq.day4;

import com.lq.Util;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    /**
     * 动态SQL
     * -------
     * if与where标签 trim标签 choose标签 set标签 foreach标签:批量查询与插入
     */
    public static void main(String[] args){

        try {
            SqlSessionFactory sqlSessionFactory = Util.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);

            //测试where
//            UserInfo userInfo = new UserInfo(null,"%l%",null,null);
//            List<UserInfo> userInfos = mapper.getUserInfoConditionWhere(userInfo);
//            for (UserInfo ui:userInfos){
//                System.out.println(ui.toString());
//            }

            //测试trim
//            UserInfo userInfo2 = new UserInfo(null,"%z%",null,null);
//            List<UserInfo> userInfos2 = mapper.getUserInfoConditionTrim(userInfo2);
//            for (UserInfo ui:userInfos2){
//                System.out.println(ui.toString());
//            }

            //测试choose
//            UserInfo userInfo3 = new UserInfo(2,"%z%",null,null);
//            List<UserInfo> userInfos3 = mapper.getUserInfoConditionChoose(userInfo3);
//            for (UserInfo ui:userInfos3){
//                System.out.println(ui.toString());
//            }

            //测试set
//            UserInfo userInfo4 = new UserInfo(4,"zhanMuSi",null,"nihao");
//            boolean b = mapper.updateUserInfoConditionSet(userInfo4);
//            if (b){
//                System.out.println("修改成功");
//            }else {
//                System.out.println("修改失败");
//            }

            //测试foreach标签
//            List<UserInfo> userInfos4 = mapper.getUserInfoConditionForeach(2,3,5);
//            for (UserInfo userInfo:userInfos4){
//                System.out.println(userInfo.toString());
//            }

            //测试内置的Parameter参数
            List<UserInfo> infoList = mapper.getUserInfoTestInnerParameter(new UserInfo(null, "%z%", null, null));
            for (UserInfo userInfo : infoList) {
                System.out.println(userInfo.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
