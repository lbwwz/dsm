<%--
  Created by IntelliJ IDEA.
  User: Lbwwz
  Date: 2016/9/5
  Time: 15:21
  To change this template use File | Settings | File Templates.
  管理员页面导航和目录模版
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/admin">D.S Mall 管理</a>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;">
                <i class="fa fa-user fa-fw"></i> 管理员 <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="javascript:;"><i class="fa fa-user fa-fw"></i> 个人信息</a>
                </li>
                <li><a href="javascript:;"><i class="fa fa-gear fa-fw"></i> 账号设置</a>
                </li>
                <li class="divider"></li>
                <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> 退出</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">

                <li>
                    <a href="/admin"><i class="fa fa-dashboard fa-fw"></i> 管理主页</a>
                </li>
                <li>
                    <a href="javascript:;"><i class="fa fa-sitemap fa-fw"></i> 商品类目管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/admin/manageCategory">更新商品类目</a>
                        </li>
                        <li>
                            <a href="javascript:;">设置品牌类目</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="javascript:;"><i class="fa fa-cogs fa-fw"></i> 商品属性目管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="/admin/manageProductAttr">设置商品属性</a>
                        </li>
                        <li>
                            <a href="adminManage?method=showCatForAddAttr">查看更新属性</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="javascript:;"><i class="fa fa-institution fa-fw"></i> 店铺管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="javascript:;">店铺审核</a>
                        </li>
                        <li>
                            <a href="javascript:;">设置店铺权限</a>
                        </li>
                        <li>
                            <a href="javascript:;">物流服务商设置</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="javascript:;"><i class="fa fa-gift fa-fw"></i> 商品管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="javascript:;">商品审核</a>
                        </li>
                        <li>
                            <a href="javascript:;">设置商品权限性</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="javascript:;"><i class="fa fa-th fa-fw"></i> 页面广告<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="javascript:;">灯箱广告区</a>
                        </li>
                        <li>
                            <a href="javascript:;">主类目区</a>
                        </li>
                        <li>
                            <a href="javascript:;">······</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="javascript:;"><i class="fa fa-wrench fa-fw"></i> 个人设置</a>
                </li>

            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>