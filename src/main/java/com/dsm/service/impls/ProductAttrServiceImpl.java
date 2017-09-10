package com.dsm.service.impls;

import com.dsm.dao.IProductAttrDao;
import com.dsm.model.product.AttrValueBean;
import com.dsm.model.product.ProductAttrBean;
import com.dsm.service.interfaces.IProductAttrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/1/17
 *
 * @author : Lbwwz
 */
@Service("IProductAttrService")
public class ProductAttrServiceImpl implements IProductAttrService {


    @Resource
    private IProductAttrDao productAttrDao;


    @Override
    public List<ProductAttrBean> getAttrByCat(int catId) {
        return productAttrDao.getAttrByCat(catId);
    }

    @Override
    public List<ProductAttrBean> getAttrNameByCat(int catId, int status) {
        return productAttrDao.getAttrNameByCat(catId,status);
    }

    @Override
    public ProductAttrBean getUsableAttrById(int attrId) {
        return productAttrDao.getUsableAttrById(attrId);
    }

    @Override
    public List<ProductAttrBean> addAttrInfo(Integer catId, Integer isSale, Integer isKey, Integer isMust, String... attrsName) {
        List<ProductAttrBean> li = new ArrayList<>();
        for (String name : attrsName)
            li.add(new ProductAttrBean(catId, name, isSale, isKey, isMust));
        try {
            if (productAttrDao.addAttr(li) > 0)
                return li;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AttrValueBean> getAttrValues(int attrId, int status) {
        return productAttrDao.getValuesByAttrId(attrId,status);
    }

    @Override
    public AttrValueBean updateAttrValue(int valueId, String attrValue) {

        AttrValueBean bean = new AttrValueBean(valueId,attrValue);
        try {
            if (1 == productAttrDao.updateAttrValue(bean)) {

                return productAttrDao.getValueById(bean.getValueId(),-1);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    public ProductAttrBean updateAttrInfo(ProductAttrBean bean) {
        try {
            if (1 == productAttrDao.updateAttr(bean)) {
                return productAttrDao.getAttrNameById(bean.getAttrId(),-1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean changeAttrStatus(Integer attrId) {
        try {
            productAttrDao.changeAttrStatus(attrId);
            return true;
        } catch (Exception ex) {
//            logger.info("更新状态失败！");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AttrValueBean> addAttrValueInfo(Integer attrId, Integer status, String... valuesName) {
        List<AttrValueBean> li = new ArrayList<>();
        for(String value: valuesName){
            li.add(new AttrValueBean(value,attrId,status));
        }
        try{
            if(productAttrDao.addAttrValues(li)>0)
                return li;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changeAttrValueStatus(Integer valueId) {
        try {
            productAttrDao.changeValueStatus(valueId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ProductAttrBean> getKeyAttrList(Integer catId) {
        if(catId<1){
            return null;
        }
        return productAttrDao.getKeyAttrInfoList(catId);
    }


}
