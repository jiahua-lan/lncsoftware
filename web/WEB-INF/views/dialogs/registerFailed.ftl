<#import "../template/mainTemp.ftl" as template>
<@template.body title="Register Failed">
<div class="container">
    <div class="page-header">
        <h1 align="center">Register Failed</h1>
        <p align="center">Please check if your information match pattern.<br>
            Or the username you pick was registered.</p>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <a href="/user/register" class="btn btn-primary btn-block">Back to Register</a>
            <a href="/" class="btn btn-default btn-block">Back to Homepage</a>
        </div>
    </div>
</div>
</@template.body>