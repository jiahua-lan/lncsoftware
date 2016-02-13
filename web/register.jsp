<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="cn.lncsoftware.common.RegexTools" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/6
  Time: 下午10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>岭南软件园 注册</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h1 class="text-center">新用户注册</h1>
                    <%
                        User passport = (User)session.getAttribute("passport");
                        request.setCharacterEncoding("utf-8");
                        String action = request.getParameter("action");

                        if(passport != null){
                    %>
                    <div class="alert alert-warning">您已经注册 <a class="alert-link" href="index.jsp">回到首页</a></div>
                    <%
                    }else{
                        if(action != null){
                            if(action.equals("register")){
                                String username = request.getParameter("username");
                                String password = request.getParameter("password");
                                String conPassword = request.getParameter("conPassword");
                                String contact = request.getParameter("contact");
                                if(RegexTools.legalUsername(username) && RegexTools.legalPassword(password) & RegexTools.legalContactInfo(contact)){
                                    if(User.getDao().getUserByName(username) != null){
                    %>
                    <div class="alert alert-warning">用户已存在
                        <a class="alert-link" href="register.jsp">回到注册页面</a> or <a class="alert-link" href="index.jsp">回到首页</a>
                    </div>
                    <%
                                    }else{
                                        if(password.equals(conPassword)){
                                            User user = new User();
                                            user.setName(username);
                                            user.setPassword(password);
                                            user.setContactInfo(contact);
                                            ArrayList<String> arrayList = new ArrayList<>();
                                            Collections.addAll(arrayList, "login");
                                            user.setRights(arrayList);
                                            User.getDao().insert(user);
                    %>
                    <div class="alert alert-success">注册成功。<a class="alert-link" href="index.jsp">回到首页</a></div>
                    <%
                                        }else{
                    %>
                    <div class="alert alert-warning">密码不一致。
                        <a class="alert-link" href="register.jsp">回到注册页面</a> or <a class="alert-link" href="index.jsp">回到首页</a>
                    </div>
                    <%
                                        }
                                    }
                                }else{
                    %>
                    <div class="alert alert-warning">注册失败，一个或者多个字段不符合格式<br>
                        <a class="alert-link" href="register.jsp">回到注册页面</a> or <a class="alert-link" href="index.jsp">回到首页</a>
                    </div>
                    <%
                            }
                        }
                    }else{
                    %>
                    <form class="form-horizontal" method="post" action="register.jsp">
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
                                <input type="password" name="conPassword" class="form-control" placeholder="再次重复您的密码">
                                <label>请认真重新输入密码并牢记，我们暂时还没有密码找回机制</label>
                            </div>
                            <div class="form-group">
                                <input type="text" name="contact" class="form-control" placeholder="联系信息">
                                <label>电子邮件、QQ（最好，因为只有协会成员才能享受在此网站的支持服务）或者手机号码</label>
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-success col-sm-12" value="注册">
                            </div>
                            <div class="form-group">
                                <a href="index.jsp" class="btn btn-warning col-sm-12">回到主页</a>
                            </div>
                        </div>
                    </form>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
