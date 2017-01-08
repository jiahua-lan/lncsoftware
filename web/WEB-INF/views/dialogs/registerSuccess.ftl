<#import "../template/mainTemp.ftl" as template>
<@template.body title="Register Successful">
    <@template.dialog mainTitle="Register Successful"
    subtitle="Please use this name for future login : ${reg_username}">
    <a href="/" class="btn btn-success btn-block">Back to Homepage</a>
    </@template.dialog>
</@template.body>