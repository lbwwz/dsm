<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common/commonPath.jsp"/>
<!DOCTYPE html>
<html lang="zh">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>D.S Mall主页</title>
    <!--  Bootstrap 核心 CSS 文件 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/non-responsive.css" rel="stylesheet">

    <link href="css/style_base.css" rel="stylesheet">

    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>

</head>

<body>
<jsp:include page="common/topNav.jsp" />
<div class="container">
    <div class="row">
        <div class="col-xs-5"><a href="index.jsp"><img src="images/logo.png"/></a></div>
        <div id="search_box" class="col-xs-7">
            <form class="form-inline" style="margin:0;">
                <div class="input-group col-xs-12">
                    <input id="search" name="search" class="form-control input-md" type="search" placeholder="商品搜索" />
                    <span id="search_btn" class="input-group-btn">
                    <button class="btn btn-info btn-md" type="button">搜索</button>
                    </span>
                </div>
            </form>
        </div>
    </div>
</div>


<div class="container detail_panel" >
    <div class="row">
        <div class="ItemDetailImg" style="float: left">
            <div id="preview" class="spec-preview">
                <span class="jqzoom"><img jqimg="${productDetail.mainImage}" src="${productDetail.mainImage}" width="400" height="400"/></span>
            </div>
            <!--缩图开始-->
            <div class="spec-scroll"> <a class="prev"><img src="images/disabled-prev.png"/></a> <a class="next"><img src="images/disabled-next.png"/></a>
                <div class="items">
                    <ul>

                            <li><img alt="佳能" bimg="${productDetail.mainImage}" src="${productDetail.mainImage}"></li>
                        <c:forEach items="${productDetail.productImages}" var="imgItem">
                            <c:if test="${imgItem.isMain != 1}"><li><img alt="佳能" bimg="${imgItem.imgUrl}" src="${imgItem.imgUrl}" ></li></c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <!--缩图结束-->
        </div>

    <div class="yListr" style="float: left">
        <h4>${productDetail.productName}</h4>
        <p>${productDetail.productBrief}</p>
        <div class="panel_itemPrice">
            <dl >
                <dt class="item_metatit">价格</dt>
                <dd><em class="item_yuan yListr_origin">¥</em> <span class="item_price yListr_origin">469.00</span>
                    <div class="staticPromoTip "></div>
                </dd>
            </dl>
            <dl>
                <dt class="item_metatit">价格</dt>
                <dd><em class="item_yuan yListr_hilite">¥</em> <span class="item_price yListr_hilite" id="sku_price">${productDetail.minPrice}</span>
                    <div class="staticPromoTip "></div>
                </dd>
            </dl>
        </div>

        <form>
            <ul id="saleSelect">
                <c:forEach items="${productDetail.saleAttrInfo}" var="attrItem">
                    <li data_attrId="${attrItem.attrId}" >
                        <span class="chooseName">${attrItem.attrName}</span>

                        <div class="sale_valueBox">
                            <c:forEach items="${attrItem.saleAttrValues}" var="valueItem">
                                <em data="${valueItem.valueId}">${valueItem.valueName}<i></i></em>
                            </c:forEach>
                        </div>
                        <div class="clear"></div>

                    </li>
                </c:forEach>

            </ul>
            <style>
                .sku_item_count{margin-top:-20px}
                .sku_item_count li .countBox,.sku_item_count li .countBox_btn{float:left}
                .sku_item_count font{padding-left:10px;line-height: 40px;color:#999;font-size:14px}
                .countBox_btn{width:32px;margin-left: 2px;}
                #countBox_input{height: 40px;color: #999}
                .countBox_btn a{width:30px;border:1px solid #ccc; display: block;text-align: center;color:#ccc;padding:2px 4px;}
                .countBox_btn a:first-of-type{margin-bottom: 4px;}
            </style>
            <ul class="sku_item_count">
                <li>
                    <span class="chooseName">数量</span>
                    <div class="countBox">
                        <input type="text" id="countBox_input" value="1"/>
                    </div>
                    <div class="countBox_btn">
                            <a href="javascript:;"/><i class="glyphicon glyphicon-chevron-up" ></i></a>
                            <a href="javascript:;"/><i class="glyphicon glyphicon-chevron-down" ></i></a>
                    </div>
                    <font ></font>
                    <div class="clear"></div>

                </li>
            </ul>
            <div class="detail_option">
                <a href="javascript:;" class="buyImmediately" id="buyImmediately">立即购买</a>
                <a href="javascript:;" class="addToCart" id="addToCart"><i class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></i>&nbsp;加入购物车</a>
            </div>
        </form>
    </div>
        <div class="likely_productBox">
            <div><img src="123.jpg" width="140" height="140"/></div>
            <div><img src="123.jpg" width="140" height="140"/></div>
            <div><img src="123.jpg" width="140" height="140"/></div>
        </div>
        <div class="clear"></div>
    </div>
</div>
<div class="container detail_panel" >
    <div class="row">
        <div class="col-xs-2 border_grey" >
            <br>
            <br>
            <br>
            <br>
            <br>

        </div>
        <div class="col-xs-8 ">


            <div class="attr_box border_grey" >
                <h5>产品参数：</h5>
                <ul class="list-inline">

                    <c:forEach items="${productDetail.baseAttrsInfo}" var="baseAttr">
                        <li style="width:235px">${baseAttr.attrName}：${baseAttr.attrValue}</li>
                    </c:forEach>
                    <c:forEach items="${productDetail.customerAttrsInfo}" var="customerAttr">
                        <li style="width:235px">${customerAttr.attrName}：${customerAttr.attrValue}</li>
                    </c:forEach>

                </ul>
            </div>
            <div class="detail_box " >
                ${productDetail.detailText}
            </div>




        </div>
        <div class="col-xs-2 ">
            <br>
            <br>
            <br>
            <br>
            <br>

        </div>
    </div>
</div>

<script src="js/dsm-jqzoom.js" type="text/javascript"></script>

<script>
    var skuList = ${skuList}
</script>
<script src="js/productDetail.js" type="text/javascript"></script>

</body>
</html>