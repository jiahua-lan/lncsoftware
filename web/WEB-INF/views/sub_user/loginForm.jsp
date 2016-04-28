<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/25
  Time: 下午8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆｜岭南软件园协会</title>
    <jsp:include page="../temps/includeCSS.jsp"/>
    <jsp:include page="../temps/includeScript.jsp"/>
</head>
<body>
<div class="container">
    <div class="page-header">
        <jsp:include page="../temps/homepageHead.jsp"/>
    </div>
    <div style="margin-top: 10pt">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="page-header text-center">
                            <h1>登陆</h1>
                        </div>
                        <form class="form-horizontal" method="post" action="login.action" id="loginForm">
                            <s:if test="#request.loginResult != null">
                                <div class="alert alert-warning">
                                    登陆失败，服务器返回信息： ${loginResult}.
                                </div>
                            </s:if>
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label class="control-label">用户名</label>
                                </div>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="username" id="username">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label class="control-label">密码</label>
                                </div>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" name="password" id="password">
                                </div>
                            </div>
                            <div class="form-group text-center">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <input class="btn btn-success btn-block" type="submit" value="登陆">
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
