<#import "template/mainTemp.ftl" as templates>
<#import "template/articleTemp.ftl" as articleTemp>
<@templates.body title="Article">
<div class="container">
    <div class="page-header">
        <h1>Software Association <small>at LingNan College</small></h1>
    </div>
    <@templates.mainNav activeOrder=1/>
    <div class="row">
        <div class="col-md-8">
            <@articleTemp.mediaGroupListItem itemset=articles type="article" />
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