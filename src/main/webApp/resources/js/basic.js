/**
 * Created by Lbwwz on 2017/3/6.
 */

/**
 * 图片懒加载插件
 * @type {{IsShow: lazyLoad.IsShow, LoadImage: lazyLoad.LoadImage, Run: lazyLoad.Run, LoadSource: lazyLoad.LoadSource}}
 */
var lazyLoad = {
    IsShow: function($el) {
        return ($el.offset().top + $el.offset().height) <= 0|| $(window).height() + ($(window).scrollTop() || 0) + 100> $el.offset().top  &&
            (($(window).scrollTop() || 0) <= ($el.offset().top + $el.offset().height));
    },
    LoadImage: function() {
        var _self = this;
        var $imgs = $("img[lazyload]");
        $imgs.each(function() {
            var $this = $(this);
            if (_self.IsShow($this)) {
                var src = $this.attr("lazyload");
                $this.removeAttr("lazyload");
                $this.attr("src", $.BENLAI.ImageUtil.optimizeURL(src));
                if($this.is(':visible')){
                    $this.fadeIn(600);
                }
            }
        });
    },

    /**
     * 页面延迟引入css和Js文件
     * @param typeName
     * @param url
     */
    LoadSource: function(typeName,url){
        var g, s;
        if(typeName == "css"||"CSS"){
            g=document.createElement('script');
            g.type='text/javascript';
            g.async=true;g.defer=true;
            g.src=url;
            s=document.getElementsByTagName('link')[0];
        }else if(typeName == "js"||"JS"){
            g=document.createElement('link');
            g.rel='stylesheet';
            g.type='text/css';
            g.href=url;
            s=document.getElementsByTagName('script')[0];
        }
        s.parentNode.insertBefore(g,s);
    },
    Run: function() {
        var _self = this;
        _self.LoadImage();
        if (_self.bind) return;
        $(window).bind("touchmove scroll",
            _debounce(function() {_self.LoadImage();
            },100,800));
        _self.bind = true
    }


};
/**
 * js 函数节流
 * @param fn
 * @param delay
 * @param atleast
 * @returns {Function}
 * @private
 */
function _throttle(fn, delay, atleast) {
    var timeout = null,
        startTime = new Date();
    return function() {
        var curTime = new Date();
        clearTimeout(timeout);
        if(curTime - startTime >= atleast) {
            fn();
            startTime = curTime;
        }else {
            timeout = setTimeout(fn, delay);
        }
    }
}

/**
 * js 函数去抖
 * @param fn
 * @param delay
 * @param atleast
 * @returns {Function}
 * @private
 */
function _debounce(fn, delay,atleast){
    var timeout = null,
        startTime = new Date();
    return function() {
        var curTime = new Date();
        clearTimeout(timeout);
        if (curTime - startTime < atleast) {
            timeout = setTimeout(fn, delay);
        } else {
            fn();
        }
        startTime= curTime;
    }
}

/**
 * 关于购物车添加和移除的相关操作
 */

/**
 * 修改购物车中的商品数量
 * @param sku
 * @param changeCount
 * @param returnWithData
 * @param success
 */
function addOrMinusToCart(sku,changeCount,success,returnWithData){
    returnWithData = returnWithData == undefined?0:returnWithData;
    $.ajax({
        url: "/cart/addOrMinusToCart",
        type:"post",
        cache: false,
        data:{skuId:sku,count:changeCount,returnWithData:returnWithData},
        success:success
    })
}

/**
 * 格式化价格显示
 * @param price 价格
 * @returns {string|*}
 */
function priceNumFormat(price) {
    price = price + "";
    var priceArr = price.split(".");
    if (priceArr.length < 2) {
        price = price + ".00";
    } else {
        for (var i = 2; i > priceArr[1].length; i--) {
            price = price + "0";
        }
    }
    return price;
}


/**
 * 侧边栏设置
 */
