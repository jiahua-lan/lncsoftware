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
    <title>Applications</title>
</head>
<body>
<h1>Applications</h1>
<%
    Bulletin[] bulletins = new Bulletin[2];
    bulletins[0] = Bulletin.getDao().getBulletinBoard("app_guide");
    bulletins[1] = Bulletin.getDao().getBulletinBoard("app_info");
    if(bulletins[0] != null){
%>
<h6>Guide</h6>
<label><%=bulletins[0].getContext()%></label>
<hr>
<%
    }

    if(bulletins[1] != null){
%>
<h6>Announce</h6>
<label><%=bulletins[1].getContext()%></label>
<hr>
<%
    }

    List<AppInfo> appInfos = AppInfo.getDao().find("title",".+");
    if(appInfos != null || appInfos.size() > 0){
        for(AppInfo appInfo : appInfos){
%>
<label>
    [<%=appInfo.getImageCode()%>]
    <a href="<%=("disable".equals(appInfo.getStatus()) ? "" : appInfo.getLink())%>"><%=appInfo.getTitle()%></a>
    <small><%=appInfo.getStatus()%></small>
</label><br>
<small><%=appInfo.getDescription()%></small><br>
<small><%=appInfo.getDate()%></small>
<hr>
<%
        }
    }
%>
</body>
</html>
