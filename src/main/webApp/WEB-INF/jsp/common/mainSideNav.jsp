<%--
  Created by IntelliJ IDEA.
  User: Lbwwz
  Date: 2017/10/15
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="J-global-toolbar">
    <div class="toolbar-wrap J-wrap">
        <div class="toolbar">
            <div class="toolbar-panels J-panel">
                <div style="visibility: hidden;" class="J-content toolbar-panel tbar-panel-cart toolbar-animate-out">
                    <h3 class="tbar-panel-header J-panel-header">
                        <a href="" class="title"><i></i><em class="title">购物车</em></a>
                        <span class="close-panel J-close"></span>
                    </h3>
                    <div id="J-cart-tips" class="tbar-tipbox">
                        <div class="tip-inner">
                            <span class="tip-text">还没有登录，登录后商品将被保存</span>
                            <a href="#" class="tip-btn J-login">登录</a>
                        </div>
                    </div>
                    <div class="tbar-panel-main">
                        <div class="tbar-panel-content J-panel-content">
                            <div id="J-cart-render">
                                <div class="tbar-cart-list">
                                    <div class="tbar-cart-item" >
                                        <div class="jtc-item-promo">
                                            <em class="promo-tag promo-mz">满赠<i class="arrow"></i></em>
                                            <div class="promo-text">已购满600元，您可领赠品</div>
                                        </div>
                                        <div class="jtc-item-goods">
                                            <span class="p-img"><a href="#" target="_blank"><img src="http://img14.360buyimg.com/n5/g10/M00/00/14/rBEQWFEAilIIAAAAAACGm9ueTbUAAAH7gB8kskAAIaz106.jpg" alt="美的（Midea） BCD-206TM(E) 206升静音/省电/三门冰箱（闪白银）" height="50" width="50" /></a></span>
                                            <div class="p-name">
                                                <a href="#">美的（Midea） BCD-206TM(E)206升静音/省电/三门冰箱（闪白银）</a>
                                            </div>
                                            <div class="p-price"><strong>￥1398.00</strong>×1 </div>
                                            <a href="#" class="p-del J-del">删除</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tbar-panel-footer J-panel-footer">
                        <div class="tbar-checkout">
                            <div class="jtc-number"> <strong class="J-count">0</strong>件商品 </div>
                            <div class="jtc-sum"> 共计：<strong class="J-total">￥113</strong> </div>
                            <a class="jtc-btn J-btn" href="#" target="_blank">去购物车结算</a>
                        </div>
                    </div>
                </div>

                <div style="visibility: hidden;" data-name="follow" class="J-content toolbar-panel tbar-panel-follow">
                    <h3 class="tbar-panel-header J-panel-header">
                        <a href="#" target="_blank" class="title"> <i></i> <em class="title">我的关注</em> </a>
                        <span class="close-panel J-close"></span>
                    </h3>
                    <div class="tbar-panel-main">
                        <div class="tbar-panel-content J-panel-content">
                            <div class="tbar-tipbox2">
                                <div class="tip-inner"> <i class="i-loading"></i> </div>
                            </div>
                        </div>
                    </div>
                    <div class="tbar-panel-footer J-panel-footer"></div>
                </div>

                <div style="visibility: hidden;" class="J-content toolbar-panel tbar-panel-history toolbar-animate-in">
                    <h3 class="tbar-panel-header J-panel-header">
                        <a href="#" target="_blank" class="title"> <i></i> <em class="title">我的足迹</em> </a>
                        <span class="close-panel J-close"></span>
                    </h3>
                    <div class="tbar-panel-main">
                        <div class="tbar-panel-content J-panel-content">
                            <div class="jt-history-wrap">
                                <ul>
                                    <li class="jth-item">
                                        <a href="#" class="img-wrap"> <img src="http://img10.360buyimg.com/n0/s100x100_g9/M03/0C/18/rBEHalCKCk8IAAAAAAD5nbR5xOAAACfgQENi_wAAPm1269.jpg" height="100" width="100" /> </a>
                                        <a class="add-cart-button" href="#" target="_blank">加入购物车</a>
                                        <a href="#" target="_blank" class="price">￥498.00</a>
                                    </li>
                                </ul>
                                <a href="#" class="history-bottom-more" target="_blank">查看更多足迹商品 &gt;&gt;</a>
                            </div>
                        </div>
                    </div>
                    <div class="tbar-panel-footer J-panel-footer"></div>
                </div>
            </div>

            <div class="toolbar-header"></div>

            <div class="toolbar-tabs J-tab">
                <div class="toolbar-tab tbar-tab-cart">
                    <i class="tab-ico"></i>
                    <em class="tab-text ">购物车</em>
                    <span class="tab-sub J-count ">1</span>
                </div>
                <div class=" toolbar-tab tbar-tab-follow">
                    <i class="tab-ico"></i>
                    <em class="tab-text">我的关注</em>
                    <span class="tab-sub J-count hide">0</span>
                </div>
                <div class=" toolbar-tab tbar-tab-history ">
                    <i class="tab-ico"></i>
                    <em class="tab-text">我的足迹</em>
                    <span class="tab-sub J-count hide">0</span>
                </div>
            </div>

            <div class="toolbar-footer">
                <div class="toolbar-tab tbar-tab-top"> <a href="#"> <i class="tab-ico  "></i> <em class="footer-tab-text">顶部</em> </a> </div>
                <div class=" toolbar-tab tbar-tab-feedback"> <a href="#" target="_blank"> <i class="tab-ico"></i> <em class="footer-tab-text ">反馈</em> </a> </div>
            </div>
            <div class="toolbar-mini"></div>
        </div>
        <div id="J-toolbar-load-hook"></div>
    </div>
</div>
