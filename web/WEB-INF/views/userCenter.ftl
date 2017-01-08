<#import "template/mainTemp.ftl" as templates>
<#import "template/articleTemp.ftl" as articleTemp>
<#import "template/userTemp.ftl" as userTemp>
<@templates.body title="User Center">
<div class="container">
    <div class="page-header">
        <h1>Software Association <small>at LingNan College</small></h1>
    </div>
    <@templates.mainNav activeOrder=4 />
    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-primary">
                <#if (user_latest_articles?? && user_latest_articles?size > 0)>
                    <div class="panel-body">
                        <span class="lnc_title_panel">Recent Articles</span>
                        <div class="btn-group pull-right">
                            <a class="btn btn-default btn-sm">Create</a>
                            <a class="btn btn-default btn-sm">See all &gt;&gt;</a>
                        </div>
                    </div>
                    <@articleTemp.mediaGroupListItem itemset=user_latest_articles type="article"/>
                <#else >
                    <div class="panel-body" align="center">
                        <h2>You have no article yet..</h2>
                        <hr>
                        <a href="/article/write" class="btn btn-primary">Create an article</a>
                    </div>
                </#if>
            </div>
        </div>
        <div class="col-md-4">
            <@userTemp.rightPanel userInfo=user_info!"" userModel=user_model active=0/>
        </div>
    </div>
</div>
</@templates.body>