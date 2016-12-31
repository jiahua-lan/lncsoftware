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
        <div class="media-right">
            <p>${item.content?html}</p>
        </div>
        <div align="right"><small>posted at ${item.date}, tagged as</small> <span class="label label-info">${item.type}</span></div>
    </div>
</a>
</#macro>

<#macro mediaGroupListItem itemset type>
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