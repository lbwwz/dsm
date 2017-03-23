package com.dsm.dao;

import com.dsm.model.product.AttrValueBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关于管理员设置的属性值的一些操作
 * @author Think
 *
 */
@Repository
public interface IAttrValueDao {

	/**
	 * 根据商品的属性ID获取该属性下所有的初始属性值
	 * @param attrId
	 * @return
	 */
	List<AttrValueBean> getValues(Integer attrId);

	/**
	 * 更改具体属性的状态
	 * @param valueId
	 */
	void changeStatus(Integer valueId);

	/**
	 * 为数据表中批量添加属性的属性值
	 * @param args
	 */
	void batchAddAttrValues(Object[] ... args);

	/**
	 * 根据商品的属性ID获取该属性下状态可用的初始属性值
	 * @param attrId
	 * @return
	 */
	List<AttrValueBean> getUsableValues(int attrId);

}
