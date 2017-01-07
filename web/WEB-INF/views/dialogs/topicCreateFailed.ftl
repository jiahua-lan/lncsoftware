<#import "../template/mainTemp.ftl" as template>
<@template.body title="Topic Create Failed">
    <@template.dialog mainTitle="Topic Create Failed"
    subtitle="Topic cannot duplicate.">
    <a class="btn btn-block btn-success" href="/article/">Back to Article List</a>
    </@template.dialog>
</@template.body>