package com.dsm.controller.user;

import com.dsm.common.utils.ServletToolUtils;
import com.dsm.common.utils.StringHandleUtils;
import com.dsm.common.utils.ValidateUtils;
import com.dsm.controller.common.BaseController;
import com.dsm.model.BackMsg;
import com.dsm.model.address.ShippingAddress;
import com.dsm.model.formData.IdentifyInfoDTO;
import com.dsm.model.formData.LocationDTO;
import com.dsm.model.formData.ShippingAddressDTO;
import com.dsm.model.formData.UserBaseInfoDTO;
import com.dsm.model.user.IdentifyInfo;
import com.dsm.model.user.User;
import com.dsm.service.interfaces.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Lbw on 2016/8/5.
 * <p>
 * 用户账户的验证等安全操作的 controller
 */
@Controller
@RequestMapping("/userHome")
public class AccountController extends BaseController {

    //用户信息操作的service
    @Autowired
    private IUserService userService;

    //地址库信息操作的service
    @Autowired
    private ILocationService locationService;

    //物流地址信息操作的service
    @Autowired
    private IShippingAddressService shippingAddressService;

    //身份认证信息操作的文件上传相关的service
    @Autowired
    private IIdentifyInfoService iIdentifyInfoService;

    // 文件上传相关的service
    @Autowired
    private IFileUploadService fileUploadService;


    /**
     * 请求用户账户中心
     *
     * @return /user/userHome/personalInfo.jsp
     */
    @RequestMapping("")
    public ModelAndView toUserHome() {
        ModelAndView view = new ModelAndView();

        view.setViewName("/user/userHome/safeCenter");
        return view;
    }


    /**
     * 请求用户身份认证首页
     *
     * @return /user/idAuthentication.jsp
     */
    @RequestMapping("idAuthentication")
    public String toIdAuthenticationPage() {
        return "/user/idAuthentication";
    }

