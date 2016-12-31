<#import "template/temp.ftl" as templates>
<@templates.body title="Article">
<div class="container">
    <div class="page-header">
        <h1>Software Association <small>at LingNan College</small></h1>
    </div>
    <@templates.mainNav activeOrder=1/>
    <div class="row">
        <div class="col-md-8">
            <div class="list-group">
                <#if (articles?? && articles.size > 0) >
                    <#list articles.content as a>
                        <a class="list-group-item" href="#">
                            <div class="media">
                                <div class="media-left" href="#">
                                    <img src="holder.js/64x64/gray">
                                </div>
                                <div class="media-body">
                                    <h4 class="media-heading">${a.title}</h4>
                                    <p>${a.subtitle}</p>
                                </div>
                            </div>
                        </a>
                    </#list>
                <#else>
                    <a class="list-group-item disabled">No Content here...</a>
                </#if>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    Actions
                </div>
                <div class="list-group">
                    <a class="list-group-item" href="/article/write">New Article</a>
                </div>
            </div>
        </div>
    </div>
</div>
</@templates.body>