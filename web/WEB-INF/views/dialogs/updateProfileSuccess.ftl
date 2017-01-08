<#import "../template/mainTemp.ftl" as template>
<@template.body title="Profile update success">
    <@template.dialog mainTitle="Profile updated"
    subtitle="">
    <a class="btn btn-block btn-success" href="/user/self/profile">Back to My Profile</a>
    <a class="btn btn-block btn-default" href="/user/self">Back to User Center</a>
    </@template.dialog>
</@template.body>