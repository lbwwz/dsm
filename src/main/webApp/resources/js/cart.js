/**
 * Created by Lbwwz on 2017/9/14.
 */
function cartInit() {
    //设置选中
    $(".cart_selected_all").add($(".shop_selected")).add($(".item_content_selected")).find(".cart_checkBox").attr("attr_checked", "1").find("span").addClass("glyphicon glyphicon-ok")
}

/**
 * 刷新购物车的数据信息
 * @param cartInfo
 */
function refreshCartHtmlInfo(cartInfo) {
    var content;
    if (cartInfo == "" || cartInfo == null || cartInfo == undefined) {
        content =
            '<div id="crumbs">' +
            '   <ol class="breadcrumb">' +
            '       <li><strong>您的位置：</strong></li> ' +
            '       <li> <a href="/">首页</a></li>' +
            '       <li class="active">购物车</li>' +
            '       <span class="pull-right">购物车帮您一次性完成批量购买与付款，下单更便捷，付款更简单！<a href="//service.taobao.com/support/help-11746.htm?spm=a1z0d.1.0.0.ogEwpx" target="_blank">如何使用购物车</a></span>' +
            '   </ol>'+
            '</div>'+

            '<div id="empty">' +
            '<h2>您的购物车还是空的，赶紧行动吧！您可以：</h2>' +
            '   <ul>' +
            '       <li>看看 <a href="//shoucang.taobao.com/shop_collect_list.htm" target="_blank">我的收藏夹</a></li>' +
            '       <li>看看 <a href="//trade.taobao.com/trade/itemlist/list_bought_items.htm" target="_blank">已买到的宝贝</a></li>' +
            '   </ul>' +
            '</div>';
    } else {
        content =
            '<div class="row cart_header">' +
            '   <div class="col-xs-12"> ' +
            '   <ul class="cart-inline ' + (cartInfo.selectedAll ? "cart_selected_all" : "") + '"> ' +
            '       <li class="dsm_chk"> ' +
            '           <label id="cart_selectAll_1" class="cart_label cart_checkBox" attr_checked="0" style="margin-top: -3px;"> ' +
            '               <span class="cart_checkbox_span cart_radioInput"></span> ' +
            '          </label> ' +
            '       </li> ' +
            '       <li class="dsm_item">' +
            '           全选<span style="padding-left:65px">商品信息</span> ' +
            '       </li> ' +
            '       <li class="dsm_info">&nbsp;</li> ' +
            '       <li class="dsm_price"> ' +
            '           <span style="padding-left:40px">单价</span> ' +
            '       </li> ' +
            '       <li class="dsm_amount"> ' +
            '           <span style="padding-left:40px">数量</span> ' +
            '       </li> ' +
            '       <li class="dsm_sum"> ' +
            '           <span style="padding-left:20px">金额</span> ' +
            '       </li> ' +
            '       <li class="dsm_op"> ' +
            '           <span style="padding-left:20px">操作</span> ' +
            '       </li> ' +
            '       <div class="clearfix"></div> ' +
            '   </ul> ' +
            '   </div> ' +
            '</div> ' +
            '<div class="row cart_body">';


        for (var i = 0; i < cartInfo.cartPackages.length; i++) {
            var cartPackage = cartInfo.cartPackages[i];
            content += '<div class="cart_list ' + (cartPackage.isSelected ? "shop_selected" : "") + '" data_shopId="' + cartPackage.shopId + '"> ' +
                '<div class="col-xs-12"> ' +
                '<div class="cartItem_header"> ' +
                '<div style="width: 50px;float: left;"> ' +
                '<label class="cart_label cart_checkBox" attr_checked="0" style="margin-top: -3px;"> ' +
                '<span class="cart_checkbox_span cart_radioInput"></span> ' +
                '</label> ' +
                '</div> ' +
                '<span class="ct_shop_name"><font>店铺：</font><a href="#" attr_id="' + cartPackage.shopId + '">' + cartPackage.shopName + '</a></span> ' +
                '</div>' +
                '<div class="cartItem_body" >';

            for (var j = 0; j < cartPackage.cartItems.length; j++) {
                var cartItem = cartPackage.cartItems[j];
                var skuAttrItems = cartItem.propertiesName.split(";");
                var $_skuAttrP = '', temp;
                for (var x = 0; x < skuAttrItems.length; x++) {
                    temp = skuAttrItems[x].split("|");
                    $_skuAttrP += '<p data_attr="' + temp[0] + ':' + temp[1] + '">' + temp[2] + '：' + temp[3] + '</p>'
                }

                content += '<div class="item_content ' + (cartItem.isSelected == 1 ? "item_content_selected" : "") + ' ' + (cartItem.isEnough ? "" : "item_unenough") + '" data_skuId="' + cartItem.skuId + '"> ' +
                    '<ul class="cart-inline"> ' +
                    '<li class="dsm_chk"> ' +
                    '<div class="div_inner"> ' +
                    '<label class="cart_label cart_checkBox" attr_checked="0"> ' +
                    '<span class="cart_checkbox_span cart_radioInput"></span> ' +
                    '</label> ' +
                    '</div> ' +
                    '</li> ' +
                    '<li class="dsm_item"> ' +
                    '<div class="div_inner"> ' +
                    '<div class="pull-left ct_pic"> ' + (cartItem.isEnough ? "" : '<span style="position: absolute;display: block;top:0;left: 0;right: 0;bottom: 0;background: rgba(0, 0, 0, 0.4);"><p>库 存不 足</p></span>') +
                    '<img src="' + cartItem.mainImage + '" width="80" height="80"/> ' +
                    '</div> ' +
                    '<div class="ct_item_content"> ' +
                    '<div class="ct_name"><a>' + cartItem.productName + '</a></div> ' +
                    '<div class="ct_tips"> ' +
                    '<span class="glyphicon glyphicon-align-center"></span> ' +
                    '</div> ' +
                    '</div> ' +
                    '<div class="clearfix"></div> ' +
                    '</div> ' +
                    '</li> ' +
                    '<li class="dsm_info"> ' +
                    '<div class="div_inner"> ' +
                    '<div class="ct_sku_content"> ' + $_skuAttrP +
                    '</div> ' +
                    '</div> ' +
                    '</li> ' +
                    '<li class="dsm_price"> ' +
                    '<div class="div_inner"> ' +
                    '<div class="ct_price_content"> ' +
                    '<div class="price_line"><em class="price_original">￥105.00</em></div> ' +
                    '<div class="price_line"><em class=" price_now" tabindex="0">￥' + priceNumFormat(cartItem.skuPrice) + '</em></div> ' +
                    '</div> ' +
                    '</div> ' +
                    '</li> ' +
                    '<li class="dsm_amount"> ' +
                    '<div class="div_inner"> ' +
                    '<div class="ct_amount_wrapper "> ' +
                    '<div class="item_amount "> ' +
                    '<a href="javascript:;" class="ct_minus" >-</a> ' +
                    '<input type="text" value="' + cartItem.cartItemNum + '" class="text_amount" data_max="' + cartItem.quantity + '"   > ' +
                    '<a href="javascript:;" class="ct_plus">+</a></div> ' +
                    '<div class="amount_msg">剩余<font>' + cartItem.quantity + '</font>件</div> ' +
                    '</div> ' +
                    '</div> ' +
                    '</li> ' +
                    '<li class="dsm_sum"><div class="div_inner"><em tabindex="0" class="item_sum_number">￥' + priceNumFormat(cartItem.skuPrice * cartItem.cartItemNum) + '</em></div></li> ' +
                    '<li class="dsm_op"> ' +
                    '<div class="div_inner"> ' +
                    '<a href="#">收藏商品</a> ' +
                    '<a href="javascript:;" class="deleteCartItem">删除</a> ' +
                    '</div> ' +
                    '</li> ' +
                    '<div class="clearfix"></div> ' +
                    '</ul> ' +
                    '</div> ';

            }
            content += '</div></div></div> ';
        }

        //这里是里层cartItem信息
        content += '</div>' +
            '<div class="cart_footer"> ' +
            '   <div class="float_bar_wrapper"> ' +
            '   <div  class="pull-left ' + (cartInfo.selectedAll ? "cart_selected_all" : "") + '"> ' +
            '       <label id="cart_selectAll_2 " class="cart_label cart_checkBox" attr_checked="0" style="margin-top: -3px;"> ' +
            '           <span class="cart_checkbox_span cart_radioInput "></span> ' +
            '       </label> ' +
            '       &nbsp;全选 ' +
            '   </div> ' +
            '   <div class="operations pull-left"> ' +
            '       <a href="javascript:;" id="cleanCartAll" class="ct_deleteSelected">删除</a> ' +
            '       <a href="#" class="ct_clearInvalid hidden">清除失效宝贝</a> ' +
            '       <a href="#" class="ct_batchFav">移入收藏夹</a> ' +
            '       <a href="#" class="ct_batchShare">分享</a> ' +
            '   </div> ' +
            '   <div class="float_bar_right"> ' +
            '       <div id="ct_showSelectedItems " class="amount_sum pull-left"> ' +
            '           <span class="txt">已选商品</span> ' +
            '               <em id="J_SelectedItemsCount">' + cartInfo.selectTotalNum + '</em> ' +
            '           <span class="txt">件</span> ' +
            '           <div class="arrow-box"><span class="selected-items-arrow"></span><span class="arrow"></span></div> ' +
            '       </div> ' +
            '       <div id="ct_checkCOD pull-left" class="check_cod" style="display: none;"> ' +
            '           <span class="s-checkbox J_CheckCOD"></span>货到付款 ' +
            '       </div> ' +
            '       <div class="ct_pipe pull-left"></div> ' +
            '       <div class="ct_price_sum pull-left"><span class="txt">合计（不含运费）：</span>' +
            '           <strong class="price"><em ><span class="total_symbol">￥&nbsp;</span><font id="totalPrice">' + priceNumFormat(cartInfo.totalPrice) + '</font></em></strong> ' +
            '       </div> ' +
            '       <div class="btn_area pull-right"><a href="javascript:void(0)" id="submit_confirm_info" class="submit_btn ' + (cartInfo.selectTotalNum < 1 ? "btn_no" : "") + '"><span>结&nbsp;算</span><b></b></a></div> ' +
            '   </div> ' +
            '   </div> ' +
            '</div> ';

    }


    $(".cart_panel").html(content);
    cartInit();
}
$(function () {

    $.ajax({
        url: "/cart/getCartInfo",
        type: "post",
        cache: false,
        data:{},
        success:function(data){
            refreshCartHtmlInfo(data);
        }
    });

    cartInit();
    /**
     * 单品勾选
     */
    $(document).on("click", ".item_content .cart_checkBox", function () {


        var $_this = $(this);
        if($_this.parents(".item_unenough").length != 0){
            layer.msg("商品库存不足，请修改商品数量后再试！", {
                icon: 2,
                time: 2000
            })
            return false;
        }
        var skuId = $_this.parents(".item_content ").attr("data_skuId");
        $.ajax({
            url: "/cart/changeSelected",
            type: "post",
            cache: false,
            data: {id: skuId, type: "sku", isSelected: 1 - $_this.attr("attr_checked")},
            success: function (data) {
                if (data.error == 0) {
                    data = JSON.parse(data.data);
                    refreshCartHtmlInfo(data);
                }else {
                    layer.msg(data.message, {
                        icon: 0,
                        time: 2000
                    })
                }
            }
        });
    });

    /**
     * 店铺商品勾选
     */
    $(document).on("click", ".cartItem_header .cart_checkBox", function () {
        var $_this = $(this);
        var shopId = $_this.parents(".cart_list ").attr("data_shopId");
        $.ajax({
            url: "/cart/changeSelected",
            type: "post",
            cache: false,
            data: {id: shopId, type: "shop", isSelected: 1 - $_this.attr("attr_checked")},
            success: function (data) {
                if (data.error == 0) {
                    data = JSON.parse(data.data);

                    refreshCartHtmlInfo(data);
                }else {
                    layer.msg(data.message, {
                        icon: 0,
                        time: 2000
                    })
                }
            }
        });
    });

    /**
     * 所有商品勾选
     */
    $(document).on("click", "[id^='cart_selectAll']", function () {
        var $_this = $(this);
        $.ajax({
            url: "/cart/changeSelected",
            type: "post",
            cache: false,
            data: {type: "all", isSelected: 1 - $_this.attr("attr_checked")},
            success: function (data) {
                if (data.error == 0) {
                    data = JSON.parse(data.data);
                    refreshCartHtmlInfo(data);

                } else {
                    layer.msg(data.message, {
                        icon: 0,
                        time: 2000
                    })
                }
            }
        });
    });


    /**
     * 增减购物车商品
     */

    $(document).on("click",".ct_minus, .ct_plus",function () {

        var $_this = $(this);
        var skuId = $_this.parents(".item_content").attr("data_skuId");
        var changeCount;
        if ($_this.attr("class") == "ct_plus") {
            changeCount = 1;
        } else {
            changeCount = -1;
        }
        addOrMinusToCart(skuId, changeCount, function (data) {
            //这里要进行更新页面数据的操作
            if (data.error == 0 || data.error == 6) {
                if (data.error == 6) { //商品库存不足，设置选中数为最大库存
                    layer.msg(data.message, {
                        icon: 0,
                        time: 2000
                    });
                    return;
                }
                data = JSON.parse(data.data);
                refreshCartHtmlInfo(data);

            } else {
                layer.msg(data.message, {
                    icon: 0,
                    time: 2000
                })
            }

        }, 1);
    });


    /**
     * 修改input中的商品数量
     */
    $(document).on("blur", ".text_amount", function () {
        var $_this = $(this);
        var changedCount = $(this).val();
        var skuId = $_this.parents(".item_content").attr("data_skuId");
        $.ajax({
            url: "/cart/changeNumInCart",
            type: "post",
            cache: false,
            data: {skuId: skuId, changedCount: changedCount},
            success: function (data) {

                if (data.error == 0 || data.error == 6) {
                    if (data.error == 6) { //商品库存不足，设置选中数为最大库存
                        layer.msg(data.message, {
                            icon: 0,
                            time: 2000
                        })
                    }
                    data = JSON.parse(data.data);
                    refreshCartHtmlInfo(data);


                } else {
                    layer.msg(data.message, {
                        icon: 2,
                        time: 1000
                    })
                }
            }
        })
    });

    /**
     * 单品删除
     */
    $(document).on("click", ".deleteCartItem", function () {
        var $_this = $(this);
        var skuId = $_this.parents(".item_content").attr("data_skuId");
        $.ajax({
            url: "/cart/deleteItem",
            type: "post",
            cache: false,
            data: {skuId: skuId},
            success: function (data) {
                if (data.error == 0) {
                    window.location.reload(true)
                } else {
                    layer.msg(data.message, {
                        icon: 2,
                        time: 1000
                    })
                }
            }
        })
    });

    /**
     * 全部删除
     */
    $(document).on("click", "#cleanCartAll", function () {
        $.ajax({
            url: "/cart/cleanAll",
            type: "post",
            cache: false,
            data: {},
            success: function (data) {
                if (data.error == 0) {
                    window.location.reload(true)
                } else {
                    layer.msg(data.message, {
                        icon: 2,
                        time: 1000
                    })
                }
            }
        })
    });



    /**
     * 去结算
     */
    $(document).on("click", "#submit_confirm_info", function () {
        $.ajax({
            url: "/order/checkOrderInfo",
            type: "post",
            cache: false,
            data: {},
            success:function(data){
                if(data.error == 0){
                    $("#items").val(data.data);
                    $("#source_time").val(Date.now());
                    $("#confirm_order").submit();
                }else{
                    layer.msg(data.message,{
                        icon: 0,
                        time: 2000
                    })
                }

            }
        })

    });


});

