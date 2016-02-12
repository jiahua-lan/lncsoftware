<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="cn.lncsoftware.data.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.common.StringTools" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/6
  Time: 下午11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>岭南软件园 文章管理</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/main.css">
</head>
<%
    User passport = (User)session.getAttribute("passport");
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
            statusFlag = "permission recognised";
        }
    }
%>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <div class="page-header">
        <h1>Article Management</h1>
    </div>
    <div class="row">
        <%
            if(passport == null && "permission denied".equals(statusFlag)) {
        %>
        <div class="col-md-6 col-md-offset-3">
            <%
                switch (statusFlag){
                    case "no login":
            %>
            <div class="alert alert-warning">请先 <a href="../index.jsp">登录</a></div>
            <%
                        break;

                    case "permission denied":
            %>
            <div class="alert alert-danger">抱歉，您没有权限</div>
            <%
                        break;
                }
            %>
        </div>
        <%
            }else{
        %>
        <div class="col-md-12">
            <div class="container-fluid">
                <form class="form-horizontal" action="ArticleManagement.jsp" method="post">
                    <input type="hidden" name="action" value="search">
                    <div class="input-group">
                        <span class="input-group-addon"><input type="checkbox" name="useRegex" value="useRegex"> 使用正则表达式</span>
                        <input class="form-control" type="text" name="keyword" value="" placeholder="输入文章标题或者部分内容">
                        <span class="input-group-btn"><input class="btn btn-default" type="submit" value="搜索"></span>
                    </div>
                </form>
            </div>
            <div class="container-fluid">
                <%
                    if(action != null){
                        switch (action){
                            case "delete":{
                                String articleID = request.getParameter("articleID");
                                if(articleID != null){
                                    Article.getDao().remove(Article.getDao().get(new ObjectId(articleID)));
                                    statusFlag = "delete success";
                                }else{
                                    statusFlag = "delete failed";
                                }
                            };break;

                            case "update":{
                                String articleID = request.getParameter("articleID");
                                if(articleID != null) {
                                    Article article = Article.getDao().get(new ObjectId(articleID));
                                    if(article != null){
                                        String title = request.getParameter("title");
                                        if(title != null && title.length() < 64 && !"".equals(title.trim())) {
                                            article.setTitle(title);
                                            String tags = request.getParameter("tags");
                                            if (tags != null) article.setTags(StringTools.splitTags(tags));
                                            String context = request.getParameter("context");
                                            if (context != null) article.setContext(context);
                                            String status = request.getParameter("status");
                                            if (status != null) article.setStatus(status);
                                            Article.getDao().update(article);
                                            statusFlag = "update success";
                                        }else{
                                            statusFlag = "illegal title";
                                        }
                                    }else{
                                        statusFlag = "article not exist";
                                    }
                                }
                            };break;
                        }
                    }

                    switch (statusFlag){
                        case "delete success":
                %>
                <div class="alert-success alert">删除成功</div>
                <%                            break;

                        case "delete failed":
                %>
                <div class="alert-warning alert">删除失败</div>
                <%
                            break;

                        case "update success":
                %>
                <div class="alert-success alert">更新成功</div>
                <%            break;

                        case "illegal title":
                %>
                <div class="alert-warning alert">标题格式不正确</div>
                <%
                            break;

                        case "article not exist":
                %>
                <div class="alert-warning alert">文章已经不存在了噢</div>
                <%
                            break;
                    }
                %>
            </div>
            <div class="container-fluid">
                <%
                    if("search".equals(action)){

                        String keyword = request.getParameter("keyword");
                        boolean useRegex = "useRegex".equals(request.getParameter("useRegex"));
                        if(keyword != null && (keyword.matches("[\\d\\w\\-_]{1,32}") || useRegex)){
                            List<Article> articleList = Article.getDao().find(useRegex ? keyword : ".*"+keyword+".*");
                            if(articleList != null && articleList.size() > 0){
                %>
                <ul class="list-group">
                    <%
                        for(Article article : articleList){
                            User author = User.getDao().get(article.getAuthor());
                            String authorName = "**用户不存在**";
                            if (author != null) authorName = author.getName();
                    %>
                    <li class="list-group-item">
                        <div class="container-fluid">
                            <form class="form-horizontal" action="ArticleManagement.jsp" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="articleID" value="<%=article.getObjectId().toHexString()%>">
                                <div class="form-group">
                                    <label class="control-label">标题: </label>
                                    <input class="form-control" type="text" name="title" value="<%=article.getTitle()%>">
                                </div>
                                <div class="form-group">
                                    <div class="col-md-4">
                                        <label>作者:</label>
                                        <label><%=authorName%></label>
                                    </div>
                                    <div class="col-md-4">
                                        <label>日期:</label>
                                        <label><%=article.getDate().toString()%></label>
                                    </div>
                                    <div class="col-md-4">
                                        <label>状态:</label>
                                        <input type="radio" name="status" value="show" <%=("show".equals(article.getStatus())) ? "checked" : ""%>> 显示
                                        <input type="radio" name="status" value="hidden" <%=("hidden".equals(article.getStatus()) ? "checked" : "")%>> 隐藏
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">标签:</label>
                                    <input class="form-control" type="text" name="tags" value="<%=StringTools.listTags(article.getTags())%>">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">内容:</label>
                                    <textarea class="form-control" name="context"><%=article.getContext()%></textarea>
                                </div>
                                <div class="form-group">
                                    <input class="btn btn-default" type="submit" value="更新">
                                    <a class="btn btn-danger" href="ArticleManagement.jsp?action=delete&articleID=<%=article.getObjectId().toHexString()%>">删除</a>
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
                <div class="alert alert-warning">关键字格式不正确</div>
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
