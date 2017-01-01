<#import "template/mainTemp.ftl" as templates>
<#import "template/articleTemp.ftl" as articleTemp>
<@templates.body title="Bulletins">
<div class="container">
    <div class="page-header">
        <h1>Software Association <small>at LingNan College</small></h1>
    </div>
    <@templates.mainNav activeOrder=2/>
    <div class="row">
        <div class="col-md-8">
            <@articleTemp.mediaGroupListPageItem itemset=bulletins type="bulletin" />
        </div>
        <div class="col-md-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    Actions
                </div>
                <div class="list-group">
                    <a class="list-group-item" href="/bulletin/write">New Bulletin</a>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    All Tags
                </div>
                <div class="list-group">
                    <#if (tags?? && tags?size > 0)>
                    <#list tags as t >
                    <a class="list-group-item" href="/bulletin/?type=${t}">${t?cap_first}</a>
                    </#list>
                    <#else>
                        <a class="list-group-item disabled" href="#">No Tags ...</a>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</@templates.body>