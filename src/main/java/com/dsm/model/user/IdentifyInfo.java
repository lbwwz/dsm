package com.dsm.model.user;

import org.apache.commons.lang3.StringUtils;

/**
 * 用户的验证（真实）身份信息
 * @author lbwwz
 */
public class IdentifyInfo {

	private Integer IdentifyId;

	private Integer userId;
	//用户的真实姓名
	private String realName;
	private String idCardNum;
	//提交的验证图片的路径
	private String imageUrls;
	
	public IdentifyInfo() {}

	
	public IdentifyInfo(Integer userId, String realName, String idCardNum, String imageUrls) {
		super();
		this.userId = userId;
		this.realName = realName;
		this.idCardNum = idCardNum;
		this.imageUrls = imageUrls;
	}

	public int getIdentifyId() {
		return IdentifyId;
	}

	public void setIdentifyId(int identifyId) {
		IdentifyId = identifyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
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

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public String getIdImageFace() {
		return StringUtils.isNotEmpty(imageUrls) ? imageUrls.split(",")[0] : null;
	}

	public String getIdImageBack() {
		return StringUtils.isNotEmpty(imageUrls) ? imageUrls.split(",")[1] : null;
	}


	@Override
	public String toString() {
		return "IdentifyInfo [userId=" + userId + ", realName=" + realName + ", idCardNum=" + idCardNum + ", imageUrls="
				+ imageUrls + "]";
	}



}
