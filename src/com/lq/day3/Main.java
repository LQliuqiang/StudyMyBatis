package com.lq.day3;

import com.lq.Util;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;

public class Main {

    /**
     * 表与表之间的级联操作（联合查询），与分步骤查询
     * @param args
     */
    public static void main(String[] args){

        try {
            SqlSessionFactory sqlSessionFactory = Util.getSqlSessionFactory();
            SqlSession sqlSession = sqlSessionFactory.openSession(true);

            //UserInfoMapper
            UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
//            UserInfo userInfo = mapper.getUserInfoAndCar(2);
//            System.out.println(userInfo.toString());
//            UserInfo userInfo = mapper.getUserInfo(3);
//            System.out.println(userInfo.toString());

//
            CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
            CarPuls carPuls = carMapper.getCarPuls(1);
            System.out.println(carPuls.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
