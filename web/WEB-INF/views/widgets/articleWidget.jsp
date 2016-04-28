<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/27
  Time: 下午3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:iterator value="#request.articleList">
    <a class="list-group-item" href="article?id=${id}">
        <h4>${title}</h4>
        <p>${previewSentences}....</p>
        <small>By ${authorName} at ${createDate}</small>
    </a>
</s:iterator>