function showSideNav(){
    $(document).ready(function(){
        setHtml();
        $(document).on("mouseover mouseout",".tbar-cart-item",function(event) {
            if (event.type == "mouseover") {
                $(this).find('.p-del').show();
            } else if (event.type == "mouseout") {
                $(this).find('.p-del').hide();
            }
        });

        $(document).on("mouseover mouseout", ".jth-item", function () {
            if (event.type == "mouseover") {
                $(this).find('.add-cart-button').show();
            } else {
                $(this).find('.add-cart-button').hide();
            }
        });
        $(document).on("mouseover mouseout", ".toolbar-tab", function () {
            if (event.type == "mouseover") {
                $(this).find('.tab-text').addClass("tbar-tab-hover");
                $(this).find('.footer-tab-text').addClass("tbar-tab-footer-hover");
                $(this).addClass("tbar-tab-selected");
            } else {
                $(this).find('.tab-text').removeClass("tbar-tab-hover");
                $(this).find('.footer-tab-text').removeClass("tbar-tab-footer-hover");
                $(this).removeClass("tbar-tab-selected");
            }
        });
        $(document).on("click",".tbar-tab-cart",function(){
            getSideNavRequestInfo(this,function(){
                $.ajax({
                    url: "/cart/getCartInfo",
                    type: "post",
                    cache: false,
                    data:{},
                    success:function(data){
                        if(data.error == 100){
                            $("#J-cart-tips").show();
                        }
                        console.log(data);
                        var cartInfo = data.data;

                        var listContent = '';
                        for (var i = 0; i < cartInfo.cartPackages.length; i++) {
                            var cartPackage = cartInfo.cartPackages[i];
                            listContent += '<div class="tbar-cart-item" >' +
                                '               <div class="jtc-item-promo">' +
                                '                   <div class="promo-text"><em class="promo-tag promo-mz"><i class="glyphicon glyphicon-home"></i></em><a href="#">' + cartPackage.shopName + '</a></div>' +
                                '               </div>';

                            for (var j = 0; j < cartPackage.cartItems.length; j++) {
                                var cartItem = cartPackage.cartItems[j];
                                var skuAttrItems = cartItem.propertiesName.split(";");
                                var skuAttrValues = '', temp;
                                for (var x = 0; x < skuAttrItems.length; x++) {
                                    temp = skuAttrItems[x].split("|");
                                    skuAttrValues +=  temp[3] + ' '
                                }
                                listContent+='<div class="jtc-item-goods">' +
                                    '               <span class="p-img"><a href="#" target="_blank"><img src="' + cartItem.mainImage + '" alt="' + cartItem.productName + '" height="50" width="50" /></a></span>' +
                                    '               <div class="p-name">' +
                                    '                   <a href="#">' + cartItem.productName + '</a>' +
                                    '                   <font>'+skuAttrValues+'</font>' +
                                    '               </div>' +
                                    '               <div class="p-price"><strong>￥'+priceNumFormat(cartItem.skuPrice)+'</strong>×'+cartItem.cartItemNum+' </div>' +
                                    '               <a href="#none" class="p-del J-del">删除</a>' +
                                    '         </div>'
                            }
                            listContent+='</div>'
                            $(".tbar-cart-list").html(listContent);
                            $(".J-count").text(cartInfo.totalNum);
                            $(".J-total").text("￥"+priceNumFormat(cartInfo.totalPrice));
                        }
                    }
                });
            });
        });

        //我的关注
        $(document).on("click", ".tbar-tab-follow", function () {
            getSideNavRequestInfo(this);
        });
        //浏览历史
        $(document).on("click", ".tbar-tab-history", function () {
            getSideNavRequestInfo(this);
        });
    });
}

