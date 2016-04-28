<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/27
  Time: 下午3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${article.title} | 岭南软件园协会</title>
    <jsp:include page="temps/includeCSS.jsp"/>
    <jsp:include page="temps/includeScript.jsp"/>
</head>
<body>
<div class="container">
    <div class="page-header">
        <jsp:include page="temps/homepageHead.jsp"/>
        <script>document.getElementById("page-head-article" +
                "").setAttribute("class","active")</script>
    </div>
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="page-header">
                        <h2>${article.title} <small style="display: inline-block">By ${article.authorName} at ${article.createDate}</small></h2>
                        <div>标签 :
                            <s:iterator value="#request.article.tags" id="tagName">
                                <span class="label label-info">${tagName}</span>
                            </s:iterator>
                        </div>
                    </div>
                    <div class="container-fluid">
                        ${renderedArticle}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
