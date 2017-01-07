<#import "../template/mainTemp.ftl" as template>
<@template.body title="Logout Success">
    <@template.dialog mainTitle="Logout Success"
    subtitle="">
    <a class="btn btn-block btn-primary" href="/">Back to Homepage</a>
    <a class="btn btn-block btn-default" href="/user/login">Back to Login</a>
    </@template.dialog>
</@template.body>