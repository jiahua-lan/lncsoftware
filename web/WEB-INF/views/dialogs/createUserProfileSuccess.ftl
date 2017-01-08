<#import "../template/mainTemp.ftl" as template>
<@template.body title="Create User Profile Success">
    <@template.dialog mainTitle="Create User Profile Success"
    subtitle="Your user profile was successfuly created!" >
    <a class="btn btn-primary btn-block" href="/user/self">Back to User Center</a>
    <a class="btn btn-default btn-block" href="/user/self/profile">Go to my profile.</a>
    </@template.dialog>
</@template.body>