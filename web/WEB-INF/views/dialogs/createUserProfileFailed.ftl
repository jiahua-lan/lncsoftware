<#import "../template/mainTemp.ftl" as template>
<@template.body title="Create User Profile Failed">
    <@template.dialog mainTitle="You already has your own profile."
    subtitle="" >
    <a class="btn btn-primary btn-block" href="/user/self">Back to User Center</a>
    <a class="btn btn-default btn-block" href="/user/self/profile">Go to my profile.</a>
    </@template.dialog>
</@template.body>