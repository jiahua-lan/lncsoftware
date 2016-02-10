<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="cn.lncsoftware.data.AppInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/6
  Time: 下午11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Application | Management</title>
</head>
<body>
<%
    User passport = (User)session.getAttribute("passport");
    String statusFlag = "";
    String action = request.getParameter("action");

    if(passport == null){
%>
Please <a href="../index.jsp">Login</a>.<br>
<%
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

    if(!statusFlag.equals("")){
%><%=statusFlag%><%
    }

    if(passport != null && "permission recognition".equals(statusFlag)){
%>
<form action="AppInfoManagement.jsp" method="post">
    <input type="hidden" name="action" value="create">
    <label>Title: <input type="text" name="title"></label><br>
    <label>Description: <input type="text" name="description"></label><br>
    <label>Thumbnail Image: <input type="file" name="imageCode"></label><br>
    <label>Link: <input type="text" name="link"></label><br>
    <label>Status:
        <input type="radio" name="status" value="working" checked>Working
        <input type="radio" name="status" value="repairing">Repairing
        <input type="radio" name="status" value="disable">Disable
    </label><br>
    <input type="submit" value="create">
</form>
<br>
<form action="AppInfoManagement.jsp" method="post">
    <input type="hidden" name="action" value="search">
    <label>Search:
        <input type="text" name="keyword">
        <input type="checkbox" name="useRegex" value="useRegex">Use Regex
        <input type="submit" value="search">
    </label>
</form>
<br>
<%
        if(action != null){
            switch (action){
                case "create":{
                    String title = request.getParameter("title");
                    if(title != null && title.length() < 32){
                        String link = request.getParameter("link");
                        if(link != null && link.length() < 256){
                            AppInfo appInfo = new AppInfo();
                            appInfo.setTitle(title);
                            appInfo.setLink(link);
                            String description = request.getParameter("description");
                            if(description != null) appInfo.setDescription(description);
                            String image = request.getParameter("imageCode");
                            if(image != null) appInfo.setImageCode(image);
                            String status = request.getParameter("status");
                            if(status != null) appInfo.setStatus(status); else appInfo.setStatus("disable");
                            appInfo.setDate(new Date());
                            AppInfo.getDao().insert(appInfo);
%>
Create successful.<br>
<%
                        }else{
%>
Link must in 256 chars.<br>
<%
                        }
                    }else{
%>
Illegal title.<br>
<%
                    }
                };break;

                case "search":{
                    String keyword = request.getParameter("keyword");
                    String useRegex = request.getParameter("useRegex");
                    if(keyword != null){
                        List<AppInfo> appInfos = AppInfo.getDao().find("title",("useRegex".equals(useRegex) ? keyword : ".*"+keyword+".*"));
                        if(appInfos != null && appInfos.size() > 0){
                            for(AppInfo appInfo : appInfos){
%>
<form action="AppInfoManagement.jsp" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="appID" value="<%=appInfo.getObjectId().toHexString()%>">
    <label>Title: <input type="text" name="title" value="<%=appInfo.getTitle()%>"></label><br>
    <label>Link: <input type="url" name="link" value="<%=appInfo.getLink()%>"></label><br>
    <label>Description: <input type="text" name="description" value="<%=appInfo.getDescription()%>"></label><br>
    <label>Image: <label><%=appInfo.getImageCode()%></label> <input type="file" name="image"></label><br>
    <label>Status:
        <input type="radio" name="status" value="working" <%=("working".equals(appInfo.getStatus()) ? "checked" : "")%>>Working
        <input type="radio" name="status" value="repairing" <%=("repairing".equals(appInfo.getStatus()) ? "checked" : "")%>>Repairing
        <input type="radio" name="status" value="disable" <%=("disable".equals(appInfo.getStatus()) ? "checked" : "")%>>Disable
    </label><br>
    <input type="submit" value="update">
</form>
<%
                            }
                        }
                    }
                };break;

                case "update":{
                    String appID = request.getParameter("appID");
                    if(appID != null){
                        AppInfo appInfo = AppInfo.getDao().get(new ObjectId(appID));
                        if(appInfo != null){
                            String title = request.getParameter("title");
                            if(title != null && title.length() < 32){
                                String link = request.getParameter("link");
                                if(link != null && link.length() < 256){
                                    appInfo.setTitle(title);
                                    appInfo.setLink(link);
                                    String description = request.getParameter("description");
                                    if(description != null) appInfo.setDescription(description);
                                    String image = request.getParameter("imageCode");
                                    if(image != null) appInfo.setImageCode(image);
                                    String status = request.getParameter("status");
                                    if(status != null) appInfo.setStatus(status); else appInfo.setStatus("disable");
                                    AppInfo.getDao().update(appInfo);
%>
Update successful.<br>
<%
                                    }else{
%>
Link must in 256 chars.<br>
<%
                                    }
                            }else{
%>
Illegal title.<br>
<%
                            }
                        }else{
%>
App not exist.
<%
                        }
                    }
                };break;
            }
        }
    }
%>
</body>
</html>
