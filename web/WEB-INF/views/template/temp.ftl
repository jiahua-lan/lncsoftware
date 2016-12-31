<#macro body title>
<html>
<head>
    <title>LNCSA - ${title?html}</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="//cdn.bootcss.com/animate.css/3.5.2/animate.min.css">
    <style>body{  margin-top: 30px;  }</style>
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
            <li <#if activeOrder==2>class="active"</#if>><a href="#">About Us</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/user/login">Login</a></li>
            <li><a href="/user/register">Register</a></li>
        </ul>
    </div>
</nav>
</#macro>