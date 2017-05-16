package com.test.serviceTest;

import com.alibaba.fastjson.JSONObject;
import com.dsm.model.formData.ReleaseProductFormDTO;
import com.dsm.service.interfaces.IProductService;
import com.test.common.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/5/8
 *
 * @author : Lbwwz
 *         <p/>
 *         productService测试类
 */
public class ProductServiceTest extends BaseJunitTest {

    @Autowired
    private IProductService productService;

    @Test
    public void releaseProductTest() {

        String postData = "{\n" +
                "\t\"baseAttrInfo\": [\n" +
                "\t\t\"1|主屏尺寸|#|zdy\",\n" +
                "\t\t\"2|主屏分辨率|17|640*1136\",\n" +
                "\t\t\"3|核心数|21|双核\",\n" +
                "\t\t\"\",\n" +
                "\t\t\"\",\n" +
                "\t\t\"\",\n" +
                "\t\t\"\",\n" +
                "\t\t\"\",\n" +
                "\t\t\"9|内存（RAM）|39|2G\"\n" +
                "\t],\n" +
                "\t\"brand\": 0,\n" +
                "\t\"catId\": 14,\n" +
                "\t\"customBaseAttrName\": [\"111\"],\n" +
                "\t\"customBaseAttrValue\": [\"11\"],\n" +
                "\t\n" +
                "\t\"detailContent\": \"<p>\\r\\n\\t123\\r\\n</p>\\r\\n<p>\\r\\n\\t123<img src=\\\"/fileBase/fileZone/1/image/detail/be94dc58a6ad8fdfcfd65d92ffaf24_1488.jpg\\\" width=\\\"100%\\\" alt=\\\"\\\" />\\r\\n</p>\",\n" +
                "\t\"imgItem\": [\n" +
                "\t\t\"/fileBase/fileZone/1/image/product/dd15de4b246a41da591bbf1330f9dacb_3674_index.jpg\",\n" +
                "\t\t\"/fileBase/fileZone/1/image/product/a5a738e98034a447929637f0aab74e97_1678_index.jpg\"\n" +
                "\t],\n" +
                "\t\"keywords\": \"1;2;3;4\",\n" +
                "\t\"mainImgItem\": \"/fileBase/fileZone/1/image/product/dd15de4b246a41da591bbf1330f9dacb_3674_index.jpg\",\n" +
                "\t\"produceBrief\": \"123123123123213\",\n" +
                "\t\"productCount\": [\n" +
                "\t\t\"12\",\n" +
                "\t\t\"13\",\n" +
                "\t\t\"44\",\n" +
                "\t\t\"32\"\n" +
                "\t],\n" +
                "\t\"productName\": \"娃哈哈\",\n" +
                "\t\"productMarketPrice\": 123,\n" +
                "\t\"property\": [\n" +
                "\t\t\"8|42|主存|2G;15|47|测试1|内存\",\n" +
                "\t\t\"8|42|主存|2G;15|48|测试1|B\",\n" +
                "\t\t\"8|44|主存|4G;15|47|测试1|内存\",\n" +
                "\t\t\"8|44|主存|4G;15|48|测试1|B\"\n" +
                "\t],\n" +
                "\t\"productPrice\": [\n" +
                "\t\t123,\n" +
                "\t\t123,\n" +
                "\t\t134,\n" +
                "\t\tnull\n" +
                "\t],\n" +
                "\t\"customProductNo\": [\n" +
                "\t\t\"1231\",\n" +
                "\t\t\"1233\",\n" +
                "\t\t\"1234\",\n" +
                "\t\t\"1235\"\n" +
                "\t]\n" +
                "}";
        ReleaseProductFormDTO releaseProductFormDTO = JSONObject.parseObject(postData, ReleaseProductFormDTO.class);

        System.out.println("\n" + releaseProductFormDTO);
        System.out.println(productService.releaseProduct(releaseProductFormDTO));


    }

    @Test
    public void getProductDetailTest() {

        System.out.println(productService.getProductDetail(1));
    }

//    @Resource
//    private





}
