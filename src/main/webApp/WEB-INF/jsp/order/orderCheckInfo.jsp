
<%--
结算信息封装的html信息
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--@elvariable id="orderCheckInfo" type="com.dsm.model.order.OrderCheckInfo"--%>
<c:if test="${orderCheckInfo != null}">
<div class="row">
    <c:if test="${orderCheckInfo.isEnough}">
    <div class="col-xs-12 address_list">
        <h5 class="title_h5">确认收货地址</h5>
        <ul class="list-unstyled">

            <%--地址为空--%>
            <c:if test="${orderCheckInfo.addressList == null || orderCheckInfo.addressList.size() == 0}">
                <li>
                    <div style="line-height: 32px;"><span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<span><a href="#">添加新地址</a></span></div>
                </li>
            </c:if>

            <c:forEach items="${orderCheckInfo.addressList}" var="address">
                <c:if test="${address.checkOrderSelected}">
                    <c:set value="${address.location.province.provinceName} ${address.location.city.cityName} ${address.location.district.districtName} ${address.location.address}" var="detailAddress"></c:set>
                    <c:set value="${address.realName} ${address.mobilePhone}" var="consigneerInfo"></c:set>
                </c:if>
                <li <c:if test="${address.checkOrderSelected}">class="selected_address"</c:if> data_default="${address.isDefault}">
                    <div class="addressBox">
                        <div class="address_tips"><span class="marker glyphicon glyphicon-send"></span><span class="marker_tips" >寄送至</span></div>
                        <label class="addressInfo" for="addressInput-${address.addressId}">
                            <input type="radio" name="address" <c:if test="${address.checkOrderSelected}">checked="checked"</c:if> id="addressInput-${address.addressId}">
                            <span class="user-address">
                                <span>${address.location.province.provinceName} ${address.location.city.cityName} ${address.location.district.districtName}</span>
                                <span>${address.location.address}</span>
                                <span>（</span>
                                <span>${address.realName}</span>
                                <span> 收）</span>
                                <em>${address.mobilePhone}</em>
                            </span>
                            <span class="tips">
                                <a class="set-default" href="javascript:void(0);" data-id="${address.addressId}">设置为默认收货地址</a>
                                <span >设置失败！</span>
                            </span>
                        </label>
                        <a class="modify pull-right" href="javascript:void(0);" data-id="${address.addressId}">修改本地址</a>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    </c:if>

    <%--结算异常项信息提示画幕--%>
    <c:if test="${!orderCheckInfo.isEnough}">
    <div class="col-xs-12">
        <div id="crumbs">

        </div>

        <div id="empty">
            <h2>结算出现异常！</h2>
            <ul>
               <li>看看 <a href="//shoucang.taobao.com/shop_collect_list.htm" target="_blank">我的收藏夹</a></li>
               <li>看看 <a href="//trade.taobao.com/trade/itemlist/list_bought_items.htm" target="_blank">已买到的宝贝</a></li>
            </ul>
        </div>
        </div>
    </c:if>
    <div class="col-xs-12 orderCheck_Info">
        <div class="order-orderDesc" id="orderDesc_0" data-reactid=".0.$confirmOrder_1.$orderDesc_0">
            <h5 class="title_h5" data-reactid=".0.$confirmOrder_1.$orderDesc_0.0">
                确认订单信息
                <c:if test="${!orderCheckInfo.isEnough}">：<font style="color: #f40;font-weight: 100">（以下商品库存不足，请返回重新结算）</font></c:if>
            </h5>
            <div class="buy-th buy-th-column-6" data-reactid=".0.$confirmOrder_1.$orderDesc_0.1">
                <div class="buy-td td-0" >店铺宝贝</div>
                <div class="buy-td td-1" >商品属性</div>
                <div class="buy-td td-2" >单价</div>
                <div class="buy-td td-3" >数量</div>
                <div class="buy-td td-4" >优惠方式</div>
                <div class="buy-td td-5" >小计</div><div class="clearfix"></div>
            </div>
        </div>
        <c:forEach items="${orderCheckInfo.orderPackages}" var="orderPackage">
            <div class="order_package">
                <div class="order_package_head">
                    <div class="pull-left" style="width: 200px">
                        <span class="glyphicon glyphicon-home"></span>
                        <span>店铺：</span>
                        <a>${orderPackage.shopName}</a>
                    </div>
                    <div class="pull-left" style="width: 200px">
                        <span>卖家：</span>
                        <a>wenzi</a>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <style>

                </style>

                <div class="order_package_main">
                    <ul class="list-unstyled item_list">
                        <c:forEach items="${orderPackage.orderSkuItemList}" var="orderSkuItem">
                            <li >
                                <div class="buy-td td-0" >
                                    <div class="div_inner">
                                        <div class="od_pic">
                                            <img src="${orderSkuItem.mainImage}" width="60" height="60"/>
                                        </div>
                                        <div class="od_desc">
                                            <a class="itemName" href="#">
                                                    ${orderSkuItem.productName}
                                            </a>

                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                                <div class="buy-td td-1" >
                                    <div class="div_inner">
                                        <c:forEach items="${fn:split(orderSkuItem.propertiesName, ';')}" var="skuItem">
                                            <c:set var="temp" value="${fn:split(skuItem, '|')}"/>
                                            <p data_attr="${temp[0]}:${temp[1]}">${temp[2]}：${temp[3]}</p>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="buy-td td-2" > <div class="div_inner price_num_style">￥ ${orderSkuItem.skuPrice}</div></div>
                                <div class="buy-td td-3" > <div class="div_inner">${orderSkuItem.itemNum}</div></div>
                                <div class="buy-td td-4" > <div class="div_inner">店铺宝贝</div></div>
                                <div class="buy-td td-5" > <div class="div_inner price_num_style">￥ ${orderSkuItem.skuPrice*orderSkuItem.itemNum}</div></div>
                                <div class="clearfix"></div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

            </div>
        </c:forEach>
        ${orderCheckInfo}

    <style>
        .order_create_info{margin-top:20px;}
        .orderCheck_info{border: 1px solid #f40;}
        .orderCheck_info_box{border: 3px solid #fff0e8;   padding: 10px 10px 10px 20px;}
        .order_create .order_return,.order_create .create_btn{float: left;line-height: 36px;font-size:16px;padding:4px 20px;}
        .order_create .order_return{width: 60%; text-align: right}
        .order_create .create_btn{background:#f40;width: 40%; text-align:center;color:#fff;font-weight: bold; word-spacing:2px}
        .orderCheck_info_box .price font{color:#aaa;font-size:14px;font-weight: 100 }
        .orderCheck_info_box .price .price_value{color:#f40;font-size:26px; }
    </style>
        <div class="row">
            <div class="col-xs-offset-7 col-xs-5 order_create_info">
                <div class="orderCheck_info">
                    <div class="orderCheck_info_box">
                        <div class="price"><strong class="pull-right">实付款： <span class="price_num_style"><font class="">￥</font><span class="price_value">${orderCheckInfo.totalPrice}</span></span></strong><div class="clearfix"></div></div>
                        <div class="detailAddress"><strong>寄送至：</strong>${detailAddress}</div>
                        <div class="consigneerInfo"><font class="pull-right"><strong>收件人：</strong>${consigneerInfo}</font><div class="clearfix"></div></div>

                    </div>
                </div>
                <div class="order_create">
                    <div class="order_return"><a href="#"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;返回购物车</a></div>
                    <div class="create_btn">提交订单</div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
</div>
</div>
</c:if>