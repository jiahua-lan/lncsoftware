<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/27
  Time: 下午7:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>Administration | LNCSA</h1>
<p>我们相信您已经从管理员处了解到一些基本注意事项，它们大概可以总结为以下三项：
    <ol>
        <li>尊重别人的隐私。</li>
        <li>输入前要先考虑（后果和风险）。</li>
        <li>权力越大，责任越大。</li>
    </ol>
</p>
<nav>
    <ul class="nav nav-pills">
        <li id="admin-head-article"><a href="<s:url namespace="/admin" action="article"/>">Article</a></li>
        <li id="admin-head-user"><a href="#">User</a></li>
        <li id="admin-head-apps"><a href="#">App</a></li>
        <li id="admin-head-contact"><a href="#">Contact</a></li>
        <li id="admin-head-bulletin"><a href="#">Bulletins</a></li>
    </ul>
</nav>
