<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/5/11
  Time: 上午11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article Management | LNCSA</title>
    <jsp:include page="../temps/includeCSS.jsp"/>
    <jsp:include page="../temps/includeHeavyScript.jsp"/>
</head>
<body>
<div class="container">
    <div class="page-header">
        <jsp:include page="../temps/adminPageHead.jsp"/>
        <script>document.getElementById("admin-head-article").setAttribute("class","active")</script>
        <script>
            function loadPreview(id){
                $('#previewArea').load('/lncsa/articlePreview?preId='+id);
            }
        </script>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div>
                <form class="form-horizontal" method="post" action="<s:url namespace="/admin" action="article-admin-search"/>">
                    <div class="input-group">
                        <span class="input-group-addon">Searching</span>
                        <input class="form-control" name="keyword" value="${request.keyword}"/>
                        <span class="input-group-btn">
                            <input type="submit" class="btn btn-default" value="Search">
                        </span>
                    </div>
                    <div class="panel panel-success" style="margin-top: 5pt">
                        <div class="list-group">
                            <jsp:include page="widget/pureArticleList.jsp"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-6" id="previewArea">

        </div>
    </div>
</div>
</body>
</html>
