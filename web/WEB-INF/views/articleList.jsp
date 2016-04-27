<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/25
  Time: 下午4:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Articles</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <jsp:include page="temps/homepageHead.jsp"/>
            <script>document.getElementById("page-head-article").setAttribute("class","active")</script>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-8">
                    <div class="list-group">
                        <jsp:include page="widgets/articleWidget.jsp"/>
                        <s:if test="#request.articleList.size==0">
                            <div class="list-group-item disabled">
                                No Article...
                            </div>
                        </s:if>
                    </div>
                    <s:if test="#request.pageList!=null">
                        <div class="text-center">
                            <nav>
                                <ul class="pagination">
                                    <s:iterator value="#request.pageList" var="item">
                                        <li><a href="articles?page=${item}">${item}</a></li>
                                    </s:iterator>
                                </ul>
                            </nav>
                        </div>
                    </s:if>
                </div>
                <div class="col-sm-4">
                    <jsp:include page="widgets/userWidget.jsp"/>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