function getSideNavRequestInfo(obj,fn_loadInfo){
    var $_panel;
    if($(obj).hasClass("tbar-tab-cart")){
        $_panel = $('.tbar-panel-cart');
    }else if($(obj).hasClass("tbar-tab-follow")){
        $_panel = $('.tbar-panel-follow');
    }else{
        $_panel = $('.tbar-panel-history');
    }
    if($('.toolbar-wrap').hasClass('toolbar-open')){
        if($(obj).find('.tab-text').length > 0){//切换
            $(".toolbar-tabs .toolbar-tab").each(function(index,element){
                if(! $(element).find('.tab-text').length > 0){
                    var info = "<em class='tab-text '>"+$(element).attr("title")+"</em>";
                    $(element).append(info);
                    $(element).removeClass('tbar-tab-click-selected');

                }
            });

            $(obj).addClass('tbar-tab-click-selected');
            $(obj).find('.tab-text').remove();

            if(fn_loadInfo){
                fn_loadInfo();
            }

            $(".toolbar-panel").css({'visibility':"hidden","z-index":"-1"});
            $_panel.css({'visibility':"visible","z-index":"1"});

        }else{//收起展开
            var info = "<em class='tab-text '>"+$(obj).attr("title")+"</em>";
            $('.toolbar-wrap').removeClass('toolbar-open');
            $(obj).append(info);
            $(obj).removeClass('tbar-tab-click-selected');
            $('.tbar-panel-cart').css({'visibility':"hidden","z-index":"-1"});
        }
    }else{//展开
        $(obj).addClass('tbar-tab-click-selected');
        $(obj).find('.tab-text').remove();
        $(".toolbar-panel").css({'visibility':"hidden","z-index":"-1"});
        if(fn_loadInfo){
            fn_loadInfo();
        }
        $_panel.css({'visibility':"visible","z-index":"1"});
        $('.toolbar-wrap').addClass('toolbar-open');
    }
}


