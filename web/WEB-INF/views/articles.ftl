<#import "template/mainTemp.ftl" as templates>
<#import "template/articleTemp.ftl" as articleTemp>
<#import "template/blockControls.ftl" as blockControls>
<@templates.body title="Article">
<div class="container">
    <div class="page-header">
        <h1>Software Association <small>at LingNan College</small></h1>
    </div>
    <@templates.mainNav activeOrder=1/>
    <div class="row">
        <div class="col-md-8">
            <div>
                <ul class="breadcrumb">
                    <li><a href="/article/">All</a></li>
                    <#if (current_topic??)><li class="active">${current_topic?cap_first}</li></#if>
                </ul>
            </div>
            <@articleTemp.mediaGroupListPageItem itemset=articles type="article" />
            <@templates.defaultPager total=articles.totalPages current=articles.number length=10 path="?page=" />
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
        <div class="col-md-4">
            <@blockControls.hotTopicPanel topicSet=topics showCreateButton=true/>
        </div>
    </div>
</div>
</@templates.body>