<#import "template/mainTemp.ftl" as template>
<@template.body title="Article">
<div class="container">
    <div class="page-header">
        <h1>Software Association <small>at LingNan College</small></h1>
    </div>
    <@template.mainNav activeOrder=1/>
    <div class="page-header">
        <h2>${article.title}</h2>
        <p>Author : ${author!""}, wrote at ${article.createDate!""}<#if modifiedDate??> , latest modified at ${modifiedDate!""} </#if></p>
        <#if (article.topics?? && article.topics?size > 0)>
            <p>Topics :
                <#list article.topics as topic>
                    <label class="label label-info">${topic.title}</label>
                </#list>
            </p>
        </#if>
    </div>
    <p>${content?html?replace("\n","<br>")}</p>
</div>
</@template.body>