<#import "../template/mainTemp.ftl" as template>
<@template.body title="Topic Created!">
    <@template.dialog mainTitle="Topic Created!"
    subtitle="The topic &quot;${topic_title}&quot; created!">
    <a class="btn btn-block btn-success" href="/article/">Back to Article List</a>
    </@template.dialog>
</@template.body>