<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/27
  Time: 下午7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="list-group">
    <s:if test="#request.contactList!=null">
        <s:iterator value="#request.contactList" var="item">
            <a class="list-group-item" href="${link}">
                <div class="media">
                    <div class="media-left">
                        <img class="media-object" <s:if test="#item.imageLink==''">
                             data-src='holder.js/60x60'
                        </s:if> <s:else>src="${imageLink}"</s:else> width="60pt" height="60pt">
                    </div>
                    <div class="media-body">
                        <p>${context}</p>
                    </div>
                </div>
            </a>
        </s:iterator>
    </s:if>
    <s:else>
        <a class="list-group-item disabled" href="#">噢，并没有什么东西……</a>
    </s:else>
</div>
