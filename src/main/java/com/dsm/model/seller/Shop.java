package com.dsm.model.seller;

/**
 * 用于封装店铺信息的shop类
 *
 * @author lbwwz
 */
public class Shop {

    private Long shopId;

    // 店铺所属的类目编号 //
    private Integer catId;

    private Long userId;

    // 卖家昵称
    private String hostName;

    // 店铺名(不重复)
    private String shopName;

    //店铺头像
    private String shopPicture;

    // 店铺描述
    private String shopDesc;

    // bulletin 店铺公告
    private String bulletin;

    // 开店时间。格式：yyyy-MM-dd HH:mm:ss
    private String createTime;

    // 店铺动态评分id (对应shopScore表中的shopScopeId)
    private ShopScore shopScore;

    //店铺的联系电话
    private String shopTelephone;

    //店铺的位置，对应 Location中的 locationId
    private int shopLocation;

    //店铺状态
    private int shopStatus;

    public Shop() {
    }

    public Shop(long userId, String hostName, String shopName) {
        super();
        this.userId = userId;
        this.hostName = hostName;
        this.shopName = shopName;
    }

    public Long getShopId() {
        return shopId;
    }


    public void setShopId(long shopId) {
        this.shopId = shopId;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public Integer getCatId() {
        return catId;
    }


    public void setCatId(Integer catId) {
        this.catId = catId;
    }


    public String getHostName() {
        return hostName;
    }


    public void setHostName(String hostName) {
        this.hostName = hostName;
    }


    public String getShopName() {
        return shopName;
    }


    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    public String getShopPicture() {
        return shopPicture;
    }


    public void setShopPicture(String shopPicture) {
        this.shopPicture = shopPicture;
    }


    public String getShopDesc() {
        return shopDesc;
    }


    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }


    public String getBulletin() {
        return bulletin;
    }


    public void setBulletin(String bulletin) {
        this.bulletin = bulletin;
    }


    public String getCreateTime() {
        return createTime;
    }


    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public ShopScore getShopScore() {
        return shopScore;
    }


    public void setShopScore(ShopScore shopScore) {
        this.shopScore = shopScore;
    }


    public String getShopTelephone() {
        return shopTelephone;
    }


    public void setShopTelephone(String shopTelephone) {
        this.shopTelephone = shopTelephone;
    }


    public int getShopLocation() {
        return shopLocation;
    }


    public void setShopLocation(int shopLocation) {
        this.shopLocation = shopLocation;
    }

    public int getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(int shopStatus) {
        this.shopStatus = shopStatus;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId='" + shopId + '\'' +
                ", catId='" + catId + '\'' +
                ", userId=" + userId +
                ", hostName='" + hostName + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopPicture='" + shopPicture + '\'' +
                ", shopDesc='" + shopDesc + '\'' +
                ", bulletin='" + bulletin + '\'' +
                ", createTime='" + createTime + '\'' +
                ", shopScore=" + shopScore +
                ", shopTelephone='" + shopTelephone + '\'' +
                ", shopLocation=" + shopLocation +
                ", shopStatus=" + shopStatus +
                '}';
    }
}
