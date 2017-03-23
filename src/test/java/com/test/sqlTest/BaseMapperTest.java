package com.test.sqlTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/3/7
 *
 * @author : Lbwwz
 */
public class BaseMapperTest {
    protected SqlSession session;
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
}
