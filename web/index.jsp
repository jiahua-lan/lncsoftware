<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/1/11
  Time: 下午8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>岭南软件园协会 - 首页</title>
    <jsp:include page="headInclude.html"></jsp:include>
</head>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"></jsp:include>
    <script>
        document.getElementById("lnc-nav-main").setAttribute("class","active");
    </script>
    <div class="page-header">
        <h1>岭南软件园协会 <small>form 广东岭南职业技术学院</small></h1>
        <p>广东岭南职业技术学院软件园协会成立于2014年10月初，是电子信息工程学院下软件专业的协会。</p>
    </div>
    <row>
        <div class="col-lg-8 col-md-7 col-sm-6">
            <div class="lnc-marginBox" id="lnc-post-list">
                <div class="media">
                    <div class="media-body">
                        <h4 class="media-heading"><a href="#">施工中</a></h4>
                        <p>新网站正在建设中，敬请期待！</p>
                    </div>
                </div>
                <div class="media">
                    <div class="media-body">
                        <h4 class="media-heading"><a href="#">Lorem ipsum dolor sit amet</a></h4>
                        <p>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-4 col-md-5 col-sm-2">
            <div class="lnc-marginBox">
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
                    <input type="text" class="form-control" id="lnc-post-searchField">
                    <span class="input-group-btn"><button class="btn btn-default">搜索</button></span>
                </div>
            </div>
            <div class="lnc-marginBox">
                <div class="panel panel-default">
                    <div class="panel-heading">公告板</div>
                    <div class="panel-body">并没有什么内容</div>
                </div>
            </div>
        </div>
    </row>
</div>
<jsp:include page="footInclude.html"></jsp:include>
</body>
</html>
