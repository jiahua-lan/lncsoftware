<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/27
  Time: 下午2:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="panel <s:if test="#session.passport==null">panel-default</s:if><s:else>panel-success</s:else>">
    <div class="panel-heading">
        用户
    </div>
    <s:if test="#session.passport!=null">
        <div class="panel-body">
            <div>欢迎， <b>${passport.name}！</b></div>
            <s:iterator value="#session.passport.rights" var="item">
                <span class="label label-success">${item}</span>
            </s:iterator>
        </div>
        <div class="list-group">
            <a class="list-group-item" href="self" id="widget-user-center">
                <span class="glyphicon glyphicon-user"> </span>
                用户中心
            </a>
            <s:iterator value="#session.passport.rights" var="item">
                <s:if test="#item=='article'">
                    <a class="list-group-item" href="writing">
                        <span class="glyphicon glyphicon-edit"> </span>
                        写文章
                    </a>
                </s:if>
                <s:if test="#item=='admin'">
                    <a class="list-group-item" href="admin/article">
                        <span class="glyphicon glyphicon-wrench"> </span>
                        后台管理
                    </a>
                </s:if>
            </s:iterator>
        </div>
        <div class="panel-footer">
            <a class="btn btn-warning btn-sm" href="logout.action">
                <span class="glyphicon glyphicon-log-out"> </span>
                登出
            </a>
        </div>
    </s:if>
    <s:else>
        <div class="panel-body">
            <div class="text-center" style="margin: 3pt 0pt">
                欢迎您，游客！
            </div>
        </div>
        <div class="panel-footer">
            <a class="btn btn-success btn-sm" href="login.action">
                <span class="glyphicon glyphicon-log-in"> </span>
                登陆
            </a>
            <a class="btn btn-default btn-sm" href="registry">
                <span class="glyphicon glyphicon-registration-mark"> </span>
                注册
            </a>
        </div>
    </s:else>
</div>
