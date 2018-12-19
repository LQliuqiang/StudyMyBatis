package com.lq.day1;

import com.lq.Util;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    //SqlSession是非线程安全的，所以将sqlSession定义为成员变量是错误的写法。
    // 因为将将sqlSession定义为成员变量，就会到时多线程共享一个sqlSession实例，而sqlSession在每次用完之后都必须关闭掉。
    //这样会导致在多个线程执行在竞争同意个sqlSession的资源下，可能B线程优先执行且关闭了sqlSession，
    // 那么A线程与C线程就在使用sqlSession时，sqlSession就已经关闭了。
    // 所以正确的做法是将SqlSession定义为局部变量，这样每个线程执行时，都会实例化不同的sqlSession实例。
//    private SqlSession sqlSession;

    public static void main(String[] args){
        SqlSession sqlSession = null;
        try {

            sqlSession = Util.getSqlSessionFactory().openSession();

            //第二种方式操作 --- 接口式编程
//            UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
//            UserInfo userInfo = mapper.getUserInfoById(1);

            //第一种方式操作---不需要接口
//            UserInfo userInfo = sqlSession.selectOne("com.lq.day1.UserInfoMapper.getUserInfoById",1);

            UserInfoMapperAnnotation mapperAnnotation = sqlSession.getMapper(UserInfoMapperAnnotation.class);
            UserInfo userInfo = mapperAnnotation.getUserInfoById(1);
            System.out.println(userInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }

    }

}