function setHtml() {

    $("head").append("<link>");
    var css = $("head").children(":last");
    css.attr({
        rel:  "stylesheet",
        type: "text/css",
        href: webRoot+"/css/module/side-nav.css"
    });

    $.ajax({
        url: "/cart/getCartSize",
        type: "post",
        cache: false,
        success:function(data){
            var content =
                '<div class="J-global-toolbar">' +
                '   <div class="toolbar-wrap J-wrap">' +
                '       <div class="toolbar">' +
                '           <div class="toolbar-panels J-panel">' +
                '               <div style="visibility: hidden;" class="J-content toolbar-panel tbar-panel-cart toolbar-animate-out">' +
                '                   <h3 class="tbar-panel-header J-panel-header">' +
                '                       <a href="" class="title"><i></i><em class="title">购物车</em></a>' +
                '                       <span class="close-panel J-close"></span>' +
                '                   </h3>' +
                '                   <div id="J-cart-tips" class="tbar-tipbox" style="display:none">' +
                '                       <div class="tip-inner">' +
                '                           <span class="tip-text">还没有登录，登录后商品将被保存</span>' +
                '                           <a href="#none" class="tip-btn J-login">登录</a>' +
                '                       </div>' +
                '                   </div>' +
                '                   <div class="tbar-panel-main">' +
                '                       <div class="tbar-panel-content J-panel-content">' +
                '                           <div id="J-cart-render">' +
                '                               <div class="tbar-cart-list">' +
                '                               </div>' +
                '                           </div>' +
                '                       </div>' +
                '                   </div>' +
                '                   <div class="tbar-panel-footer J-panel-footer">' +
                '                       <div class="tbar-checkout">' +
                '                           <div class="jtc-number"> <strong class="J-count">0</strong>件商品 </div>' +
                '                           <div class="jtc-sum"> 共计：<strong class="J-total"></strong> </div>' +
                '                           <a class="jtc-btn J-btn" href="#none" target="_blank">去购物车结算</a>' +
                '                       </div>' +
                '                   </div>' +
                '               </div>' +
                '               <div style="visibility: hidden;" data-name="follow" class="J-content toolbar-panel tbar-panel-follow">' +
                '                   <h3 class="tbar-panel-header J-panel-header">' +
                '                       <a href="#" target="_blank" class="title"> <i></i> <em class="title">我的关注</em> </a>' +
                '                       <span class="close-panel J-close"></span>' +
                '                   </h3>' +
                '                   <div class="tbar-panel-main">' +
                '                       <div class="tbar-panel-content J-panel-content">' +
                '                           <div class="tbar-tipbox2">' +
                '                               <div class="tip-inner"> <i class="i-loading"></i> </div>' +
                '                           </div>' +
                '                       </div>' +
                '                   </div>' +
                '                   <div class="tbar-panel-footer J-panel-footer"></div>' +
                '               </div>' +
                '               <div style="visibility: hidden;" class="J-content toolbar-panel tbar-panel-history toolbar-animate-in">' +
                '                   <h3 class="tbar-panel-header J-panel-header">' +
                '                   <a href="#" target="_blank" class="title"> <i></i> <em class="title">我的足迹</em> </a>' +
                '                   <span class="close-panel J-close"></span>' +
                '                   </h3>' +
                '                   <div class="tbar-panel-main">' +
                '                       <div class="tbar-panel-content J-panel-content">' +
                '                           <div class="jt-history-wrap">' +
                '                               <ul>' +
                '                                   <li class="jth-item">' +
                '                                       <a href="#" class="img-wrap"> <img src="http://img10.360buyimg.com/n0/s100x100_g9/M03/0C/18/rBEHalCKCk8IAAAAAAD5nbR5xOAAACfgQENi_wAAPm1269.jpg" height="100" width="100" /> </a>' +
                '                                       <a class="add-cart-button" href="#" target="_blank">加入购物车</a>' +
                '                                       <a href="#" target="_blank" class="price">￥498.00</a>' +
                '                                   </li>' +
                '                               </ul>' +
                '                               <a href="#" class="history-bottom-more" target="_blank">查看更多足迹商品 &gt;&gt;</a>' +
                '                           </div>' +
                '                       </div>' +
                '                   </div>' +
                '                   <div class="tbar-panel-footer J-panel-footer"></div>' +
                '               </div>' +
                '           </div>' +
                '           <div class="toolbar-tabs J-tab">' +
                '               <div class="toolbar-tab tbar-tab-cart" title="购物车" attr_loaded="0">' +
                '                   <i class="tab-ico"></i>' +
                '                   <em class="tab-text ">购物车</em>' +
                '                   <span class="tab-sub J-count ">'+data.data+'</span>' +
                '               </div>' +
                '               <div class=" toolbar-tab tbar-tab-follow" title="我的关注" >' +
                '                   <i class="tab-ico"></i>' +
                '                   <em class="tab-text">我的关注</em>' +
                '                   <span class="tab-sub J-count hide">0</span>' +
                '               </div>' +
                '               <div class=" toolbar-tab tbar-tab-history" title="我的足迹">' +
                '                   <i class="tab-ico"></i>' +
                '                   <em class="tab-text">我的足迹</em>' +
                '                   <span class="tab-sub J-count hide">0</span>' +
                '               </div>' +
                '           </div>' +
                '           <div class="toolbar-footer">' +
                '               <div class="toolbar-tab tbar-tab-top"> <a href="#"> <i class="tab-ico  "></i> <em class="footer-tab-text">顶部</em> </a> </div>' +
                '               <div class=" toolbar-tab tbar-tab-feedback"> <a href="#" target="_blank"> <i class="tab-ico"></i> <em class="footer-tab-text ">反馈</em> </a> </div>' +
                '           </div>' +
                '           <div class="toolbar-mini"></div>' +
                '       </div>' +
                '       <div id="J-toolbar-load-hook"></div>' +
                '   </div>' +
                '</div>';

            $("body").prepend($(content));
        }
    });


}

