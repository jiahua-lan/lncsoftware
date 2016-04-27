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
    <title>Applications</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<script src="js/holder.min.js"></script>
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
                            No Applications....
                        </div>
                    </s:if>
                </div>
            </div>
            <div class="col-sm-4">
                <jsp:include page="widgets/userWidget.jsp"/>
                <s:if test="#request.bulletinBoard!=null">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Bulletin Board
                        </div>
                        <div class="panel-body">
                            ${bulletinBoard.context}
                        </div>
                        <div class="panel-footer">
                            <s:date name="#request.bulletinBoard.date" format="yyyy-MM-DD hh:mm"/>
                        </div>
                    </div>
                </s:if>
            </div>
        </div>
    </div>
</div>
<s:debug></s:debug>
</body>
</html>
