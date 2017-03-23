package com.dsm.model.user;

import com.dsm.model.address.Location;
import com.dsm.model.address.ShippingAddress;
import com.dsm.model.seller.Shop;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = -5248921928915633135L;
    private int id;

    private String userName;

    private String password;

    private int sex;

    private String mobile;

    private String email;

    private String birthday;

    private String qq;
    //角色  0，买家；1，卖家；
    private int role;
    //实名认证状态
    private int promotedType;
    //用户注册时间
    private String regTime;
    //上次登录时间
    private String lastVisit;
    //用户状态：0表示正常，1表示冻结
    private int status;
    //用户的头像
    private String headImage;
    //用户作为买家的信用id
    private int buyerCredit;
    //用户作为卖家的信用id
    private int sellerCredit;
    //是否是设置了安全问题
    private int hasQuestion;
    //用户的账户资金
    private double userMoney;
    //用户的支付密码
    private String payPassword;
    //家乡
    private Location hometown;
    //居住地
    private Location domicile;
    //默认地址
    private ShippingAddress defaultAddress;
    //用户店铺，当用户完成验证之后进行注册才能获取
    private Shop shop;

    public User() {}

    //用于用户注册信息的封装
    public User(String userName, String password, int sex, String mobile, String email) {
        super();
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.mobile = mobile;
        this.email = email;
    }

    //用于用户基本信息的封装
    public User(int id, int sex, String birthday, String qq) {
        super();
        this.id = id;
        this.sex = sex;
        this.birthday = birthday;
        this.qq = qq;
    }

    //用户头像
    public User(int id, String headImage) {
        super();
        this.id = id;
        this.headImage = headImage;
    }

    public User(Integer userId, int promotedType) {
        this.id = userId;
        this.promotedType = promotedType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getPromotedType() {
        return promotedType;
    }

    public void setPromotedType(int promotedType) {
        this.promotedType = promotedType;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public int getBuyerCredit() {
        return buyerCredit;
    }

    public void setBuyerCredit(int buyerCredit) {
        this.buyerCredit = buyerCredit;
    }

    public int getSellerCredit() {
        return sellerCredit;
    }

    public void setSellerCredit(int sellerCredit) {
        this.sellerCredit = sellerCredit;
    }

    public int getHasQuestion() {
        return hasQuestion;
    }

    public void setHasQuestion(int hasQuestion) {
        this.hasQuestion = hasQuestion;
    }

    public double getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(double userMoney) {
        this.userMoney = userMoney;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public Location getHometown() {
        return hometown;
    }

    public void setHometown(Location hometown) {
        this.hometown = hometown;
    }

    public Location getDomicile() {
        return domicile;
    }

    public void setDomicile(Location domicile) {
        this.domicile = domicile;
    }

    public ShippingAddress getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(ShippingAddress defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                ", qq='" + qq + '\'' +
                ", role=" + role +
                ", promotedType=" + promotedType +
                ", regTime='" + regTime + '\'' +
                ", lastVisit='" + lastVisit + '\'' +
                ", status=" + status +
                ", headImage='" + headImage + '\'' +
                ", buyerCredit=" + buyerCredit +
                ", sellerCredit=" + sellerCredit +
                ", hasQuestion=" + hasQuestion +
                ", userMoney=" + userMoney +
                ", payPassword='" + payPassword + '\'' +
                ", hometown=" + hometown +
                ", domicile=" + domicile +
                ", defaultAddress=" + defaultAddress +
                ", shop=" + shop +
                '}';
    }
}
