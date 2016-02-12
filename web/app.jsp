<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.AppInfo" %>
<%@ page import="cn.lncsoftware.data.Bulletin" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/9
  Time: 下午8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>岭南软件园 应用</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <script>
        document.getElementById("nav-app").setAttribute("class","active");
    </script>
    <div class="page-header">
        <h1>应用</h1>
        <%
            Bulletin bulletin = Bulletin.getDao().getBulletinBoard("app_guide");
            if(bulletin != null){
        %>
        <p><%=bulletin.getContext()%></p>
        <%
            }
        %>
    </div>
    <div class="row">
        <div class="col-md-8">
            <%
                List<AppInfo> appInfos = AppInfo.getDao().find("title",".+");
                if(appInfos != null || appInfos.size() > 0){
                    for(AppInfo appInfo : appInfos){
            %>
            <div class="media">
                <div class="media-left">
                    <img class="media-object" <%=("".equals(appInfo.getImageCode().trim()) ? "data-src='holder.js/60x60'" : "src='" + appInfo.getImageCode() + "' width='60pt' height='60pt'")%>>
                </div>
                <div class="media-body">
                    <h4 class="media-heading"><a href="<%=("disable".equals(appInfo.getStatus()) ? "" : appInfo.getLink())%>"><%=appInfo.getTitle()%></a>
                        <%
                            switch (appInfo.getStatus()){
                                case "working":
                        %>
                        <span class="label label-success">工作中</span>
                        <%
                                    break;

                                case "repairing":
                        %>
                        <span class="label label-warning">维护期</span>
                        <%
                                    break;

                                case "disable":
                        %>
                        <span class="label label-default">已失效</span>
                        <%
                                    break;
                            }
                        %>
                    </h4>
                    <%=appInfo.getDescription()%>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
        <div class="col-md-4">
            <div class="panel panel-primary">
                <div class="panel-heading">通知</div>
                <div class="panel-body">
                    <%
                        Bulletin bulletin1 = Bulletin.getDao().getBulletinBoard("app_info");
                        if(bulletin1 != null){
                    %>
                    <%=bulletin1.getContext()%>
                    <%
                        }else{
                    %>现在并没有什么通知<%
                        }
                    %>
                </div>
                <%
                    if(bulletin1 != null){
                %>
                <div class="panel-footer"><%=bulletin1.getDate()%></div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>
<script src="js/holder.min.js"></script>
</body>
</html>
