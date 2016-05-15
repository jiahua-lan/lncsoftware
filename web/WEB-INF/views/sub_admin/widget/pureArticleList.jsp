<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/5/11
  Time: 下午5:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:iterator value="#request.articleList">
    <a href="#" class="list-group-item" onclick="loadPreview('${id}')">
        <h4>${title}</h4>
    </a>
</s:iterator>