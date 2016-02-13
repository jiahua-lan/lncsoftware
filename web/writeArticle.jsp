<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="java.util.Date" %>
<%@ page import="cn.lncsoftware.data.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="cn.lncsoftware.common.StringTools" %><%--
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
    <link rel="stylesheet" href="css/bootstrap-markdown.min.css">
</head>
<%
    User passport = (User)session.getAttribute("passport");
    request.setCharacterEncoding("utf-8");
    String action = request.getParameter("action");
    String statusFlag = "";
    Article preloadArticle = null;
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

                    case "load":{
                        String articleID = request.getParameter("articleID");
                        if(articleID != null){
                            Article article = Article.getDao().get(new ObjectId(articleID));
                            if(article != null){
                                if(article.getAuthor().equals(passport.getObjectId())){
                                    preloadArticle = article;
                                }else{
                                    statusFlag = "not author";
                                }
                            }else{
                                statusFlag = "article not exist";
                            }
                        }else{
                            statusFlag = "request illegal";
                        }
                    };break;

                    case "update":{
                        String articleID = request.getParameter("articleID");
                        if(articleID != null){
                            Article article = Article.getDao().get(new ObjectId(articleID));
                            if(article != null){
                                String title = request.getParameter("title");
                                String tags = request.getParameter("tags");
                                String context = request.getParameter("context");
                                if(title != null && title.length() < 64 && !"".equals(title.trim())){
                                    article.setTitle(title);
                                    if(tags != null && !"".equals(tags.trim())){
                                        ArrayList<String> arrayList = new ArrayList<>();
                                        Collections.addAll(arrayList,tags.split(";"));
                                        article.setTags(arrayList);
                                    }
                                    article.setContext(context);
                                    article.setAuthor(passport.getObjectId());
                                    article.setDate(new Date());
                                    Article.getDao().update(article);
                                    statusFlag = "update success";
                                }else{
                                    statusFlag = "illegal title";
                                }
                            }else{
                                statusFlag = "article not exist";
                            }
                        }else{
                            statusFlag = "request illegal";
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

                case "not author":
        %>
        <div class="alert alert-danger">你不是文章的作者</div>
        <%
                    break;

                case "article not exist":
        %>
        <div class="alert alert-warning">文章已经不存在了</div>
        <%
                    break;

                case "request illegal":
        %>
        <div class="alert alert-danger">无法识别的请求</div>
        <%
                    break;

                case "update success":
        %>
        <div class="alert alert-success">更新成功</div>
        <%
                    break;
            }

            if(passport != null && (!"permission denied".equals(statusFlag) && !"not author".equals(statusFlag))){
                boolean modifyFlag = preloadArticle != null;
        %>
        <form class="form-horizontal" action="writeArticle.jsp" method="post">
            <input type="hidden" name="action" value="<%=modifyFlag ? "update" : "upload"%>">
            <% if(modifyFlag) {%>
            <input type="hidden" name="articleID" value="<%=preloadArticle.getObjectId().toHexString()%>">
            <%}%>
            <div class="form-group">
                <label class="control-label">标题 </label>
                <input class="form-control" type="text" name="title" placeholder="标题必须小于64个字符" value="<%=modifyFlag ? preloadArticle.getTitle() : ""%>">
            </div>
            <div class="form-group">
                <label class="control-label">标签 </label>
                <input class="form-control" type="text" name="tags" placeholder="选填，例如：tags1;tags2;" value="<%=modifyFlag ? StringTools.listTags(preloadArticle.getTags()) : ""%>">
            </div>
            <div class="form-group">
                <label class="control-label">内容 </label>
                <textarea class="form-control markdown" name="context" rows="10"><%=modifyFlag ? preloadArticle.getContext() : ""%></textarea>
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
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/markdown.min.js"></script>
<script src="js/bootstrap-markdown.js"></script>
<script>
    $(".markdown").markdown(
            {
                onPreview: function(e) {
                    return markdown.toHTML(e.getContent());
                }
            }
    );
</script>
</body>
</html>
