package com.lq;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Util {


    public static SqlSessionFactory getSqlSessionFactory() throws IOException {
        //获取全局的配置文件
        InputStream inputStream = Resources.getResourceAsStream("resources/mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

}
