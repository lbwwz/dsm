package com.dsm.model.address;

import java.io.Serializable;

/**
 * 封装了包括：全国省份信息
 *
 * @author lbwwz
 */
public class ProvinceBean implements Serializable{

    private static final long serialVersionUID = -6069956097949897086L;
    private String provinceName;

    //邮编
    private String zipCode;

    public ProvinceBean() {
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "provinceBeans [provinceName=" + provinceName + ", zipCode=" + zipCode + "]";
    }


}
