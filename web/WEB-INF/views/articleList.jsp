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
            <h1>Lingnan Software(Center) Association</h1>
            <nav>
                <ul class="nav nav-pills">
                    <li><a href="index">Home</a></li>
                    <li class="active"><a href="articles">Article</a></li>
                    <li><a href="javascript:">Apps</a></li>
                    <li><a href="javascript:">Contact</a></li>
                </ul>
            </nav>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="list-group">
                    <s:iterator value="#request.articleList">
                        <a class="list-group-item" href="javascript:">
                            <h4>${title}</h4>
                            <p>${previewSentences}</p>
                            <small>By ${authorName} at ${createDate}</small>
                        </a>
                    </s:iterator>
                    <s:if test="#request.articleList.size==0">
                        <div class="list-group-item disabled">
                            No Article...
                        </div>
                    </s:if>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
