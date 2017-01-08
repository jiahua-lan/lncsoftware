<#macro bulletinCarousel bulletinSet>
<div class="row carousel slide" id="carousel_banner" style="height: 240px">
    <div class="col-md-4">
        <div class="panel panel-success">
            <div class="panel-heading">
                Recent Bulletins
            </div>
            <div class="list-group" id="index_bulletin_list">
                <#list bulletinSet as b>
                    <a class="list-group-item" data-target="#carousel_banner" data-slide-to="${b_index}" href="#index_bulletin_item_${b.id}">
                        <label class="label label-info pull-right">${b.type}</label>
                        <#if (b.content?length > 25)>${b.content?substring(0,25)?html}...
                        <#else >${b.content?html}
                        </#if>
                    </a>
                </#list>
            </div>
        </div>
    </div>
    <div class="col-md-8" style="height: inherit;">
        <div class="carousel-inner" style="height: inherit;">
            <#list bulletinSet as b>
                <div class="item ${(b_index == 0)?string("active","")}" style="height: inherit;">
                    <div class="panel panel-primary" style="margin: 0 3pt">
                        <div class="panel-body">
                            <div class="media">
                                <#if b.imageLink?? && b.imageLink != "">
                                    <div class="media-left">
                                        <img src="${b.imageLink}" onload="DrawImage(this,64,64)" >
                                    </div>
                                </#if>
                                <div class="media-right">
                                    <p>${b.content}</p>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer container-fluid" style="align-self: baseline">
                            Posted at ${b.createDate} tagged as <span class="label label-info">${b.type}</span>
                            <#if (b.link?? && b.link != "")>
                                <a class="btn btn-success btn-sm pull-right" href="${b.link!""}">Detail &gt;&gt;</a>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>
</#macro>

<#macro hotTopicPanel topicSet showCreateButton>
<div class="panel panel-success">
    <div class="panel-heading">Hot Topics</div>
    <div class="list-group">
        <#if (topics?? && topicSet?size > 0)>
            <#list topicSet as topic>
                <a class="list-group-item" href="/article/topic/${topic.id}">${topic.title}</a>
            </#list>
        <#else >
            <a class="list-group-item disabled" href="#">Oh... No topics..</a>
        </#if>
    </div>
    <div class="panel-footer container-fluid">
        <#if showCreateButton ><a href="/topic/create" class="btn btn-sm btn-primary">Create...</a></#if>
        <#if ( topicSet?? && topicSet?size > 0)>
            <a href="#" class="btn btn-success btn-sm pull-right">More topics &gt;&gt;</a>
        </#if>
    </div>
</div>
</#macro>