<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/27
  Time: 下午4:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="#request.topBoard.imageLink!=null">
    <div class="panel panel-primary">
        <div class="panel-body">
            <div class="media">
                <s:if test="#request.topBoard.imageLink!=null">
                    <div class="media-left">
                        <div class="media-object">
                            <a href="${topBoard.link}">
                                <img src="${topBoard.imageLink}" onload="DrawImage(this,128,128)">
                            </a>
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
