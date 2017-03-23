package com.dsm.service.impls;

import com.dsm.dao.ICategoryDao;
import com.dsm.model.product.CategoryBean;
import com.dsm.service.interfaces.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/11/22
 *
 * @author : Lbwwz
 * 类目操作的service实现类
 */
@Service("ICatalogService")
public class CategoryServiceImpl  implements ICategoryService{

//    Logger logger;

    @Resource
    private ICategoryDao categoryDao;

    @Override
    public boolean addCategoryList(List<CategoryBean> beans) {
        try{
            if(beans.size()>0 && categoryDao.addCategoryList(beans)>0)
                return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CategoryBean> getRootCategory() {
        return categoryDao.getRootCategory(1);
    }

    @Override
    public List<CategoryBean> getRootCategoryIgnoreStatus() {
        return categoryDao.getRootCategory(-1);
    }

    @Override
    public CategoryBean updateCategory(CategoryBean bean) {
        try{
            if(1==categoryDao.updateCategory(bean)){
                return categoryDao.getCategoryById(bean.getCatId());
            }

        }catch (Exception ex){
//            logger.info("更新状态失败！");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CategoryBean> getChildCategory(Integer catId, Integer status) {

        Map<String, Object> params = new HashMap<>();
        params.put("catId", catId);
        params.put("status", status > 1 ? -1 : status);
        return categoryDao.getChildCategory(params);
    }

    @Override
    public boolean changeStatus(Integer catId) {
        try{
            if(1==categoryDao.changeStatus(catId))
                return true;
        }catch (Exception ex){
//            logger.info("变更类目状态失败！");
            ex.printStackTrace();
        }
        return  false;
    }


    @Override
    public List<CategoryBean> getLevelCatalog(int catId) {
        List<CategoryBean> catalogBeans = new ArrayList<>();

        CategoryBean catalogBeanTemp = categoryDao.getCategoryById(catId);
        //循环查询，并将类目对象添加到list集合中
        while (catalogBeanTemp.getParentId() != 0) {
            catalogBeans.add(catalogBeanTemp);
            catalogBeanTemp = categoryDao.getCategoryById(catalogBeanTemp.getParentId());
        }
        //添加根目录
        catalogBeans.add(catalogBeanTemp);
        //反转集合中元素的顺序，使顺序由上级到下级
        Collections.reverse(catalogBeans);
        return catalogBeans;
    }
}
