<#import "template/mainTemp.ftl" as templates>
<#import "template/articleTemp.ftl" as articleTemp>
<#import "template/blockControls.ftl" as blockControls>
<@templates.body title="Index">
    <div class="container">
        <div class="page-header">
            <h1>Software Association <small>at LingNan College</small></h1>
        </div>
        <@templates.mainNav activeOrder=0 />
        <#if (bulletins?? && bulletins?size > 0)>
            <@blockControls.bulletinCarousel bulletinSet=bulletins />
        </#if>

        <div class="row">
            <div class="col-md-8">
                <div class="panel panel-success">
                    <div class="panel-body clearfix">
                        <span style="font-size: 17pt;">Recent Article</span>
                        <a class="btn btn-success btn-sm pull-right" href="/article/">More &gt;&gt;</a>
                    </div>
                    <@articleTemp.mediaGroupListItem itemset=articles type="article"/>
                </div>
            </div>
            <div class="col-md-4">
                <@blockControls.hotTopicPanel topicSet=topics showCreateButton=false />
            </div>
        </div>
    </div>
</@templates.body>
<script>$("#carousel_banner").carousel(0);</script>