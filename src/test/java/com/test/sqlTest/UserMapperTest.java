package com.test.sqlTest;

import com.dsm.model.user.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by Lbwwz on 2016/7/28.
 */
public class UserMapperTest {

    private SqlSession session;

    @Before
    public void before(){
        //mybatis的配置文件
        String resource = "conf.xml";

        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = getClass().getClassLoader().getResourceAsStream(resource);

        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        //创建能执行映射文件中sql的sqlSession
        session = sessionFactory.openSession(true);
    }

    @After
    public void after(){
        session.clearCache();
        session.close();
    }


    /**
     * 测试user相关的查询操作
     */
    @Test
    public void test1(){
        String statement = "com.dsm.dao.IUserDao.queryUserByLogin";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql

        User user = new User();
        user.setEmail("353394368@qq.com");
        user = session.selectOne(statement,user);
        System.out.println(user);
    }

    /**
     * 测试user相关的查询操作
     */
    @Test
    public void test2(){
        String statement = "com.dsm.dao.IUserDao.getUserById";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement,25);
        System.out.println("打印对象："+user);
    }




    /**
     * 用户注册
     */
    @Test
    public void test3(){
        String statement = "com.dsm.dao.IUserDao.register";//映射sql的标识字符串
        User user = new User("1231s3", "123123123", 1, "18234251627", "12312@123.com");
        int i = session.insert(statement,user);
        System.out.println(user.getId());
    }

    @Test
    public void test4(){
        String statement = "com.dsm.dao.IUserDao.checkUserNameExist";//映射sql的标识字符串
        int i = session.selectOne(statement,"蚊子");
        System.out.println(i);
    }

    @Test
    public void test5(){
        String statement = "com.dsm.dao.IUserDao.updateHeadImage";//映射sql的标识字符串
        User user = new User(1,"//fileBase/headImages/1.jpg");
        int i = session.update(statement,user);
        System.out.println(user.getId());
    }

    @Test
    public void test6(){
        String statement = "com.dsm.dao.IUserDao.updateBaseInfo";
        User user = new User(1,1,"2016-12-12","707070707");
        session.update(statement,user);
    }

    @Test
    public void test7(){
        String statement = "com.dsm.dao.IUserDao.changePromotedType";
        User user = new User();
        user.setId(27);
        user.setPromotedType(1);
        System.out.println(session.update(statement,user));
    }


}
