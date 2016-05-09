<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/27
  Time: 下午5:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>应用｜岭南软件园协会</title>
    <jsp:include page="temps/includeCSS.jsp"/>
    <jsp:include page="temps/includeScript.jsp"/>
    <script src="js/jquery-1.11.3.min.js"></script>
</head>
<body>
<div class="container">
    <div class="page-header">
        <jsp:include page="temps/homepageHead.jsp"/>
        <script>document.getElementById("page-head-apps").setAttribute("class","active")</script>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-8">
                <div class="list-group">
                    <s:if test="#request.appList != null">
                        <s:iterator value="#request.appList" var="item">
                            <a class="list-group-item ${disable}" href="${link}">
                                <div class="media">
                                    <div class="media-left">
                                        <img class="media-object"
                                        <s:if test="imageCode==''"> data-src='holder.js/60x60'</s:if>
                                            <s:else> src='${imageCode}' width='60pt' height='60pt'</s:else>>
                                    </div>
                                    <div class="media-body">
                                        <h4 class="media-heading">
                                            <s:if test="status=='working'">
                                                <span class="label label-success">工作中</span>
                                            </s:if>
                                            <s:elseif test="status=='repairing'">
                                                <span class="label label-warning">维护期</span>
                                            </s:elseif>
                                            <s:elseif test="status=='disable'">
                                                <span class="label label-default">已失效</span>
                                            </s:elseif>
                                            ${title}
                                        </h4>
                                        <p>${description}</p>
                                    </div>
                                </div>
                            </a>
                        </s:iterator>
                    </s:if>
                    <s:if test="#request.appList.size<=0">
                        <div class="list-group-item disabled">
                            暂时没有应用……
                        </div>
                    </s:if>
                </div>
            </div>
            <div class="col-sm-4">
                <jsp:include page="widgets/userWidget.jsp"/>
                <s:if test="#request.bulletinBoard!=null">
                    <script>$(document).ready(function(){ $("#bulletinBoard").addClass("animated"); });</script>
                    <div class="panel panel-primary bounceIn" id="bulletinBoard">
                        <div class="panel-heading">
                            小喇叭
                        </div>
                        <div class="panel-body">
                            ${bulletinBoard.context}
                        </div>
                        <div class="panel-footer">
                            发布于 <s:date name="#request.bulletinBoard.date" format="yyyy年MM月DD日 hh时mm分"/>
                        </div>
                    </div>
                </s:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
