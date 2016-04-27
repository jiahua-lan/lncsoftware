<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/27
  Time: 下午8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="list-group">
    <s:if test="#request.friendLinks!=null">
        <s:iterator value="#request.friendLinks">
            <a class="list-group-item" href="${link}">${context}</a>
        </s:iterator>
    </s:if>
    <s:else>
        <a class="list-group-item disabled" href="#">噢，这里没东西……</a>
    </s:else>
</div>