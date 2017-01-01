<#import "template/mainTemp.ftl" as templates>
<#import "template/articleTemp.ftl" as articleTemp>
<@templates.body title="Index">
    <div class="container">
        <div class="page-header">
            <h1>Software Association <small>at LingNan College</small></h1>
        </div>
        <@templates.mainNav activeOrder=0/>
        <div class="row">
            <div class="col-md-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Recent bulletins
                    </div>
                    <div class="list-group">
                        <a class="list-group-item" href="#">Something...</a>
                        <a class="list-group-item" href="#">Something...</a>
                        <a class="list-group-item" href="#">Something...</a>
                        <a class="list-group-item" href="#">Something...</a>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <#if bulletin?? >
                    <div class="panel panel-primary">
                        <div class="panel-body">
                            <div class="container-fluid">
                                <span style="font-size: 20pt;">Latest Bulletin</span>
                                <a class="btn btn-success pull-right" href="${bulletin.link!""}">Detail &gt;&gt;</a>
                                <hr>
                                <div class="media">
                                    <#if bulletin.imageLink?? && bulletin.imageLink != "">
                                        <div class="media-left">
                                            <img src="${bulletin.imageLink}" width="64pt" height="64pt" >
                                        </div>
                                    </#if>
                                    <div class="media-right">
                                        <p>${bulletin.content}</p>
                                    </div>
                                    <p align="right">
                                        at ${bulletin.date} tagged as <span class="label label-info">${bulletin.type}</span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </#if>
            </div>
        </div>
        <div class="row">
            <div class="col-md-8">
                <div class="panel panel-success">
                    <div class="panel-body">
                        <span style="font-size: 20pt;">Recent Article</span>
                        <a class="btn btn-success pull-right" href="/article/">More &gt;&gt;</a>
                    </div>
                    <@articleTemp.mediaGroupListItem itemset=articles type="article"/>
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-success">
                    <div class="panel-heading">Topics</div>
                    <div class="list-group">
                        <a class="list-group-item">Some Topic....</a>
                        <a class="list-group-item">Some Topic....</a>
                        <a class="list-group-item">Some Topic....</a>
                        <a class="list-group-item">Some Topic....</a>
                        <a class="list-group-item">Some Topic....</a>
                        <a class="list-group-item">Some Topic....</a>
                        <a class="list-group-item">Some Topic....</a>
                    </div>
                    <div class="panel-footer container-fluid">
                        <a href="#" class="btn btn-success pull-right">More topics &gt;&gt;</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@templates.body>