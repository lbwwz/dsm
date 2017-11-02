package com.dsm.model.formData;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/10/23
 *
 * @author : Lbwwz
 */
public class OrderCreateDto {
    //订单商品
    private String itemString;
    //订单的收货地址
    private String addressString;
    //订单收货人
    private String receiver;
    //收货人电话
    private String phoneNum;
    //给卖家的留言
    private String leaveWords;

    public String getItemString() {
        return itemString;
    }

    public void setItemString(String itemString) {
        this.itemString = itemString;
    }

    public String getAddressString() {
        return addressString;
    }

    public void setAddressString(String addressString) {
        this.addressString = addressString;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getLeaveWords() {
        return leaveWords;
    }

    public void setLeaveWords(String leaveWords) {
        this.leaveWords = leaveWords;
    }
}
