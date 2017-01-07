<#import "../template/mainTemp.ftl" as template>
<@template.body title="Login Failed">
    <@template.dialog mainTitle="Login Failed"
    subtitle="Please check your name or password">
    <a class="btn btn-block btn-primary" href="/user/login">Back to Login</a>
    <a class="btn btn-block btn-default" href="/">Back to Homepage</a>
    </@template.dialog>
</@template.body>