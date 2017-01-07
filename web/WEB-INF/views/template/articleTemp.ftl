<#macro articleListItem item imglink>
<a class="list-group-item" href="/article/${item.id}">
    <div class="media">
        <div class="media-left">
            <img src="${imglink!""}">
        </div>
        <div class="media-body">
            <h4 class="media-heading">${item.title!""}</h4>
            <p>${item.subtitle!""}</p>
        </div>
        <div align="right"><small>at ${item.createDate}</small></div>
    </div>
</a>
</#macro>

<#macro bulletinListItem item>
<a class="list-group-item" href="${item.link!"#"}">
    <div class="media">
        <#if (item.imageLink?? && item.imageLink != "")>
        <div class="media-left">
            <img src="${item.imageLink}" width="64pt" height="64pt">
        </div>
        </#if>
        <div class="media-body">
            <p>${item.content?html}</p>
        </div>
        <div align="right"><small>posted at ${item.createDate}, tagged as</small> <span class="label label-info">${item.type}</span></div>
    </div>
</a>
</#macro>

<#macro mediaGroupListPageItem itemset type>
<div class="list-group">
    <#if ( itemset?? && (itemset.content?size > 0) ) >
        <#switch type>
            <#case "article">
                <#list itemset.content as i>
                    <@articleListItem item=i imglink="holder.js/64x64/gray" />
                </#list>
                <#break>
            <#case "bulletin">
                <#list itemset.content as i>
                    <@bulletinListItem item=i />
                </#list>
                <#break>
        </#switch>
    <#else>
        <a class="list-group-item disabled" href="#">No Content here...</a>
    </#if>
</div>
</#macro>

<#macro mediaGroupListItem itemset type>
<div class="list-group">
    <#if ( itemset?? && (itemset?size > 0) ) >
        <#switch type>
            <#case "article">
                <#list itemset as i>
                    <@articleListItem item=i imglink="holder.js/64x64/gray" />
                </#list>
                <#break>
            <#case "bulletin">
                <#list itemset as i>
                    <@bulletinListItem item=i />
                </#list>
                <#break>
        </#switch>
    <#else>
        <a class="list-group-item disabled" href="#">No Content here...</a>
    </#if>
</div>
</#macro>

<#macro bulletinBoardBody bulletin title>
<div class="panel-body tab-content">
    <span style="font-size: 20pt;">${title}</span>
    <#if (bulletin.link?? && bulletin.link != "")>
        <a class="btn btn-success pull-right" href="${bulletin.link!""}">Detail &gt;&gt;</a>
    </#if>
    <hr>
    <div class="media">
        <#if bulletin.imageLink?? && bulletin.imageLink != "">
            <div class="media-left">
                <img src="${bulletin.imageLink}" onload="DrawImage(this,64,64)" >
            </div>
        </#if>
        <div class="media-right">
            <p>${bulletin.content}</p>
        </div>
        <p align="right">
            at ${bulletin.createDate} tagged as <span class="label label-info">${bulletin.type}</span></p>
    </div>
</div>
</#macro>