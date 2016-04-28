<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/28
  Time: 下午1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Write Article | LNCSA</title>
    <jsp:include page="temps/includeCSS.jsp"/>
    <jsp:include page="temps/includeScript.jsp"/>
    <jsp:include page="temps/includeHeavyScript.jsp"/>
</head>
<body>
<div class="container">
    <div class="page-header">
        <jsp:include page="temps/homepageHead.jsp"/>
        <script>document.getElementById("page-head-article").setAttribute("class", "active")</script>
    </div>
    <div class="container-fluid">
        <form class="form-horizontal" action="article-save" method="post">
            <input type="hidden" name="id" value="${articleID}">
            <div class="form-group">
                <label class="control-label">标题 </label>
                <input class="form-control" type="text" name="title" placeholder="标题必须小于64个字符" value="${article.title}">
            </div>
            <div class="form-group">
                <label class="control-label">标签 </label>
                <input class="form-control" type="text" name="tags" placeholder="选填，例如：tags1;tags2;"
                       value="<s:if test="#request.article!=null"><s:iterator value="#request.article.tags" var="item">${item};</s:iterator></s:if>">
            </div>
            <div class="form-group">
                <label class="control-label">Preview Sentences</label>
                <input class="form-control" type="text" name="previewSentences"
                       placeholder="An introduction in 100 words" value="${article.previewSentences}">
            </div>
            <div class="form-group">
                <label class="control-label">内容 </label>
                <textarea class="form-control markdown" name="context"
                          rows="20">${article.context}</textarea>
            </div>
            <div class="form-group">
                <input class="btn btn-success btn-block" type="submit" value="提交">
            </div>
        </form>
    </div>
</div>
<script>$(".markdown").markdown({
    onPreview: function (e) {
        return markdown.toHTML(e.getContent());
    }
});</script>
</body>
</html>