    /**
     * 请求用户首页信息填写/设置的form表单页面
     *
     * @return /user/idAuthForm.jsp
     */
    @RequestMapping("idEditForm")
    public ModelAndView toEditIdFormPage() {
        ModelAndView view = new ModelAndView();

        User sessionUser = (User) getSession().getAttribute("user");
        int pt = sessionUser.getPromotedType();
        if(pt == 3){
            //认证通过用户无需进入编辑页面
            view.setViewName(InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/userHome/idAuthentication");
        }else if (pt != 0) {     //认证中
            //获取认证信息
            IdentifyInfo identifyInfo = iIdentifyInfoService.getIdentifyInfo(sessionUser.getId());
            view.addObject("identifyInfo", identifyInfo);
            //认证未通过，需要修改信息,增加提示信息
            if (pt == 2) {
                view.addObject("alertMsg", "认证信息未能通过管理员审核，请及时修改，以免影响您的审核通过！");
            }
        }
        view.setViewName("/user/idAuthForm");
        return view;
    }


    /**
     * 提交用户身份（实名认证）验证信息
//     * @param IdentifyInfoDTO 实名认证信息
     */
    @ResponseBody
    @RequestMapping("submitIdentifyInfo")
    public BackMsg submitIdentifyInfo(IdentifyInfoDTO identifyInfoDTO, String idCheckToken) {
        User sessionUser = (User) getSession().getAttribute("user");

        String errorMsg = checkIdentifyInfo(identifyInfoDTO);
        if (!StringUtils.isEmpty(errorMsg)) {
            return new BackMsg<>(1,null,errorMsg);
        }
        BackMsg backMsg;
        //校验信息重复提交
        if (ServletToolUtils.checkRepeatSubmit(getSession(), idCheckToken, "idCheckToken")) {

            identifyInfoDTO.setUserId(sessionUser.getId());
            int promotedType = sessionUser.getPromotedType();
            if (promotedType > 2) {   //已认证
                backMsg = new BackMsg<>(1, "", "您已经完成了身份认证，无需继续修改信息！");
            } else {        //认证中

                if (promotedType == 0) {
                    //添加信息
                    errorMsg = iIdentifyInfoService.addIdentifyInfo(identifyInfoDTO);
                } else {
                    //更新信息
                    errorMsg = iIdentifyInfoService.updateIdentifyInfo(identifyInfoDTO);
                }
                if(StringUtils.isNotEmpty(errorMsg)){
                    backMsg = new BackMsg<>(BackMsg.ERROR,null,errorMsg);
                }else{
                    backMsg = new BackMsg<>(BackMsg.CORRECT,"/user/idAuthentication","提交成功！");
                }
            }
        }else{
            backMsg = new BackMsg<>(BackMsg.ERROR,null,"请勿重复提交！");
        }
        return backMsg;
    }

    /**
     * 校验提交的用户地址信息是否合法
     *
     * @param identifyInfoDTO 身份信息封装对象
     * @return 错误信息，若为空表示信息无误
     */
    private String checkIdentifyInfo(IdentifyInfoDTO identifyInfoDTO) {
        if (StringUtils.isEmpty(identifyInfoDTO.getRealName()) || StringUtils.isEmpty(identifyInfoDTO.getIdCardNum())) {
            return "姓名或身份证号码不能为空！";
        }
        if (!ValidateUtils.isIdCardNum(identifyInfoDTO.getIdCardNum())) {
            return "请填写正确格式的身份证号码！";
        }
        if(identifyInfoDTO.getIdImageFace() == null || identifyInfoDTO.getIdImageBack() == null ){
            return "请补全正反面照片！";
        }
        return null;
    }


    /**
     * 请求用户个人信息设置页面及其他后台信息获取
     *
     * @param isSuccess 操作成功符号
     * @return /user/userHome/personalInfo.jsp
     */
    @RequestMapping("personalInfo")
    public ModelAndView toPersonalInfoPage(String isSuccess) {

        ModelAndView view = new ModelAndView();
        User user = (User) getSession().getAttribute("user");

        view.addObject("provinces", locationService.getProvinces());
        if (user.getDomicile() != null) {
            //查询并设置目录级别下相应的城市，区/县集合
            view.addObject("domicileCityRs", locationService.getCities(user.getDomicile().getProvince().getZipCode()));
            view.addObject("domicileDistrictRs", locationService.getDistricts(user.getDomicile().getCity().getZipCode()));
        }
        if (user.getHometown() != null) {
            //设置相应的城市，区/县集合
            view.addObject("hometownCityRs", locationService.getCities(user.getHometown().getProvince().getZipCode()));
            view.addObject("hometownDistrictRs", locationService.getDistricts(user.getHometown().getCity().getZipCode()));

        }
        if ("1".equals(isSuccess)) {
            view.addObject("backMsg", "设置个人信息操作成功");
        }
        view.setViewName("/user/userHome/personalInfo");
        return view;
    }

    /**
     * 提交用户的基本信息
     *
     * @param userBaseInfo     用户基本信息封装
     * @param hometownProvince 家乡省份编码
     * @param hometownCity     家乡城市编码
     * @param hometownDistrict 家乡区域编码
     * @param domicileProvince 居住地省份编码
     * @param domicileCity     居住地城市编码
     * @param domicileDistrict 居住地区域编码
     * @return 操作结果返回对象
     */
    @ResponseBody
    @RequestMapping("submitBaseInfo")
    public BackMsg submitBaseInfo(UserBaseInfoDTO userBaseInfo, String hometownProvince,
                                  String hometownCity, String hometownDistrict,
                                  String domicileProvince, String domicileCity,
                                  String domicileDistrict) {
        // 重复提交检测
        if (ServletToolUtils.checkRepeatSubmit(getRequest(), "token", "token")) {
            //获取当前用户的信息
            User sessionUser = (User) getSession().getAttribute("user");
            int userId = sessionUser.getId();

            userBaseInfo.setHometown(new LocationDTO(sessionUser.getDomicile().getLocationId(), domicileProvince, domicileCity, domicileDistrict));
            userBaseInfo.setDomicile(new LocationDTO(sessionUser.getHometown().getLocationId(), hometownProvince, hometownCity, hometownDistrict));

            //提交用户的基本信息（包含新增和修改）
            userBaseInfo.setUserId(userId);
            userService.submitBaseInfo(userBaseInfo);

            //更新session中的User信息
            resetSessionAttribute("user", userService.queryUserById(userId));
        } else {
            return new BackMsg<>(1, "", "请不要重复提交信息");
        }
        return new BackMsg<>(0, "/userHome/personalInfo?isSuccess=1", "信息修改成功");
    }


    /**
     * 请求头像设置页面
     *
     * @return /user/userHome/headImage.jsp
     */
    @RequestMapping("headImage")
    public String toSetHeadImg() {
        return "/user/userHome/headImage";
    }


    /**
     * 设置用户头像
     *
     * @param headImgInput 用户头像封装的文件流
     * @param view         视图信息封装对象
     * @return /user/userHome/headImage.jsp
     */
    @RequestMapping(value = "setHeadImage", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam MultipartFile headImgInput, ModelAndView view) {


        User user = ((User) getSession().getAttribute("user"));

        BackMsg<String> backMsg = fileUploadService.uploadFile(headImgInput, "/headImages",
                fileName -> user.getId() + "." + StringHandleUtils.getFileExt(fileName));

        //上传成功
        if (backMsg.getError() == 0) {
            userService.uploadHeadImage(new UserBaseInfoDTO(user.getId(), backMsg.getData()));
            //更新session中的User属性信息
//            updateSessionAttribute("user", user1 -> ((User) user1).setHeadImage(backMsg.getData()));
            user.setHeadImage(backMsg.getData());
        }
        view.addObject("backMsg", backMsg.getMessage());
        view.setViewName("redirect:/userHome/headImage");
        return view;
    }


    /**
     * 跳转到shoppingAddress页面之前需要准备的信息内容
     *
     * @param view 视图信息对象封装
     * @return /user/userHome/shippingAddress.jsp
     */
    @RequestMapping("shippingAddress")
    public ModelAndView toShoppingAddressPage(ModelAndView view, String isSuccess) {

        //返回设置省份信息
        view.addObject("provinces", locationService.getProvinces());

        //获取用户的收获地址信息，并将其封装到request中
        view.addObject("addresses", shippingAddressService.getConsigneeAddressList());

        if ("1".equals(isSuccess)) {
            view.addObject("backMsg", "地址信息设置成功！");
        }

        view.setViewName("/user/userHome/shippingAddress");
        return view;
    }

    /**
     * 提交收货地址信息
     *
     * @param formData 地址信息的form数据对象
     * @return 地址提交结果的返回数据对象
     */
    @ResponseBody
    @RequestMapping("submitConsigneeAddress")
    public BackMsg submitConsigneeAddress(ShippingAddressDTO formData) {
        BackMsg backMsg = null;
        String errorMsg = checkAddressDTO(formData);
        if (StringUtils.isEmpty(errorMsg)) {
            User sessionUser = (User) getSession().getAttribute("user");
            formData.setUserId(sessionUser.getId());
            // 重复提交检测
            if (ServletToolUtils.checkRepeatSubmit(getRequest(), "token", "token")) {
                shippingAddressService.submitConsigneeAddressInfo(formData);

                backMsg = new BackMsg<>(0, "/userHome/shippingAddress?isSuccess=1", "保存信息成功！");
            }

        } else {
            backMsg = new BackMsg<>(1, "", errorMsg);
        }
        return backMsg;
    }

    /**
     * 校验ShippingAddressDTO中相关信息的合法性
     *
     * @param formData 提交的第地址信息数据
     * @return 错误信息，若为空，则表示无异常
     */
    private String checkAddressDTO(ShippingAddressDTO formData) {
        String errorMsg;

        if (!ValidateUtils.isZipCode(formData.getZipCode())) {
            errorMsg = "请填写正确的邮编";
            return errorMsg;
        }

        if (StringUtils.isEmpty(formData.getMobile())) {
            errorMsg = "电话号码不能为空！";
            return errorMsg;
        }
        if (!ValidateUtils.isMobile(formData.getMobile())) {
            errorMsg = "请填写正确的电话号码！";
            return errorMsg;
        }
        return null;
    }


    /**
     * 修改地址信息请求需要执行的数据准备操作（修改信息按钮的页面信息准备）
     *
     * @param view      视图信息封装
     * @param addressId 要修改的地址id
     * @return /user/userHome/shippingAddress.jsp
     */
    @RequestMapping("reviseConsigneeAddress")
    public ModelAndView reviseConsigneeAddress(ModelAndView view, Integer addressId) {

        ShippingAddress consigneeAddressItem = shippingAddressService.getShippingAddressByAddressId(addressId);

        //返回设置省份, 城市/县/市, 区域 list信息
        view.addObject("provinces", locationService.getProvinces());
        view.addObject("cities", locationService.getCities(consigneeAddressItem.getLocation().getCity().getProvinceCode()));
        view.addObject("districts", locationService.getDistricts(consigneeAddressItem.getLocation().getDistrict().getCityCode()));

        //返回需要修改的用户的收获地址信息和地址库信息
        view.addObject("consigneeAddressItem", consigneeAddressItem);

        view.addObject("addresses", shippingAddressService.getConsigneeAddressList());
        view.setViewName("/user/userHome/shippingAddress");
        return view;
    }

    /**
     * 删除某条收获地址信息
     *
     * @param addressId 要删除的地址ID
     * @return 地址删除结果的返回数据对象
     */
    @ResponseBody
    @RequestMapping("deleteConsigneeAddress")
    public BackMsg deleteConsigneeAddress(Integer addressId) {
        BackMsg backMsg;

        String errorMsg;
        //执行删除操作，删除成功返回空对象，失败则返回相应的错误信息
        errorMsg = shippingAddressService.deleteShippingAddress(addressId);
        if (StringUtils.isEmpty(errorMsg)) {
            backMsg = new BackMsg<>(0, "/userHome/shippingAddress", "");
        } else {
            backMsg = new BackMsg<>(1, "", errorMsg);
        }
        return backMsg;
    }

    /**
     * 更改默认的收获地址
     *
     * @param addressId 地址ID
     * @return 默认地址重置的返回数据对象
     */
    @ResponseBody
    @RequestMapping("changeDefaultAddress")
    private BackMsg changeDefaultConsigneeAddress(Integer addressId) {
        BackMsg backMsg;

        if (shippingAddressService.resetDefaultAddress(addressId)) {
            backMsg = new BackMsg<>(0, "", "设置默认地址成功！");
        } else {
            backMsg = new BackMsg<>(0, "", "设置失败，请稍后再试！");
        }
        return backMsg;
    }

}
