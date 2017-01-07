<#import "../template/mainTemp.ftl" as template>
<@template.body title="Article Not Found">
    <@template.dialog mainTitle="Article Not Found"
    subtitle="The article you are looking for maybe deleted or not existed." >
    <a class="btn btn-block btn-primary" href="/article/">Back to Article List</a>
    <a class="btn btn-block btn-default" href="/">Back to Homepage</a>
    </@template.dialog>
</@template.body>