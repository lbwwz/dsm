package com.dsm.dao;

import com.dsm.model.product.CategoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 关于类目业务的操作
 *
 * @author lbwwz
 */

@Repository
public interface ICategoryDao {

    /**
     * 添加一组类目
     *
     * @param beans 一组类目信息的封装
     */
    Integer addCategoryList(List<CategoryBean> beans);

    /**
     * 根据目录ID获取相应的目录信息
     *
     * @param catId 目录ID
     * @return 目录ID对应的目录信息
     */
    CategoryBean getCategoryById(Integer catId);

    /**
     * 获取parentId=0的所有类目(根节点)
     *
     * @param status 状态字段，1，可用；0，不可用；-1，忽略状态
     * @return 所有的一级类目集合
     */
    List<CategoryBean> getRootCategory(Integer status);


    /**
     * 更新一条类目
     *
     * @param bean 要更新的类目更新信息的封装
     */
    Integer updateCategory(CategoryBean bean);

    /**
     * 根据类目的ID查询他的所有子类目信息
     *
     * @param param 封装参数的 map（参数如下）
     *              ├── catId:类目ID
     *              └── status:状态字段，负数表示忽略该条件
     * @return 传入类目ID对应的类目信息
     */
    List<CategoryBean> getChildCategory(Map param);

    /**
     * 更改类目的状态
     *
     * @param catId 要更改状态的类目ID
     */
    Integer changeStatus(Integer catId);


    /**
     * 获取该类目所在的父层级列表信息
     *
     * @param catId 类目id
     * @return 层级目录信息列表
     */
    List<CategoryBean> getCategoryNavList(Integer catId);


    /**
     * 获取该类目子目录结构列表信息
     *
     * @param catId 类目id
     * @return 层级目录信息列表
     */
    List<CategoryBean> getTreeCategoryList(Integer catId);
}
