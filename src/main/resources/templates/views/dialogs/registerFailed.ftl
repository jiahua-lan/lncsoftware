<#import "../template/mainTemp.ftl" as template>
<@template.dialog mainTitle="Register Failed"
subtitle="Please check if your information match pattern.<br>
            Or the username you pick was registered.">
<a href="/user/register" class="btn btn-primary btn-block">Back to Register</a>
<a href="/" class="btn btn-default btn-block">Back to Homepage</a>
</@template.dialog>