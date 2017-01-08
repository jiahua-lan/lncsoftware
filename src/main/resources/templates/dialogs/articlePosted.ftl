<#import "../template/mainTemp.ftl" as template>
<@template.body title="Article Posted!">
    <@template.dialog mainTitle="Article Posted!"
    subtitle="Your article &lt;&lt;${article_title}&gt;&gt; was saved!">
    <a class="btn btn-block btn-success" href="/article/">Back to Article List</a>
    </@template.dialog>
</@template.body>