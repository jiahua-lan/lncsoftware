<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/28
  Time: 上午11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel panel-default">
    <div class="panel-heading">
        Change password
    </div>
    <div class="panel-body">
        <form action="updatePassword.action" method="post">
            <div class="container-fluid">
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="New password">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="confirmedPassword"
                           placeholder="Input your new password again">
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-block btn-default" value="Update">
                </div>
            </div>
        </form>
    </div>
</div>