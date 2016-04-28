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
    <title>文章｜岭南软件园协会</title>
    <jsp:include page="temps/includeCSS.jsp"/>
    <jsp:include page="temps/includeScript.jsp"/>
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
                                暂时没有文章……
                            </div>
                        </s:if>
                    </div>
                    <jsp:include page="widgets/pageNavWidget.jsp"/>
                </div>
                <div class="col-sm-4">
                    <jsp:include page="widgets/userWidget.jsp"/>
                    <form action="articles.action" method="post">
                        <div class="input-group">
                            <span class="input-group-addon">共 ${pageCount} 页</span>
                            <input type="number" name="page" class="form-control" value="${currentPage}">
                            <span class="input-group-btn"><input class="btn btn-default" type="submit" value="跳转"></span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
