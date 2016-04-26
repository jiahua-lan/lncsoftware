<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/25
  Time: 下午8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Lingnan Software(Center) Association</h1>
        <nav>
            <ul class="nav nav-pills">
                <li><a href="index">Home</a></li>
                <li><a href="articles">Article</a></li>
                <li><a href="javascript:">Apps</a></li>
                <li><a href="javascript:">Contact</a></li>
            </ul>
        </nav>
    </div>
    <div style="margin-top: 10pt">
        <div class="row">
            <div class="col-md-6 col-md-offset-3">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="page-header text-center">
                            <h1>Login</h1>
                        </div>
                        <form class="form-horizontal" method="post">
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label class="control-label">Username</label>
                                </div>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-2">
                                    <label class="control-label">Password</label>
                                </div>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control">
                                </div>
                            </div>
                            <div class="form-group text-center">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <input class="btn btn-success btn-block" type="submit" value="Login">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
