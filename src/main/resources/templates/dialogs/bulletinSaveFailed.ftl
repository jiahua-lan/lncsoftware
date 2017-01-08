<#import "../template/mainTemp.ftl" as template>
<@template.body title="Bulletin Save Failed">
    <@template.dialog mainTitle="Bulletin Save Failed"
    subtitle="Please check if your information match pattern.">
    <a href="/bulletin/write" class="btn btn-primary btn-block">Write again</a>
    <a href="/bulletin/" class="btn btn-default btn-block">Back to List</a>
    </@template.dialog>
</@template.body>