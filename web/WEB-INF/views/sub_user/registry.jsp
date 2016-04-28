<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/28
  Time: 上午10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up | LNCSA</title>
    <jsp:include page="../temps/includeCSS.jsp"/>
</head>
<body>
<div class="container">
    <div class="page-header">
        <jsp:include page="../temps/homepageHead.jsp"/>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-primary">
                    <div class="panel-body">
                        <div class="page-header">
                            <h1 class="text-center">Sign up</h1>
                        </div>
                        <s:if test="#request.error=='field'">
                            <div class="alert alert-warning">Please ensure each field are filled right.</div>
                        </s:if>
                        <s:if test="#request.error=='duplicate'">
                            <div class="alert alert-warning">Username was existed, please use another name.</div>
                        </s:if>
                        <form class="form-horizontal" method="post" action="registry.action">
                            <div class="container-fluid">
                                <input type="hidden" name="action" value="register">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="username" placeholder="用户名">
                                    <label>可以使用：A-Z a-z 和 0-9 之间的字符, 以及 &quot;-&quot; 和 &quot;_&quot;, 6 - 32 个字符</label>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" class="form-control" placeholder="不建议使用自己的常用密码">
                                    <label>可以使用：A-Z a-z and 0-9, = &lt; &gt; 以及 _ @ &amp; % # &quot; ! &yen;</label>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="confirmedPassword" class="form-control" placeholder="再次重复您的密码">
                                    <label>请认真重新输入密码并牢记，我们暂时还没有密码找回机制</label>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="contactInfo" class="form-control" placeholder="联系信息">
                                    <label>电子邮件、QQ（最好，因为只有协会成员才能享受在此网站的支持服务）或者手机号码</label>
                                </div>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-success col-sm-12" value="注册">
                                </div>
                                <div class="form-group">
                                    <a href="index" class="btn btn-warning col-sm-12">回到主页</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
