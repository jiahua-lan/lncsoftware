<#macro rightPanel userInfo userModel active>
<div class="panel panel-success">
<div class="panel-body">
    <#if (Session.session_user.headPic?? && Session.session_user.headPic != "")>
        <div class="media">
            <div class="media-left">
                <img src="${Session.session_user.headPic}" onload="DrawImage(this,64,64)">
            </div>
            <div class="media-body">
                <div class="lnc_title_panel">
                    <#if (Session.session_user.nickname?? && Session.session_user.nickname != "") >${Session.session_user.nickname}
                            <#else >${Session.session_user.username}
                    </#if>
                </div>
                <p>
                    <label class="label label-success">user</label>
                    <label class="label label-success">admin</label>
                </p>
            </div>
        </div>
    <#else>
        <div class="lnc_title_panel">
            <#if (Session.session_user.nickname?? && Session.session_user.nickname != "") >${Session.session_user.nickname}
                            <#else >${Session.session_user.username}
            </#if>
        </div>
        <div>
            <label class="label label-success">user</label>
            <label class="label label-success">admin</label>
        </div>
    </#if>

</div>
    <div class="list-group">
        <a href="/user/self" class="list-group-item <#if (active==0)>active</#if>">User Center</a>
        <a href="/user/self/profile" class="list-group-item <#if (active==1)>active</#if>">My Profile</a>
    </div>
</div>
</#macro>