/**
 * Created by Lbwwz on 2017/9/14.
 */
$(function () {
    /**
     * 单品勾选
     */
    $(".dsm_chk").on("click", ".cart_checkBox", function () {
        var $_this = $(this);
        var skuId = $_this.parents(".item_content ").attr("data_skuId");
        $.ajax({
            url: "/cart/changeSelected",
            type: "post",
            cache: false,
            data: {id: skuId, type: "sku", isSelected: 1 - $_this.attr("attr_checked")},
            success: function (data) {
                if (data.error == 0) {
                    data = JSON.parse(data.data);
                    /**
                     * 底部信息显示条信息变更
                     */
                    $("#J_SelectedItemsCount").text(data.selectTotalNum);
                    $("#totalPrice").text(priceNumFormat(data.totalPrice));
                    if (data.selectTotalNum == 0) {
                        $(".submit_btn").addClass("btn_no")
                    } else {
                        $(".submit_btn").removeClass("btn_no")
                    }

                    /**
                     * 选中状态变更
                     */
                    var $_itemHeadCheckBox = $_this.parents(".cart_list").find(".cartItem_header").find(".cart_checkBox")
                    if ($_this.attr("attr_checked") == "1") {
                        $_this.children(".cart_checkbox_span").removeClass("glyphicon glyphicon-ok");
                        $_this.parents(".item_content").removeClass("item_content_selected");
                        $_this.parents(".cart_list").children(".item_content");
                        $_this.attr("attr_checked", "0");

                        //移除店铺全选
                        $_itemHeadCheckBox.attr("attr_checked", "0");
                        $_itemHeadCheckBox.find(".cart_checkbox_span").removeClass("glyphicon glyphicon-ok");

                        //移除全量全选
                        $("[id^='cart_selectAll']").attr("attr_checked", "0");
                        $("[id^='cart_selectAll']").find(".cart_checkbox_span").removeClass("glyphicon glyphicon-ok");
                    } else {
                        $_this.children(".cart_checkbox_span").addClass("glyphicon glyphicon-ok");
                        $_this.parents(".item_content").addClass("item_content_selected");
                        $_this.attr("attr_checked", "1");

                        //店铺全选是否选中
                        if ($_this.parents(".cartItem_body").find(".item_content").length == $_this.parents(".cartItem_body").find(".item_content_selected").length) {
                            $_itemHeadCheckBox.find(".cart_checkbox_span").addClass("glyphicon glyphicon-ok");
                            $_itemHeadCheckBox.attr("attr_checked", "1");
                        }
                        //全体选中是否选中
                        if ($(".cartItem_header").find("[attr_checked='1']").length == $(".cart_list").length) {
                            $("[id^='cart_selectAll']").attr("attr_checked", "1");
                            $("[id^='cart_selectAll']").find(".cart_checkbox_span").addClass("glyphicon glyphicon-ok");
                        }
                    }
                }
            }
        });
    });

    /**
     * 店铺商品勾选
     */
    $(".cartItem_header").on("click", ".cart_checkBox", function () {
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
                    /**
                     * 底部信息显示条信息变更
                     */
                    $("#J_SelectedItemsCount").text(data.selectTotalNum);
                    $("#totalPrice").text(priceNumFormat(data.totalPrice));
                    if (data.selectTotalNum == 0) {
                        $(".submit_btn").addClass("btn_no")
                    } else {
                        $(".submit_btn").removeClass("btn_no")
                    }


                    var $_itemContents = $_this.parents(".cart_list").find(".item_content");
                    if ($_this.attr("attr_checked") == "1") {
                        $_this.children(".cart_checkbox_span").removeClass("glyphicon glyphicon-ok");
                        $_itemContents.removeClass("item_content_selected");
                        $_itemContents.find(".cart_checkbox_span").removeClass("glyphicon glyphicon-ok");
                        $_this.add($_itemContents.find(".cart_checkbox")).attr("attr_checked", "0");

                        //移除全量全选
                        $("[id^='cart_selectAll']").attr("attr_checked", "0").find(".cart_checkbox_span").removeClass("glyphicon glyphicon-ok");
                    } else {
                        $_this.children(".cart_checkbox_span").addClass("glyphicon glyphicon-ok");
                        $_itemContents.addClass("item_content_selected");
                        $_itemContents.find(".cart_checkbox_span").addClass("glyphicon glyphicon-ok");
                        $_this.add($_itemContents.find(".cart_checkbox")).attr("attr_checked", "1");

                        //全体选中是否选中
                        if ($(".cartItem_header").find("[attr_checked='1']").length == $(".cart_list").length) {
                            $("[id^='cart_selectAll']").attr("attr_checked", "1").find(".cart_checkbox_span").addClass("glyphicon glyphicon-ok");
                        }
                    }
                }
            }
        });
    });

    /**
     * 所有商品勾选
     */
    $("body").on("click", "[id^='cart_selectAll']", function () {
        var $_this = $(this);
        $.ajax({
            url: "/cart/changeSelected",
            type: "post",
            cache: false,
            data: {type: "all", isSelected: 1 - $_this.attr("attr_checked")},
            success: function (data) {
                if (data.error == 0) {
                    data = JSON.parse(data.data);

                    if ($_this.attr("attr_checked") == "1") {
                        $(".cart_checkBox").attr("attr_checked", "0").find(".cart_checkbox_span").removeClass("glyphicon glyphicon-ok");
                        $(".item_content").removeClass("item_content_selected")
                    } else {
                        $(".cart_checkBox").attr("attr_checked", "1").find(".cart_checkbox_span").addClass("glyphicon glyphicon-ok");
                        $(".item_content").addClass("item_content_selected")
                    }
                    /**
                     * 底部信息显示条信息变更
                     */
                    $("#J_SelectedItemsCount").text(data.selectTotalNum);
                    $("#totalPrice").text(priceNumFormat(data.totalPrice));
                    if (data.selectTotalNum == 0) {
                        $(".submit_btn").addClass("btn_no")
                    } else {
                        $(".submit_btn").removeClass("btn_no")
                    }
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
    $(".ct_minus").add($(".ct_plus")).click(function () {

        var $_this = $(this);
        var shopId = $_this.parents(".cart_list ").attr("data_shopId");
        var skuId = $_this.parents(".item_content").attr("data_skuId");
        var changeCount;
        if ($_this.attr("class") == "ct_plus") {
            changeCount = 1;
        } else {
            changeCount = -1;
        }
        var $_numInput = $_this.parent().find("input");
        addOrMinusToCart(skuId, changeCount, function (data) {
            //这里要进行更新页面数据的操作
            if (data.error == 0 || 6) {
                if (data.error == 6) { //商品库存不足，设置选中数为最大库存
                    layer.msg(data.message, {
                        icon: 0,
                        time: 2000
                    });
                    return;
                }
                data = JSON.parse(data.data);
                $("#J_SelectedItemsCount").text(data.selectTotalNum);
                $("#totalPrice").text(priceNumFormat(data.totalPrice));
                var cartNewItemNum;
                var itemNewPrice;
                outer:
                    for (var i = 0; i < data.cartPackages.length; i++) {
                        if (data.cartPackages[i].shopId == shopId) {
                            for (var j = 0; j < data.cartPackages[i].cartItems.length; j++) {
                                var cartItem = data.cartPackages[i].cartItems[j];
                                if (cartItem.skuId == skuId) {
                                    cartNewItemNum = cartItem.cartItemNum;
                                    itemNewPrice = cartItem.skuPrice;
                                    break outer;
                                }
                            }
                        }
                    }
                $_this.parents(".item_content").find(".item_sum_number").text("￥" + priceNumFormat(cartNewItemNum * itemNewPrice))
                $_numInput.val(cartNewItemNum);

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
    $(".cart_list").on("blur", ".text_amount", function () {
        var $_this = $(this);
        var changedCount = $(this).val();
        var shopId = $_this.parents(".cart_list ").attr("data_shopId");
        var skuId = $_this.parents(".item_content").attr("data_skuId");
        $.ajax({
            url: "/cart/changeNumInCart",
            type: "post",
            cache: false,
            data: {skuId: skuId, changedCount: changedCount},
            success: function (data) {

                if (data.error == 0 || 6) {
                    if (data.error == 6) { //商品库存不足，设置选中数为最大库存
                        layer.msg(data.message, {
                            icon: 0,
                            time: 2000
                        })
                    }
                    data = JSON.parse(data.data);

                    $("#J_SelectedItemsCount").text(data.selectTotalNum);
                    $("#totalPrice").text(priceNumFormat(data.totalPrice));
                    var cartNewItemNum;
                    var itemNewPrice;
                    outer:
                        for (var i = 0; i < data.cartPackages.length; i++) {
                            if (data.cartPackages[i].shopId == shopId) {
                                for (var j = 0; j < data.cartPackages[i].cartItems.length; j++) {
                                    var cartItem = data.cartPackages[i].cartItems[j];
                                    if (cartItem.skuId == skuId) {
                                        cartNewItemNum = cartItem.cartItemNum;
                                        itemNewPrice = cartItem.skuPrice;
                                        break outer;
                                    }
                                }
                            }
                        }
                    $_this.parents(".item_content").find(".item_sum_number").text("￥" + priceNumFormat(cartNewItemNum * itemNewPrice));
                    $_this.val(cartNewItemNum);


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
    $("body").on("click", ".deleteCartItem", function () {
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
    $("body").on("click", "#cleanCartAll", function () {
        var $_this = $(this);
        var skuId = $_this.parents(".item_content").attr("data_skuId");
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
    $("body").on("click", "#cartToOrderCheck", function () {
        $.ajax({
            url: "/order/checkOrder",
            type: "post",
            cache: false,
            data: {},
            success: function (data) {
                if (data.error == 0) {
                    window.location.href=webRoot+"/order/confirm_order.htm?spm="+data.data;
                } else {
                    layer.msg(data.message, {
                        icon: 2,
                        time: 1000
                    })
                }
            }
        })
    });


});

