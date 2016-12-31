<#import "../template/mainTemp.ftl" as template>
<@template.body title="Login Failed">
    <div class="container">
        <div class="page-header">
            <h1 align="center">Login Failed</h1>
            <p align="center">Please check your name or password</p>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <a class="btn btn-block btn-primary" href="/user/login">Back to Login</a>
                <a class="btn btn-block btn-default" href="/">Back to Homepage</a>
            </div>
        </div>
    </div>
</@template.body>