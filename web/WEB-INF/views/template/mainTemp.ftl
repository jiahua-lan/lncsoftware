<#macro body title>
<html>
<head>
    <title>LNCSA - ${title?html}</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/animate.css/3.5.2/animate.min.css">
    <style>body {
        margin-top: 30px;
    }</style>
</head>
<body>
    <#nested>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="https://cdn.rawgit.com/imsky/holder/master/holder.js"></script>
</body>
</html>
</#macro>

<#macro mainNav activeOrder>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li <#if activeOrder==0>class="active"</#if>><a href="/">Home</a></li>
            <li <#if activeOrder==1>class="active"</#if>><a href="/article/">Articles</a></li>
            <li <#if activeOrder==2>class="active"</#if>><a href="/bulletin/">Bulletins</a></li>
            <li <#if activeOrder==3>class="active"</#if>><a href="/about">About Us</a></li>
        </ul>
        <#if (Session.session_userid??) >
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user/${Session.session_userid}">Welcome, ${Session.session_username?html}. </a></li>
                <li><a href="/user/logout">Logout</a></li>
            </ul>
        <#else>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user/login">Login</a></li>
                <li><a href="/user/register">Register</a></li>
            </ul>
        </#if>
    </div>
</nav>
</#macro>

<#macro defaultPager total current shownPages path>
<div class="container-fluid">
    <nav style="text-align: center">
        <ul class="pagination">
            <#if current == 0>
                <li class="disabled"><a href="#">&laquo;</a></li>
            <#else>
                <li><a href="${path + (current - 1)}">&laquo;</a></li>
            </#if>
            <#if (total <= shownPages)>
                <#list 0..total-1 as i>
                    <li <#if i == current>class="active" </#if>><a href=${path + i}>${i+1}</a></li>
                </#list>
            <#elseif (total > shownPages)>
                <#if (current <= (shownPages/2))>
                    <#list 0..(shownPages-1) as i>
                        <li <#if i == current>class="active" </#if>><a href=${path + i}>${i+1}</a></li>
                    </#list>
                <#elseif (current >= (shownPages/2) && current <= (total-shownPages/2)) >
                    <#list current-(shownPages/2-1)..current+(shownPages/2) as i>
                        <li <#if i == current>class="active" </#if>><a href=${path + i}>${i+1}</a></li>
                    </#list>
                <#elseif (current + (shownPages/2) >= total - 1)>
                    <#list (total-shownPages)..(total-1) as i>
                        <li <#if i == current>class="active" </#if>><a href=${path + i}>${i+1}</a></li>
                    </#list>
                </#if>
            </#if>
            <#if current == total-1>
                <li class="disabled"><a href="#">&raquo;</a></li>
            <#else >
                <li><a href="${path + (current + 1)}">&raquo;</a></li>
            </#if>
        </ul>
    </nav>
</div>
</#macro>