package com.test.sqlTest;

import com.dsm.model.address.ShippingAddress;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/5
 *
 * @author : Lbwwz
 */
public class shippingAddressMapperTest {

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


    @Test
    public void test(){
        String statement = "com.dsm.dao.IShippingAddressDao.getConsigneeAddressList";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        List<ShippingAddress> user =  session.selectList(statement,1);
        System.out.println("打印对象："+user);
    }
}
