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


