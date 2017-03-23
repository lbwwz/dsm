package com.test.sqlTest;

import com.dsm.model.product.AttrValueBean;
import com.dsm.model.product.ProductAttrBean;
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
 * Date: 2017/1/16
 *
 * @author : Lbwwz
 */
public class ProductAttrMapperTest {

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
    public void test1(){
        String statement = "com.dsm.dao.IProductAttrDao.getValuesByAttrId";//映射sql的标识字符串

        Map<String ,Object> m = new HashMap<>();
        m.put("attrId",1);
        m.put("status",1);
        //执行查询返回一个唯一user对象的sql
        List<ProductAttrBean> li=  session.selectList(statement,m);
        System.out.println("打印对象："+li);
    }

    @Test
    public void test2(){
        String statement = "com.dsm.dao.IProductAttrDao.getAttrByCat";//映射sql的标识字符串


        //执行查询返回一个唯一user对象的sql
        List<ProductAttrBean> li=  session.selectList(statement,14);
        System.out.println("打印对象："+li);
    }

    @Test
    public void test3(){
        String statement = "com.dsm.dao.IProductAttrDao.getAttrNameByCat";//映射sql的标识字符串

        //执行查询返回一个唯一user对象的sql
        Map<String ,Object> m = new HashMap<>();
        m.put("catId",14);
        m.put("status",-1);
        List<ProductAttrBean> li=  session.selectList(statement,m);
        System.out.println("打印对象："+li);
    }

    @Test
    public void test4(){
        String statement = "com.dsm.dao.IProductAttrDao.changeAttrStatus";//映射sql的标识字符串

        //执行查询返回一个唯一user对象的sql
//        Map<String ,Object> m = new HashMap<>();
//        m.put("catId",14);
//        m.put("status",-1);
        int i = session.update(statement,1);
        System.out.println("打印对象："+i);
    }

    @Test
    public void test5(){
        String statement = "com.dsm.dao.IProductAttrDao.getValuesByAttrId";//映射sql的标识字符串

        //执行查询返回一个唯一user对象的sql
        Map<String ,Object> m = new HashMap<>();
        m.put("attrId",1);
        m.put("status",-1);
        List<AttrValueBean> i = session.selectList(statement,m);
        System.out.println("打印对象："+i);
    }

    @Test
    public void test6(){
        String statement = "com.dsm.dao.IProductAttrDao.AddAttrValues";//映射sql的标识字符串

        //执行查询返回一个唯一user对象的sql
        AttrValueBean bean = new AttrValueBean();
        bean.setAttrId(1);
        bean.setStatus(1);
        bean.setValueName("123123");
        List<AttrValueBean> li = new ArrayList<>();
        li.add(bean);
        Integer i = session.insert(statement,li);
        System.out.println("打印对象："+i);
    }

    @Test
    public void test7(){
        String statement = "com.dsm.dao.IProductAttrDao.updateAttrValue";//映射sql的标识字符串

        //执行查询返回一个唯一user对象的sql
        AttrValueBean bean = new AttrValueBean(33,"电阻屏");
        int i = session.update(statement,bean);
        System.out.println("打印对象："+i);
    }

}
