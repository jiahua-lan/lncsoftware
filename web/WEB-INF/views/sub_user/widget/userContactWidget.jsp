<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/28
  Time: 上午11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="panel panel-default">
    <div class="panel-heading">
        Contact Info
    </div>
    <div class="panel-body">
        <form action="updateContact.action">
            <div class="container-fluid">
                <div class="input-group">
                    <span class="input-group-addon">QQ or E-mail</span>
                    <input class="form-control" name="contactInfo" type="text"
                           value="${userInfo.contactInfo}">
                                <span class="input-group-btn"><input type="submit" class="btn btn-default"
                                                                     value="Update"></span>
                </div>
            </div>
        </form>
    </div>
</div>
