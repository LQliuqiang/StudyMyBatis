package com.lq.day5;

import com.lq.Util;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;

public class Main {

    /**
     * MyBatis 缓存机制 --- 持久化层框架比较重要的机制---提升查询效率
     * 一级缓存（本地缓存）---->默认SqlSession缓存--默认是开启的
     * 二级缓存（全局缓存）---->需要手动开启与配置，是namespace级别的。MyBatis还提供了缓存接口，让我们自定义实现二级缓存。
     *
     * 缓存案例解释：就是多个用户访问同一个数据，且在访问这个数据的访问量较高的情况下，我们服务器端就可以将这个从数据库中
     *           查出来的数据进行缓存，这样做可以避免每次 多个用户查询这个数据时，都要走这个流程：
     *           客户端链接服务器-->服务器去数据库查询数据---服务器将数据库中查询的的数据进行转换格式--再将数据发送到客户端；
     *           而是改为：
     *           客户端链接服务器---服务器取缓存数据---再将数据发送到客户端；
     *           好处：1.更快速的提高了客户端查询数据的效率；
     *                 2.降低了对数据库的高访问量。提高数据库性能。
     *
     *
     *   @1: 一级缓存：（本地缓存）：sqlSession级别的缓存。一级缓存是一直开启的；SqlSession级别的一个Map(存放在Map集合中)
     * 	 * 		与数据库同一次会话期间查询到的数据会放在本地缓存中。
     * 	 * 		以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库；
     * 	 *
     * 	 * 		一级缓存失效情况（没有使用到当前一级缓存的情况，效果就是，还需要再向数据库发出查询）：
     * 	 * 		1、sqlSession不同。
     * 	 * 		2、sqlSession相同，查询条件不同.(当前一级缓存中还没有这个数据)
     * 	 * 		3、sqlSession相同，两次查询之间执行了增删改操作(这次增删改可能对当前数据有影响)
     * 	 * 		4、sqlSession相同，手动清除了一级缓存（缓存清空） sqlSession.clearCache();
     *
     * 	 @2:  二级缓存：（全局缓存）：基于namespace级别的缓存：一个namespace对应一个二级缓存：
     * 	 * 		工作机制：
     * 	 * 		1、一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中；
     * 	 * 		2、如果会话关闭；一级缓存中的数据会被保存到二级缓存中；新的会话查询信息，就可以参照二级缓存中的内容；
     * 	 * 		3、sqlSession===UserInfoMapper==>UserInfo
     * 	 * 						CarMapper===>Car
     * 	 * 			不同namespace查出的数据会放在自己对应的缓存中（map）
     * 	 * 			效果：数据会从二级缓存中获取
     * 	 * 			【注意】：查出的数据都会被默认先放在一级缓存中。
     * 	 * 				只有会话提交或者关闭以后，一级缓存中的数据才会转移到二级缓存中
     * 	 * 		使用：
     * 	 * 			1）、开启全局二级缓存配置：<setting name="cacheEnabled" value="true"/>
     * 	 * 			2）、去xxxMapper.xml中配置使用二级缓存：
     * 	 * 				<cache></cache>
     * 	 * 			3）、我们的POJO需要实现序列化接口
     *
     *
     *    @3: 和缓存有关的设置/属性：
     * 			1）、cacheEnabled=true：false：关闭缓存（二级缓存关闭）(一级缓存一直可用的)
     * 			2）、每个select标签都有useCache="true"：
     * 					false：不使用缓存（一级缓存依然使用，二级缓存不使用）
     * 			3）、【每个增删改标签的：flushCache="true"：（一级二级都会清除）】
     * 					增删改执行完成后就会清楚缓存；
     * 					测试：flushCache="true"：一级缓存就清空了；二级也会被清除；
     * 					查询标签：flushCache="false"：
     * 						如果flushCache=true;每次查询之后都会清空缓存；缓存是没有被使用的；
     * 			4）、sqlSession.clearCache();只是清楚当前session的一级缓存；
     * 			5）、localCacheScope：本地缓存作用域：（一级缓存SESSION）；当前会话的所有数据保存在会话缓存中；
     * 								STATEMENT：可以禁用一级缓存；
     *
     *     @4: 第三方缓存整合：
     * 	 *		1）、导入第三方缓存包即可；
     * 	 *		2）、导入与第三方缓存整合的适配包；官方有；
     * 	 *		3）、mapper.xml中使用自定义缓存
     * 	 *		<cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>
     */
    public static void main(String[] args){
        try {
            SqlSessionFactory sqlSessionFactory = Util.getSqlSessionFactory();
//            testOneCache(sqlSessionFactory);
            testSecondCache(sqlSessionFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //测试一级缓存
    private static void testOneCache(SqlSessionFactory sqlSessionFactory){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        UserInfo userInfo1 = mapper.getUserInfoById(2);
        UserInfo userInfo2 = mapper.getUserInfoById(2);
        System.out.println(userInfo1==userInfo2);
        sqlSession.close();
    }

    //测试二级缓存 需要看日志检测 有木有发送两次sql语句
    private static void testSecondCache(SqlSessionFactory sqlSessionFactory){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserInfoMapper mapper = sqlSession.getMapper(UserInfoMapper.class);
        UserInfo userInfo1 = mapper.getUserInfoById(2);
        System.out.println(userInfo1);
        sqlSession.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserInfoMapper mapper2 = sqlSession2.getMapper(UserInfoMapper.class);
        UserInfo userInfo2 = mapper2.getUserInfoById(2);
        System.out.println(userInfo2);
        sqlSession2.close();
    }

}
