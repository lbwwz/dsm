package com.dsm.service.impls;

import com.dsm.common.utils.ServletToolUtils;
import com.dsm.common.utils.StringHandleUtils;
import com.dsm.dao.IIdentifyInfoDao;
import com.dsm.dao.IUserDao;
import com.dsm.model.BackMsg;
import com.dsm.model.formData.IdentifyInfoDTO;
import com.dsm.model.user.IdentifyInfo;
import com.dsm.model.user.User;
import com.dsm.service.interfaces.IFileUploadService;
import com.dsm.service.interfaces.IIdentifyInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2016/9/5
 *
 * @author : Lbwwz
 */

@Service("IIdentifyInfoService")
public class IdentifyInfoServiceImpl implements IIdentifyInfoService {

    @Resource
    private IIdentifyInfoDao identifyInfoDao;

    @Resource
    private IFileUploadService fileUploadService;

    @Resource
    private IUserDao userDao;

    @Transactional
    @Override
    public String addIdentifyInfo(IdentifyInfoDTO info) {

        String checkMsg = checkInfo(info, 0);
        if (StringUtils.isNotEmpty(checkMsg)) {
            return checkMsg;
        }

        //校验通过
        MultipartFile[] files = {info.getIdImageFace(), info.getIdImageBack()};
        String imageUrls = "", errorMag = null;
        try {
            //上传图片，命名：*_1表示正面，*_2表示背面
            List<BackMsg<String>> MsgList = fileUploadService.uploadFiles(files, "/idImages",
                    (fileName, index) -> info.getUserId() + "_" + (index + 1) + "." + StringHandleUtils.getFileExt(fileName));

            for (BackMsg msg : MsgList) {
                if (msg.getError() != 0) {
                    errorMag = msg.getMessage();
                    break;
                }
                imageUrls += msg.getData() + ",";
            }

            if (StringUtils.isEmpty(errorMag)) {  //上传成功
                imageUrls = imageUrls.substring(0, imageUrls.length() - 1);

                if (identifyInfoDao.addIdentifyInfo(
                        new IdentifyInfo(info.getUserId(), info.getRealName(), info.getIdCardNum(), imageUrls)) > 0) {

                    /*
                     * 更新用户的身份认证状态
                     */
                    if (userDao.changePromotedType(new User(info.getUserId(), 1)) > 0) {
                        ((User) SecurityUtils.getSubject().getSession().getAttribute("user")).setPromotedType(1);
                    }
                    return null;
                }
            } else {
                errorMag = "操作失败，请重试！";
            }

        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ServletToolUtils.rollBackToken();
            errorMag = "系统异常，请稍后重试！";
            ex.printStackTrace();
        }
        return errorMag;
    }

    /**
     * 校验用户提交的信息
     *
     * @param info   用户身份信息
     * @param option 类型：0，添加；其他，修改
     * @return 异常的提示信息，无异常则返回null
     */
    private String checkInfo(IdentifyInfoDTO info, int option) {

        if (StringUtils.isEmpty(info.getIdCardNum()) || StringUtils.isEmpty(info.getIdCardNum())) {
            return "姓名和身份证号不能为空！";
        }

        if (option == 0) {
            if (info.getIdImageFace() == null || info.getIdImageBack() == null)
                return "身份证正反面照片不能为空！";
        }
        return null;
    }

    @Override
    public IdentifyInfo getIdentifyInfo(long userId) {
        return identifyInfoDao.getIdentifyInfo(userId);
    }

    @Transactional
    @Override
    public String updateIdentifyInfo(IdentifyInfoDTO info) {
        String checkMsg = checkInfo(info, 0);
        if (StringUtils.isNotEmpty(checkMsg)) {
            return checkMsg;
        }

        try {
            IdentifyInfo identifyInfo = identifyInfoDao.getIdentifyInfo(info.getUserId());
            if (info.equalsPrimaryInfo(identifyInfo)) {
                return "请完成信息修改再进行提交！";
            }

            String faceImg = null, backImg = null;
            if (info.getIdImageFace() != null) {
                BackMsg<String> msg = fileUploadService.uploadFile(info.getIdImageFace(), "/idImages",
                        (fileName) -> info.getUserId() + "_" + 1 + "." + StringHandleUtils.getFileExt(fileName));
                if (msg.getError() == BackMsg.ERROR) {
                    return msg.getMessage();
                }
                faceImg = msg.getData();
            }
            if (info.getIdImageBack() != null) {
                BackMsg<String> msg = fileUploadService.uploadFile(info.getIdImageBack(), "/idImages",
                        (fileName) -> info.getUserId() + "_" + 2 + "." + StringHandleUtils.getFileExt(fileName));
                if (msg.getError() == BackMsg.ERROR) {
                    return msg.getMessage();
                }
                backImg = msg.getData();
            }

            faceImg = (faceImg == null ? identifyInfo.getIdImageFace() : faceImg);
            backImg = (backImg == null ? identifyInfo.getIdImageBack() : backImg);
            identifyInfo.setImageUrls(faceImg + "," + backImg);
            //更新信息
            if (identifyInfoDao.updateIdentifyInfo(identifyInfo) > 0) {
            /*
             * 更新用户的身份认证状态
             */
                if (userDao.changePromotedType(new User(info.getUserId(), 1)) > 0) {
                    ((User) SecurityUtils.getSubject().getSession().getAttribute("user")).setPromotedType(1);
                }
                return null;
            } else {
                return "操作失败，请重试！";
            }
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ServletToolUtils.rollBackToken();
            ex.printStackTrace();
        }
        return "系统异常，请稍后重试！";

    }
}
