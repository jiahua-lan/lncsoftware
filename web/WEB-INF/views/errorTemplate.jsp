<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/28
  Time: 下午2:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error | LNCSA</title>
    <jsp:include page="temps/includeCSS.jsp"/>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <jsp:include page="temps/homepageHead.jsp"/>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-danger">
                        <div class="panel-body">
                            <div class="page-header">
                                <h1 class="text-center">Error</h1>
                            </div>
                            ${message}
                        </div>
                        <div class="panel-footer">
                            <a class="btn btn-default" href="<s:url namespace="/" action="index"/>">Back to Homepage</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
