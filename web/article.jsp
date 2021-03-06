<%@ page import="cn.lncsoftware.data.Article" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.User" %>
<%@ page import="java.util.Collections" %>
<%@ page import="cn.lncsoftware.data.Bulletin" %>
<%@ page import="org.bson.types.ObjectId" %>
<%@ page import="cn.lncsoftware.common.StringTools" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/7
  Time: 上午1:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>岭南软件园 文章</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<%
    User passport = (User)session.getAttribute("passport");
    request.setCharacterEncoding("utf-8");
    String action = request.getParameter("action");
%>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <script>
        document.getElementById("nav-article").setAttribute("class","active");
    </script>
    <%
        if(action == null){
    %>
    <div class="page-header">
        <h1>文章</h1>
        <%
            Bulletin bulletin = Bulletin.getDao().getBulletinBoard("article");
            if(bulletin != null){
        %>
        <p><%=bulletin.getContext()%></p>
        <%
            }
        %>
    </div>
    <%
        }
    %>
    <div class="row">
        <%
            if(action != null){
                switch (action){
                    case "details":{
                        String articleID = request.getParameter("articleID");
                        if(articleID != null){
                            Article article = Article.getDao().get(new ObjectId(articleID));
                            if(article != null){
                                User user = User.getDao().get(article.getAuthor());
                                String authorName = "**用户不存在**";
                                if (user != null) authorName = user.getName();
        %>
        <div class="col-md-12">
            <div class="container-fluid">
                <div class="page-header">
                    <h1><%=article.getTitle()%> <small>By: <%=authorName%> at <%=StringTools.convertDate(article.getDate())%></small>
                        <%if(passport.getObjectId() != null && passport.getObjectId().equals(article.getAuthor())){%>
                        <a class="btn btn-sm btn-info" href="writeArticle.jsp?action=load&articleID=<%=articleID%>">修改</a>
                        <%}%></h1>
                    <label>标签:
                        <%
                            if(article.getTags() != null && article.getTags().size() > 0){
                                for(String s : article.getTags()){
                        %><span class="label label-default"><%=s%></span> <%
                                }
                            }else{
                        %>
                        无
                        <%
                            }
                        %>
                    </label>
                </div>
                <p id="markdown-context"><%=article.getContext()%></p>
            </div>
        </div>
        <%
                            }else{
        %>
        <div class="col-md-6 col-md-offset-3"><div class="alert alert-warning">文章不存在.</div></div>
        <%
                            }
                        }else{
        %>
        <div class="col-md-6 col-md-offset-3"><div class="alert alert-warning">未知请求，臣做不到嗷.</div></div>
        <%
                        }
                    };break;

                    default:
        %>
        <div class="col-md-6 col-md-offset-3"><div class="alert alert-warning">文章不存在.</div></div>
        <%
                        break;
                }
            }else{
        %>
        <div class="col-md-8 col-md-offset-2">
            <div class="list-group">
                <%
                    String spage = request.getParameter("page");
                    int ipage = 1;
                    if(spage != null){
                        ipage = Integer.parseInt(spage);
                    }
                    List<Article> articles = Article.getDao().getPage(ipage);
                    if(articles == null || articles.size() == 0){
                %>
                <a class="list-group-item disabled" href="#">噢，现在还没有文章.</a>
                <%
                }else{
                    Collections.reverse(articles);
                    for(Article article : articles){
                        String author = User.getDao().get(article.getAuthor()).getName();
                        if(author == null) author = "**用户不存在**";
                %>
                <a class="list-group-item" href="article.jsp?action=details&articleID=<%=article.getObjectId().toHexString()%>">
                    <div class="media">
                        <div class="media-body">
                            <h4><%=article.getTitle()%> <small>by :<%=author%></small></h4>
                            <p><%=article.getPreviewSentences()%><%=(article.getContext().length() > 100 ? " ...(共"+ article.getContext().length() +"字)":"")%></p>
                        </div>
                    </div>
                </a>
                <%
                    }
                %>
            </div>
            <%
                int totalPages = Article.getDao().getPages();
                if(totalPages > 0){
            %>
            <nav>
                <ul class="pagination">
                    <%
                        for(int i = 0; i < totalPages; i++){
                    %>
                    <li><a href="article.jsp?page=<%=i + 1%>"><%=i + 1%></a></li>
                    <%
                        }
                    %>
                </ul>
            </nav>
            <%
                    }
                }
            %>
        </div>
        <%
            }
        %>
    </div>
</div>
<script src="js/markdown.min.js"></script>
<script>
    var context = document.getElementById("markdown-context").innerHTML;
    document.getElementById("markdown-context").innerHTML = markdown.toHTML(context);
</script>
</body>
</html>
