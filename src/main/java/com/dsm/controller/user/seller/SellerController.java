package com.dsm.controller.user.seller;

import com.dsm.common.DsmConcepts;
import com.dsm.common.utils.ServletToolUtils;
import com.dsm.controller.common.BaseController;
import com.dsm.model.BackMsg;
import com.dsm.model.formData.ReleaseProductFormDTO;
import com.dsm.model.product.CategoryBean;
import com.dsm.model.product.ProductAttrBean;
import com.dsm.service.interfaces.ICategoryService;
import com.dsm.service.interfaces.IFileUploadService;
import com.dsm.service.interfaces.IProductAttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/1/23
 *
 * @author : Lbwwz
 *         卖家相关的操作
 */
@Controller
@RequestMapping("/user/seller")
public class SellerController extends BaseController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductAttrService productAttrService;

    @Autowired
    private IFileUploadService fileUploadService;

    /**
     * 店铺主页
     */
    @RequestMapping("/")
    public String shopIndex(Map<String, Object> m) {

        return "/user/seller/shopIndex";
    }


    /**
     * 发布商品页面
     */
    @RequestMapping("/releaseProductPage")
    public String goToReleaseProduct(Map<String, Object> m) {
        m.put("rootCat", categoryService.getRootCategoryIgnoreStatus());


        return "/user/seller/releaseProduct-selectCat";
    }

    @RequestMapping("/releaseProductForm")
    public String releaseProductForm(Map<String, Object> m, Integer childCat) {
        if (childCat ==  null ) {
            return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/user/seller/releaseProductPage";
        }

//        //预存信息编号，唯一
//        long productEditNo = System.currentTimeMillis();
//        m.put("productEditNo"+productEditNo,System.currentTimeMillis());

        //查询必要的信息
        List<CategoryBean> catLeverList = categoryService.getLevelCatalog(childCat);
        m.put("productCatMenu", catLeverList);
        m.put("catId",childCat);
        List<ProductAttrBean> productAttrBeanList = productAttrService.getAttrByCat(childCat);
        m.put("productAttrBeans", productAttrBeanList);
        return "/user/seller/releaseProduct-writeInfo";
    }

    @RequestMapping("/releaseProductForm_detail")
    public String releaseProductForm_detail(Map<String, Object> m, Integer childCat) {
        return null;
    }

    /**
     * 上传详情页商品图片
     *
     * @param productImgUpload 图片文件集合
     */
    @ResponseBody
    @RequestMapping("/uploadProductImg")
    public List<BackMsg> uploadProductImg(@RequestParam MultipartFile[] productImgUpload) {
        return fileUploadService.uploadFiles(productImgUpload, "/fileZone/" + getSessionUser().getId() + "/image/product");
    }

    /**
     * 商品图文详情图片上传
     *
     * @param imgFile 图片文件集合
     */
    @ResponseBody
    @RequestMapping("/uploadDetailImg")
    public Map<String, Object> uploadDetailImg(@RequestParam MultipartFile imgFile) {
        BackMsg backMsg = fileUploadService.uploadFile(imgFile, "/fileZone/" + getSessionUser().getId() + "/image/detail");
        Map<String, Object> m = new HashMap<>();
        m.put("error", backMsg.getError());
        m.put("url", backMsg.getData());
        //设置图片宽度为100%
        m.put("width", "100%");
        return m;
    }

    /**
     *
     */

    @ResponseBody
    @RequestMapping("/releaseProductInfo")
    public BackMsg releaseProductInfo(ReleaseProductFormDTO releaseProductFormDTO,String token){
        //校验提交的信息

        if (ServletToolUtils.checkRepeatSubmit(getRequest(), "token", "token")) {

        String errorMsg = checkReleaseProductFormInfo(releaseProductFormDTO);
        if(StringUtils.isNotEmpty(errorMsg)){
            return new BackMsg(DsmConcepts.ERROR,"",errorMsg);
        }
        //校验通过
        System.out.println(releaseProductFormDTO);
        }else{

        }
        return null;

    }

    private String checkReleaseProductFormInfo(ReleaseProductFormDTO releaseProductFormDTO) {
        String msg = null;



        return msg;
    }
}
