<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/27
  Time: 下午2:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="panel panel-default">
    <div class="panel-heading">
        User
    </div>
    <s:if test="#session.passport!=null">
        <div class="panel-body">
            <div>Welcome <b>${passport.name}</b></div>
            <s:iterator value="#session.passport.rights" var="item">
                <span class="label label-success">${item}</span>
            </s:iterator>
        </div>
        <div class="list-group">
            <a class="list-group-item" href="javascript:">User Center</a>
            <s:iterator value="#session.passport.rights" var="item">
                <s:if test="#item=='article'">
                    <a class="list-group-item" href="javascript:">Write Article</a>
                </s:if>
                <s:if test="#item=='admin'">
                    <a class="list-group-item" href="javascript:">Administrator</a>
                </s:if>
            </s:iterator>
        </div>
        <div class="panel-footer">
            <a class="btn btn-warning btn-sm" href="logout.action">Logout</a>
        </div>
    </s:if>
    <s:else>
        <div class="panel-body">
            <div class="text-center" style="margin: 3pt 0pt">
                Welcome, Guest!
            </div>
        </div>
        <div class="panel-footer">
            <a class="btn btn-success btn-sm" href="login.action">Login</a>
            <a class="btn btn-default btn-sm">Sign up</a>
        </div>
    </s:else>
</div>
