<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="cn.lncsoftware.data.Bulletin" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/6
  Time: 下午11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bulletin | Management</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/main.css">
</head>
<%
    User passport = (User)session.getAttribute("passport");
    String statusFlag = "";
    String action = request.getParameter("action");
    String type = request.getParameter("type");
    if(type == null || "".equals(type)) type = "mainPage";

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
            statusFlag = "permission recognised";
        }
    }
%>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <div class="page-header">
        <h1>Bulletins</h1>
    </div>
    <div class="row">
        <%
            if(passport == null || "permission denied".equals(statusFlag)) {
        %>
        <div class="col-md-6 col-md-offset-3">
            <%
                switch (statusFlag){
                    case "no login":
            %>
            <div class="alert alert-warning">Please <a class="alert-link" href="../index.jsp">Login</a></div>
            <%
                        break;

                    case "permission denied":
            %>
            <div class="alert alert-danger">Permission denied</div>
            <%
                        break;
                }
            %>
        </div>
        <%
            }else{
        %>
        <div class="col-md-12 lnc-marginBox">
            <div class="btn-group btn-group-justified">
                <a class="btn btn-default <%="mainPage".equals(type) ? "active" : "" %>" href="BulletinManagement.jsp?type=mainPage">MainPage</a>
                <a class="btn btn-default <%="article".equals(type) ? "active" : "" %>" href="BulletinManagement.jsp?type=article">Article</a>
                <a class="btn btn-default <%="app".equals(type) ? "active" : "" %>" href="BulletinManagement.jsp?type=app">App</a>
                <a class="btn btn-default <%="contact".equals(type) ? "active" : "" %>" href="BulletinManagement.jsp?type=contact">Contact</a>
            </div>
        </div>
        <%
            if(action != null){
                switch (action){
                    case "update":{
                        String bulletinID = request.getParameter("bulletinID");
                        if(bulletinID != null){
                            Bulletin bulletin = Bulletin.getDao().get(new ObjectId(bulletinID));
                            if(bulletin != null){
                                String context = request.getParameter("context");
                                if(context != null && !"".equals(context)){
                                    bulletin.setContext(context);
                                    bulletin.setDate(new Date());
                                    Bulletin.getDao().update(bulletin);
                                    statusFlag = "update success";
                                }else{
                                    statusFlag = "require context";
                                }
                            }else{
                                statusFlag = "bulletin deleted";
                            }
                        }
                    };break;

                    case "add":{
                        String btype = request.getParameter("btype");
                        if(btype != null){
                            switch (btype){
                                case "mainPage":
                                case "article":
                                case "app_guide":
                                case "app_info":{
                                    if(Bulletin.getDao().getBulletinBoard(btype) == null){
                                        String context = request.getParameter("context");
                                        if(context != null && !"".equals(context)){
                                            Bulletin bulletin = new Bulletin();
                                            bulletin.setType(btype);
                                            bulletin.setContext(context);
                                            bulletin.setDate(new Date());
                                            Bulletin.getDao().insert(bulletin);
                                            statusFlag = "add success";
                                        }else{
                                            statusFlag = "require context";
                                        }
                                    }else{
                                        statusFlag = "bulletin exist";
                                    }
                                };break;

                                case "contactInfo":
                                case "contactFriendLink":{
                                    String context = request.getParameter("context");
                                    if(context != null && !"".equals(context)){
                                        String link = request.getParameter("link");
                                        if(link != null || !"".equals(link)){
                                            Bulletin bulletin = new Bulletin();
                                            bulletin.setType(btype);
                                            bulletin.setContext(context);
                                            bulletin.setDate(new Date());
                                            bulletin.setLink(link);
                                            bulletin.setImageLink(request.getParameter("imageLink"));
                                            Bulletin.getDao().insert(bulletin);
                                            statusFlag = "add success";
                                        }else{
                                            statusFlag = "require link";
                                        }
                                    }else{
                                        statusFlag = "require context";
                                    }
                                };break;

                                default:{
                                    statusFlag = "type illegal";
                                };break;
                            }
                        }
                    };break;

                    case "delete":{
                        String bulletinID = request.getParameter("bulletinID");
                        if(bulletinID != null){
                            Bulletin bulletin = Bulletin.getDao().get(new ObjectId(bulletinID));
                            if(bulletin != null){
                                Bulletin.getDao().remove(bulletin);
                                statusFlag = "delete success";
                            }
                        }
                    };break;
                }
            }
        %>
        <div class="col-md-12 lnc-marginBox">
            <%
                switch (statusFlag){
                    case "update success":
            %>
            <div class="alert alert-success">Update success.</div>
            <%
                    break;

                case "require context":
            %>
            <div class="alert alert-warning">Context required</div>
            <%
                    break;

                case "bulletin deleted":
            %>
            <div class="alert alert-warning">Bulletin deleted</div>
            <%          break;

                case "add success":
            %>
            <div class="alert alert-success">Add success.</div>
            <%
                    break;

                case "bulletin exist":
            %>
            <div class="alert alert-warning">Bulletin exist.</div>
            <%
                    break;

                case "require link":
            %>
            <div class="alert alert-warning">Link was required.</div>
            <%
                    break;

                case "type illegal":
            %>
            <div class="alert alert-warning">Type illegal.</div>
            <%
                    break;

                case "delete success":
            %>
            <div class="alert alert-success">Delete success.</div>
            <%
                        break;
                }
            %>
        </div>
        <div class="col-md-12 lnc-marginBox">
            <div class="container-fluid">
                <%
                    switch (type){
                        case "mainPage":
                        case "article": {
                %>
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <%
                                if("mainPage".equals(type)) {
                            %>
                            Mainpage Bulletin Board
                            <%
                                } else if("article".equals(type)) {
                            %>
                            Article Bulletin Board
                            <%
                                }
                            %>
                        </div>
                        <div class="panel-body">
                            <%
                                Bulletin bulletin = Bulletin.getDao().getBulletinBoard(type);
                            %>
                            <form class="form-horizontal" action="BulletinManagement.jsp" method="post">
                                <div class="container-fluid">
                                    <input type="hidden" name="action" value="<%=bulletin == null ? "add" : "update"%>">
                                    <input type="hidden" name="type" value="<%=type%>">
                                    <div class="form-group">
                                        <label class="control-label">Context:</label>
                                        <textarea class="form-control" name="context"><%=bulletin == null ? "" : bulletin.getContext()%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <input class="btn btn-primary" type="submit" value="<%=bulletin == null ? "Create" : "Update"%>">
                                        <% if(bulletin != null) {%>
                                        <input type="hidden" name="bulletinID" value="<%=bulletin.getObjectId().toHexString()%>">
                                        <a class="btn btn-danger" href="BulletinManagement.jsp?action=delete&bulletinID=<%=bulletin.getObjectId().toHexString()%>">Delete</a>
                                        <%}else{%>
                                        <input type="hidden" name="btype" value="<%=type%>">
                                        <%}%>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <%
                        };break;

                    case "app":{
                        String[] s = new String[]{"app_guide","app_info"};
                        for(String s1 : s){
                            Bulletin bulletin = Bulletin.getDao().getBulletinBoard(s1);
                %>
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <%
                                switch (s1){
                                    case "app_guide":
                            %>
                            Guide Bulletin
                            <%
                                        break;

                                    case "app_info":
                            %>
                            Information Bulletin
                            <%
                                        break;
                                }
                            %>
                        </div>
                        <div class="panel-body">
                            <div class="container-fluid">
                                <form class="form-horizontal" action="BulletinManagement.jsp" method="post">
                                    <input type="hidden" name="action" value="<%=bulletin == null ? "add" : "update"%>">
                                    <input type="hidden" name="type" value="<%=type%>">
                                    <div class="form-group">
                                        <label class="control-label">Context:</label>
                                        <textarea class="form-control" name="context"><%=bulletin == null ? "" : bulletin.getContext()%></textarea>
                                    </div>
                                    <div class="form-group">
                                        <input class="btn btn-primary" type="submit" value="<%=bulletin == null ? "Create" : "Update"%>">
                                        <%if(bulletin != null){%>
                                        <input type="hidden" name="bulletinID" value="<%=bulletin.getObjectId().toHexString()%>">
                                        <a class="btn btn-danger" href="BulletinManagement.jsp?action=delete&bulletinID=<%=bulletin.getObjectId().toHexString()%>&type=app">Delete</a>
                                        <%}else{%>
                                        <input type="hidden" name="btype" value="<%=s1%>">
                                        <%}%>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                        }
                    };break;

                    case "contact":{
                %>
                <div class="col-md-6">
                    <div class="panel panel-info">
                        <div class="panel-heading">Contact info</div>
                        <%
                            List<Bulletin> bulletins = Bulletin.getDao().getBulletinItems("contactInfo");
                        %>
                        <ul class="list-group">
                            <%
                                if(bulletins != null && bulletins.size() > 0){
                                    for (Bulletin bulletin : bulletins){
                            %>
                            <li class="list-group-item">
                                <div class="container-fluid">
                                    <form class="form-horizontal" action="BulletinManagement.jsp" method="post">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="type" value="<%=type%>">
                                        <input type="hidden" name="bulletinID" value="<%=bulletin.getObjectId().toHexString()%>">
                                        <div class="form-group input-group">
                                            <span class="input-group-addon">Context</span>
                                            <input class="form-control" type="text" name="context" value="<%=bulletin.getContext()%>">
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon">Link</span>
                                            <input class="form-control" type="url" name="link" value="<%=bulletin.getLink()%>">
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon">Image</span>
                                            <input class="form-control" type="url" name="imageLin" value="<%=bulletin.getImageLink()%>">
                                        </div>
                                        <div class="form-group form-group">
                                            <input class="btn btn-primary" type="submit" value="update">
                                            <a class="btn btn-danger" href="BulletinManagement.jsp?action=delete&bulletinID=<%=bulletin.getObjectId().toHexString()%>&type=<%=type%>">Delete</a>
                                        </div>
                                    </form>
                                </div>
                            </li>
                            <%
                                    }
                                }else{
                            %>
                            <li class="list-group-item list-group-item-heading disabled">Nothing here...</li>
                            <%
                                }
                            %>
                            <li class="list-group-item">
                                <div class="container-fluid">
                                    <form class="form-horizontal" action="BulletinManagement.jsp" method="post">
                                        <input type="hidden" name="action" value="add">
                                        <input type="hidden" name="btype" value="contactInfo">
                                        <input type="hidden" name="type" value="<%=type%>">
                                        <div class="form-group input-group">
                                            <span class="input-group-addon">Context</span>
                                            <input class="form-control" type="text" name="context">
                                        </div>
                                        <div class="form-group input-group">
                                            <label class="input-group-addon">Link</label>
                                            <input class="form-control" type="text" name="link">
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon">ImageLink</span>
                                            <input class="form-control" type="url" name="imageLink">
                                        </div>
                                        <div class="form-group form-group">
                                            <input class="btn btn-success" type="submit" value="Add">
                                        </div>
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="panel panel-success">
                        <div class="panel-heading">Friend Links</div>
                        <ul class="list-group">
                            <%
                                bulletins = Bulletin.getDao().getBulletinItems("contactFriendLink");
                                if(bulletins != null && bulletins.size() > 0){
                                    for (Bulletin bulletin : bulletins){
                            %>
                            <li class="list-group-item">
                                <div class="container-fluid">
                                    <form action="BulletinManagement.jsp" method="post">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="type" value="<%=type%>">
                                        <input type="hidden" name="bulletinID" value="<%=bulletin.getObjectId().toHexString()%>">
                                        <div class="form-group input-group">
                                            <span class="input-group-addon">Context</span>
                                            <input class="form-control" type="text" name="context" value="<%=bulletin.getContext()%>">
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon">Link</span>
                                            <input class="form-control" type="url" name="link" value="<%=bulletin.getLink()%>">
                                        </div>
                                        <div class="form-group">
                                            <input class="btn btn-primary" type="submit" value="Update">
                                            <a class="btn btn-danger" href="BulletinManagement.jsp?action=delete&bulletinID=<%=bulletin.getObjectId().toHexString()%>&type=<%=type%>">Delete</a>
                                        </div>
                                    </form>
                                </div>
                            </li>
                            <%
                                }
                            }else{
                            %>
                            <li class="list-group-item list-group-item-heading disabled">Nothings here..</li>
                            <%
                                }
                            %>
                            <li class="list-group-item">
                                <div class="container-fluid">
                                    <form class="form-horizontal" action="BulletinManagement.jsp" method="post">
                                        <input type="hidden" name="action" value="add">
                                        <input type="hidden" name="btype" value="contactFriendLink">
                                        <input type="hidden" name="type" value="<%=type%>">
                                        <div class="form-group input-group">
                                            <span class="input-group-addon">Context</span>
                                            <input class="form-control" type="text" name="context">
                                        </div>
                                        <div class="form-group input-group">
                                            <span class="input-group-addon">Link</span>
                                            <input class="form-control" type="text" name="link">
                                        </div>
                                        <div class="form-group">
                                            <input class="btn btn-success" type="submit" value="Add">
                                        </div>
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <%
                        };break;
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
