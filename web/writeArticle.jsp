<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="cn.lncsoftware.data.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="org.bson.types.ObjectId" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/7
  Time: 下午10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Write Article</title>
</head>
<body>
<%
    User passport = (User)session.getAttribute("passport");
    String action = request.getParameter("action");
    String statusFlag = "";
    if(passport == null){
%>
Please <a href="index.jsp">Login</a><br>
<%
    }else{
        if(action != null){
            switch (action){
                case "new":{
%>
<form action="writeArticle.jsp" method="post">
    <input type="hidden" name="action" value="upload">
    <label>Title <input type="text" name="title" placeholder="less than 64 chars"></label><br>
    <label>Author: <%=passport.getName()%></label><br>
    <label>Today is: <%=new Date().toString()%></label><br>
    <label>Tags:<input type="text" name="tags" placeholder="tags1;tags2;"></label><br>
    <label>Context:<br><textarea name="context"></textarea></label><br>
    <input type="submit" value="submit">
</form>
<%
                };break;

                case "upload":{
                    String title = request.getParameter("title");
                    String tags = request.getParameter("tags");
                    String context = request.getParameter("context");
                    if(title != null && title.length() < 64 && !"".equals(title.trim())){
                        Article article = new Article();
                        article.setTitle(title);
                        if(tags != null && !"".equals(tags.trim())){
                            ArrayList<String> arrayList = new ArrayList<>();
                            Collections.addAll(arrayList,tags.split(";"));
                            article.setTags(arrayList);
                        }
                        article.setContext(context);
                        article.setAuthor(passport.getObjectId());
                        article.setDate(new Date());
                        article.setStatus("show");
                        Article.getDao().insert(article);
%>
Submit successful.<br>
<%
                    }else{
%>
Illegal Title.<br>
<%
                    }
                };break;
            }
        }
    }
%>
</body>
</html>
