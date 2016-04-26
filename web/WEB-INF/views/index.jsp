<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/24
  Time: 下午8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Lingnan Software(Center) Association</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<script src="js/tools.js"></script>
<div class="container">
    <div class="page-header">
        <h1>Lingnan Software(Center) Association</h1>
        <nav>
            <ul class="nav nav-pills">
                <li class="active"><a href="index">Home</a></li>
                <li><a href="articles">Article</a></li>
                <li><a href="javascript:">Apps</a></li>
                <li><a href="javascript:">Contact</a></li>
            </ul>
        </nav>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-8 col-sm-8">

                <s:if test="#request.topBoard.imageLink!=null">
                    <div class="panel panel-primary">
                        <div class="panel-body">
                            <div class="media">

                                <s:if test="#request.topBoard.imageLink!=null">
                                    <div class="media-left">
                                        <div class="media-object">
                                            <a href="${topBoard.link}"><img src="${topBoard.imageLink}"
                                                                            onload="DrawImage(this,128,128)"></a>
                                        </div>
                                    </div>
                                </s:if>

                                <div class="media-body">
                                    <p id="topBoard">${topBoard.context}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </s:if>

                <div class="list-group">

                    <s:iterator value="#request.articleList">
                        <a class="list-group-item" href="javascript:">
                            <h4>${title}</h4>
                            <p>${previewSentences}</p>
                            <small>By ${authorName} at ${createDate}</small>
                        </a>
                    </s:iterator>

                    <s:if test="#request.articleList==null">
                        <div class="list-group-item disabled">No Article.</div>
                    </s:if>

                    <s:elseif test="articleList.size>=10">
                        <a class="list-group-item" href="javascript:">More...</a>
                    </s:elseif>
                </div>
            </div>
            <div class="col-md-4 col-sm-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        User
                    </div>
                    <div class="panel-body">
                        <s:if test="#session.passport!=null">
                            <div class="text-center">
                                Welcome, ${passport.name}
                            </div>
                            <div class="text-center">
                                <a class="btn btn-warning">Logout</a>
                            </div>
                        </s:if>
                        <s:else>
                            <div class="text-center" style="margin: 3pt 0pt">
                                You did not login yet
                            </div>
                            <div class="text-center">
                                <a class="btn btn-success">Login</a>
                                <a class="btn btn-default">Sign up</a>
                            </div>
                        </s:else>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
