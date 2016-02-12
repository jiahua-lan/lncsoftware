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
    <title>岭南软件园 写文章</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<%
    User passport = (User)session.getAttribute("passport");
    String action = request.getParameter("action");
    String statusFlag = "";
    if(passport == null){
        statusFlag = "no login";
}else{
    for(String s : passport.getRights()){
        if("article".equals(s)){
            statusFlag = "permission recognized";
            break;
        }
        statusFlag = "permission denied";
    }

    switch (statusFlag){
        case "permission recognized":{
            if(action != null){
                switch (action){
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
                            statusFlag = "submit success";
                        }else{
                            statusFlag = "illegal title";
                        }
                    };break;
                }
            }
        };break;
    }
}
%>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <script>
        document.getElementById("nav-article").setAttribute("class","active");
    </script>
    <div class="page-header">
        <h1>写文章</h1>
        <p>写文章之前先在其它文本编辑器里写一遍，然后粘贴到此。网站不提供保存草稿功能。<br>
          网站文章统一使用Markdown格式，如你并不清楚什么是Markdown格式，请先
          <a href="http://baike.baidu.com/link?url=IuGaek4XNBNB7cC2_7WcyffLr5-SfsaWb8brCguNCZHnQbl3gxLLdc5aZPX2bpUK1sQu2UxHz8ggxbFTWQ3ySq">了解</a>
          此格式后再下笔。
        </p>
    </div>
    <div class="container-fluid">
        <%
            switch (statusFlag){
                case "no login":
        %>
        <div class="alert alert-warning">请先 <a class="alert-link" href="index.jsp">登录</a></div>
        <%
                    break;

                case "permission recognized":
                    break;

                case "permission denied":
        %>
        <div class="alert alert-danger">您并没有写文章的权限。若要写文章，请通知管理员为您开放此权限</div>
        <%
                    break;

                case "submit success":
        %>
        <div class="alert alert-success">文章提交成功</div>
        <%
                    break;

                case "illegal title":
        %>
        <div class="alert alert-warning">标题格式不正确</div>
        <%
                    break;
            }

            if(passport != null && !"permission denied".equals(statusFlag)){
        %>
        <form class="form-horizontal" action="writeArticle.jsp" method="post">
            <input type="hidden" name="action" value="upload">
            <div class="form-group">
                <label class="control-label">标题 </label>
                <input class="form-control" type="text" name="title" placeholder="标题必须小于64个字符">
            </div>
            <div class="form-group">
                <label class="control-label">标签 </label>
                <input class="form-control" type="text" name="tags" placeholder="选填，例如：tags1;tags2;">
            </div>
            <div class="form-group">
                <label class="control-label">内容 </label>
                <textarea class="form-control" name="context"></textarea>
            </div>
            <div class="form-group">
                <input class="btn btn-success" type="submit" value="提交">
            </div>
        </form>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
