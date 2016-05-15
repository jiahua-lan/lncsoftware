<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/5/11
  Time: 下午5:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="panel panel-default">
    <div class="panel-body">
        <div class="page-header">
            <h2>${article.title}
                <small style="display: inline-block">By ${article.authorName}
                    at <s:date name="#request.article.createDate" format="yyyy-MM-dd hh:mm"/> </small>
            </h2>
            <div>标签 :
                <s:iterator value="#request.article.tags" id="tagName">
                    <span class="label label-info">${tagName}</span>
                </s:iterator>
            </div>
        </div>
        <div class="container-fluid">
            ${article.previewSentences}
        </div>
    </div>
    <div class="panel-footer">
        <a class="btn btn-danger" href="javascript:">Delete</a>
    </div>
</div>