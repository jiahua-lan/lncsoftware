<#import "../template/mainTemp.ftl" as template>
<@template.body title="Welcome - ${login_username}">
    <@template.dialog mainTitle="Welcome ${login_username}"
    subtitle="">
    <a class="btn btn-block btn-success" href="/">Back to Homepage</a>
    </@template.dialog>
</@template.body>