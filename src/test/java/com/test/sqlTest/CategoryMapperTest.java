package com.test.sqlTest;

import com.dsm.model.product.CategoryBean;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/1/10
 *
 * @author : Lbwwz
 */
public class CategoryMapperTest {

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
        String statement = "com.dsm.dao.ICategoryDao.getRootCategory";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql

        List<CategoryBean> list =  session.selectList(statement,false);
        System.out.println("打印对象："+list);
    }

    @Test
    public void test2(){
        String statement = "com.dsm.dao.ICategoryDao.getChildCat";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        Map m = new HashMap<>();
        m.put("catId","2");
        m.put("status",true);
        List<CategoryBean> list =  session.selectList(statement,m);
        System.out.println("打印对象："+list);
    }

    @Test
    public void test3(){
        String statement = "com.dsm.dao.ICategoryDao.updateCategory";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        CategoryBean bean = new CategoryBean(1,"手机/手机配件",1);
        session.update(statement,bean);
        System.out.println("打印对象："+bean);
    }

    @Test
    public void test4(){
        Integer i = null;
        int j = i;
        System.out.println(j);
    }

    @Test
    public void test5(){
        List<CategoryBean> beans = new ArrayList<>();
        beans.add(new CategoryBean(0,"hehe1",1));
        beans.add(new CategoryBean(0,"hehe2",1));
        beans.add(new CategoryBean(0,"hehe3",1));

        String statement = "com.dsm.dao.ICategoryDao.addCategoryList";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        session.insert(statement,beans);
        System.out.println("打印对象："+beans);
    }

}
