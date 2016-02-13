<%@ page import="java.util.List" %>
<%@ page import="cn.lncsoftware.data.Bulletin" %><%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/2/11
  Time: 上午12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>岭南软件园 联系</title>
</head>
<body>
<div class="container">
    <jsp:include page="navbar.jsp"/>
    <script>
        document.getElementById("nav-contact").setAttribute("class","active");
    </script>
    <div class="page-header">
        <h1>联系信息</h1>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/main.css">
    </div>
    <div class="row">
        <div class="col-sm-8 col-sm-offset-2">
            <h2>联系我们</h2>
            <div class="list-group">
                <%
                    List<Bulletin> contact = Bulletin.getDao().getBulletinItems("contactInfo");
                    if(contact != null && contact.size() > 0){
                        for (Bulletin b : contact){
                %>
                <a class="list-group-item list-group-item-text" href="<%=b.getLink()%>">
                    <div class="media">
                        <div class="media-left">
                            <img class="media-object" <%="".equals(b.getImageLink().trim()) || b.getImageLink().equals(null) ? "data-src='holder.js/60x60'" : "src='" +
                             b.getImageLink() +"'"%> width="60pt" height="60pt">
                        </div>
                        <div class="media-body">
                            <p class="markdown-context"><%=b.getContext()%></p>
                        </div>
                    </div>
                </a>
                <%
                    }
                }else{
                %>
                <a class="list-group-item disabled" href="#">噢，并没有什么东西……</a>
                <%
                    }
                %>
            </div>
            <h2>友情链接</h2>
            <div class="list-group">
                <%
                    contact = Bulletin.getDao().getBulletinItems("contactFriendLink");
                    if(contact != null && contact.size() > 0){
                        for(Bulletin bulletin : contact){
                %>
                <a class="list-group-item list-group-item-success" href="<%=bulletin.getLink()%>"><%=bulletin.getContext()%></a>
                <%
                        }
                    }else{
                %>
                <a class="list-group-item disabled" href="#">噢，这里没东西……</a>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/holder.min.js"></script>
<script src="js/markdown.min.js"></script>
<script>
    $(".markdown-context").each(function () {
        context = $(this).html();
        $(this).html(markdown.toHTML(context));
    });
</script>
</body>
</html>
