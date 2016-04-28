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
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-danger">
                <div class="panel-body">
                    <div class="page-header">
                        Error
                    </div>
                    ${message}
                </div>
            </div>
        </div>
    </div>
</body>
</html>
