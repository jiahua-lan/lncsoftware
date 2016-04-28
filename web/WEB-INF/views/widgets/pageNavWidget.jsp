<%--
  Created by IntelliJ IDEA.
  User: catten
  Date: 16/4/28
  Time: 上午1:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="#request.pageList!=null">
    <div class="text-center">
        <nav>
            <ul class="pagination">
                <s:iterator value="#request.pageList" var="item">
                    <li id="nav-page-${item}"><a href="articles?page=${item}">${item}</a></li>
                </s:iterator>
            </ul>
        </nav>
        <script>document.getElementById("nav-page-${currentPage}").setAttribute("class","active")</script>
    </div>
</s:if>