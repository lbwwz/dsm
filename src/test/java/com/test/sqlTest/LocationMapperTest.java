package com.test.sqlTest;

import com.dsm.model.address.CityBean;
import com.dsm.model.address.Location;
import com.dsm.model.address.ProvinceBean;
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
public class LocationMapperTest {

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
     * 测试mybatis级联查询bean的自动注入
     *
     * 查询地址信息
     */
    @Test
    public void test(){
        String statement = "com.dsm.dao.IProvinceDao.getProvinces";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        List<ProvinceBean> user =  session.selectList(statement);
        System.out.println("打印对象："+user);
    }

    @Test
    public void test1(){
        String statement = "com.dsm.dao.ILocationDao.getLocation";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        Location user =  session.selectOne(statement,1);
        System.out.println("打印对象："+user);
    }

    /**
     * 测试mybatis级联查询bean的自动注入
     *
     * 查询地址信息
     */
    @Test
    public void test2(){
        String statement = "com.dsm.dao.ICityDao.getCities";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        List<CityBean> user =  session.selectList(statement,"420000");
        System.out.println("打印对象："+user);
    }

}
