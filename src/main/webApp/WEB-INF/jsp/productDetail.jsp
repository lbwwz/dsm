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

    <link href="css/style_index.css" rel="stylesheet">

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

<!--广告 -->
<div class="container " style="margin-top:30px;">
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
            <ul>
                <c:forEach items="${productDetail.saleAttrInfo}" var="attrItem">
                    <li data_attrId="${attrItem.attrId}">
                        <span>${attrItem.attrName}</span>
                        <c:forEach items="${attrItem.saleAttrValues}" var="valueItem">
                        <em data="${valueItem.valueId}">${valueItem.valueName}<i></i></em>
                        </c:forEach>
                    </li>
                </c:forEach>

            </ul>
            <%--<p class="colorp00">价值：<em>￥6099.00</em>--%>
                <!-- <span>月供：<em>6281.97</em>元（本金：<em>6099.00</em>元 + 服务费：<em>182.97</em>元）分期 x <em>1个月</em></span> -->
            <%--</p>--%>
           <%-- <label>首付金额：</label>
            <input type="text" />
            <label> 元</label>
            <label style="margin-left:40px;">分期月数：</label>
            <select>
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
                <option>10</option>
                <option>11</option>
                <option>12</option>
                <option>13</option>
                <option>14</option>
                <option>15</option>
                <option>16</option>
            </select>--%>
            <div class="detail_option">
                <a href="" class="buyImmediately" >立即购买</a>
                <a href="" class="addToCart" id="addToCart"><i class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></i>&nbsp;加入购物车</a>
            </div>
        </form>
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