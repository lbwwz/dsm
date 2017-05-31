package com.dsm.service.interfaces;

import com.dsm.model.product.CategoryBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/11/22
 *
 * @author : Lbwwz
 *
 * 类目操作的业务接口
 */
public interface ICategoryService {
    /**
     * 添加一组类目
     *
     * @param beans 一组类目信息的封装
     */
    boolean addCategoryList(List<CategoryBean> beans);

    /**
     * 获取parentId=0的所有类目(根节点)
     *
     * @return 所有的一级类目集合
     */
    List<CategoryBean> getRootCategory();


    List<CategoryBean> getRootCategoryIgnoreStatus();

    /**
     * 更新一条类目
     *
     * @param bean 要更新的类目更新信息的封装
     */
    CategoryBean updateCategory(CategoryBean bean);

    /**
     * 根据类目的ID查询他的所有子类目信息
     *
     * @param catId 类目ID
     * @param status 状态字段，1，可用；0，不可用；-1，忽略状态
     *
     * @return 传入类目ID对应的类目信息
     */
    List<CategoryBean> getChildCategory(Integer catId, Integer status);

    /**
     * 更改类目的状态
     *
     * @param catId 要更改状态的类目ID
     */
    boolean changeStatus(Integer catId);

    /**
     * 根据目录ID获取目录的所有链式上级目录
     *
     * @param catId 目录ID
     * @return 所有上级目录信息列表
     */
    @Deprecated
    List<CategoryBean> getLevelCatalog(int catId);


    /**
     * 根据目录ID获取目录的所有链式上级目录
     *
     * @param catId 目录ID
     * @return 该目录和上级目录所在的层级信息（getLevelCatalog的代替方法）
     */
    List<CategoryBean> getCatalogNavList(int catId);

    List<CategoryBean> getLeafCateLogList(int catId);



}
