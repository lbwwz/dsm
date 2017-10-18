package com.dsm.model.formData;

import com.dsm.model.user.IdentifyInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/2/8
 *
 * @author : Lbwwz
 */
public class IdentifyInfoDTO implements Serializable {

    private static final long serialVersionUID = -4587375224126116149L;
    private Long userId;
    //用户的真实姓名
    private String realName;
    private String idCardNum;
    //身份认证图片正面
    private MultipartFile idImageFace;
    //身份认证图片背面
    private MultipartFile idImageBack;

    public IdentifyInfoDTO() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public MultipartFile getIdImageFace() {
        return idImageFace;
    }

    public void setIdImageFace(MultipartFile idImageFace) {
        this.idImageFace = idImageFace;
    }

    public MultipartFile getIdImageBack() {
        return idImageBack;
    }

    public void setIdImageBack(MultipartFile idImageBack) {
        this.idImageBack = idImageBack;
    }


    /**
     * 对比提交的表单信息数据和原始身份信息数据相比是否发生了更改
     * @param o 原始信息数据
     * @return {@link Boolean}
     */
    public boolean equalsPrimaryInfo(IdentifyInfo o) {
        if(o == null)return false;
        if (realName != null ? !realName.equals(o.getRealName()) : o.getRealName() != null) return false;
        if (idCardNum != null ? idCardNum.equals(o.getIdCardNum()) : o.getIdCardNum() != null) return false;

        return idImageFace == null && idImageBack == null;
    }


}
