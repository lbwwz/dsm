<%--
  Created by IntelliJ IDEA.
  User: Lbwwz
  Date: 2016/9/5
  Time: 15:21
  To change this template use File | Settings | File Templates.
  账户设置页面侧栏
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 侧栏导航 -->
<div class="col-xs-2 side-list">
    <div class="avatar">
        <div class="avatar-box">
            <a class="user-avatar" href="//i.taobao.com/u/ODMzODczMDAx/front.htm?tracelog=snshomeside" target="_blank">
                <img src="${webRoot}/${user.headImage}"></a>
        </div>
    </div>
    <h4 class="center-side-head" >账号管理</h4>
    <ul>
        <li><a href="${webRoot }/userHome">安全设置</a></li>
        <li><a href="${webRoot }/userHome/personalInfo">个人设置</a></li>
        <li><a href="javascript:;">隐私设置</a></li>
        <li><a href="javascript:;">个人成长信息</a></li>
        <li><a href="${webRoot }/userHome/shippingAddress">收货地址</a></li>
    </ul>

</div>
<!-- 侧栏导航 end -->

<script type="text/javascript">
    //设置选中样式
    function setCheckedBtn(index){
        $(".side-list>ul").find('a:eq('+index+')').attr("id","be-Checked");
    }
</script>
