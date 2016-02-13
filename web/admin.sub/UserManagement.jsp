<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.factory.UserDAO" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="cn.lncsoftware.common.RegexTools" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/6
  Time: 下午11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>岭南软件园 用户管理</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/main.css">
</head>
<%
    User passport = (User)session.getAttribute("passport");
    request.setCharacterEncoding("utf-8");
    String statusFlag = "";
    String action = request.getParameter("action");

    if(passport == null){
        statusFlag = "no login";
    }else{
        boolean adminFlag = false;
        for(String s : passport.getRights()){
            if(s.equals("admin")){
                adminFlag = true;
                break;
            }
        }
        if(!adminFlag){
            statusFlag = "permission denied";
        }else{
            statusFlag = "permission recognition";
        }
    }

    if(passport != null){
        if(statusFlag.equals("permission recognition")){
            if(action != null){
                switch (action){
                    case "update":{
                        String userID = request.getParameter("userID");
                        if(userID != null){
                            User user = User.getDao().get(new ObjectId(userID));
                            if(user != null){
                                String username = request.getParameter("username");
                                String contact = request.getParameter("contact");
                                String[] rights = request.getParameterValues("rights");
                                if(RegexTools.legalUsername(username) && RegexTools.legalContactInfo(contact)){
                                    user.setName(username);
                                    user.setContactInfo(contact);
                                    if(rights != null){
                                        ArrayList<String> arrayList = new ArrayList<>();
                                        Collections.addAll(arrayList,rights);
                                        user.setRights(arrayList);
                                        User.getDao().update(user);
                                        statusFlag = "update success";
                                    }
                                }else{
                                    statusFlag = "illegal field";
                                }
                            }else{
                                statusFlag = "user not exist";
                            }
                        }
                    };break;
                }
            }
        }
    }
%>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <script>
        document.getElementById("nav-user").setAttribute("class","active");
    </script>
    <div class="page-header">
        <h1>用户管理</h1>
    </div>
    <div class="row">
        <%
            if(passport == null || "permission denied".equals(statusFlag)){
        %>
        <div class="col-sm-6 col-sm-offset-3">
            <%
                switch (statusFlag){
                    case "no login":
            %>
            <div class="alert alert-warning">请先 <a class="alert-link" href="../index.jsp">登录</a></div>
            <%
                        break;

                    case "permission denied":
            %>
            <div class="alert alert-danger">抱歉，你没有权限</div>
            <%
                        break;
                }
            %>
        </div>
        <%
            }else{
        %>
        <div class="col-sm-12">
            <div class="container-fluid">
                <form class="form-horizontal" action="UserManagement.jsp" method="post">
                    <input type="hidden" name="action" value="search">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" name="useRegex" value="useRegex"> 正则表达式</span>
                        <input class="form-control" type="text" name="keyword" placeholder="输入用户名称">
                        <span class="input-group-btn"><input class="btn btn-primary" type="submit" value="搜索"></span>
                    </div>
                </form>
            </div>
            <div class="col-sm-10 col-sm-offset-1">
                <%
                    switch (statusFlag){
                        case "update success":
                %>
                <div class="alert alert-success">更新成功</div>
                <%
                        break;

                    case "illegal field":
                %>
                <div class="alert alert-warning">字段格式不对</div>
                <%
                        break;

                    case "user not exist":
                %>
                <div class="alert alert-warning">用户已经不存在了</div>
                <%
                            break;
                    }
                if("search".equals(action)){
                    String keyword = request.getParameter("keyword");
                    boolean useRegex = "useRegex".equals(request.getParameter("useRegex"));
                    if(keyword != null && (keyword.matches("[\\d\\w\\-_]{1,32}") || useRegex)){
                        List<User> users = User.getDao().search("name",(useRegex ? keyword : (".*"+keyword+".*")));
                        if(users != null && users.size() > 0){
                %>
                <div class="alert alert-success"><%=users.size()%> 结果。 关键字： <%=keyword%></div>
                <ul class="list-group">
                    <%
                            for(User user : users){
                    %>
                    <li class="list-group-item">
                        <div class="container-fluid">
                            <form class="form-horizontal" action="UserManagement.jsp" method="post">
                                <input type="hidden" name="userID" value="<%=user.getObjectId().toHexString()%>">
                                <input type="hidden" name="action" value="update">
                                <div class="form-group">
                                    <div class="input-group">
                                        <label class="input-group-addon">用户名</label>
                                        <input class="form-control" type="text" name="username" value="<%=user.getName()%>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="input-group">
                                        <label class="input-group-addon">联系方式</label>
                                        <input class="form-control" type="text" name="contact" value="<%=user.getContactInfo()%>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">权限</label>
                                    <input type="checkbox" name="rights" value="login" <%=(user.getRights().contains("login") ? "checked" : "")%>> 登陆
                                    <input type="checkbox" name="rights" value="admin" <%=(user.getRights().contains("admin") ? "checked" : "")%>> 管理员
                                    <input type="checkbox" name="rights" value="article" <%=(user.getRights().contains("article") ? "checked" : "")%>> 写作
                                </div>
                                <div class="form-group">
                                    <input class="btn btn-default btn-block" type="submit" value="更新">
                                </div>
                            </form>
                        </div>
                    </li>
                    <%
                            }
                    %>
                </ul>
                <%
                        }else{
                %>
                <div class="alert alert-warning">无结果</div>
                <%
                        }
                    }else{
                %>
                <div class="alert alert-warning">关键字格式有误.</div>
                <%
                    }
                }
            %>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
