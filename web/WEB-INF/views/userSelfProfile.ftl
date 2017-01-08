<#import "template/mainTemp.ftl" as templates>
<#import "template/articleTemp.ftl" as articleTemp>
<#import "template/userTemp.ftl" as userTemp>
<@templates.body title="Profile">
<div class="container">
    <div class="page-header">
        <h1>Software Association <small>at LingNan College</small></h1>
    </div>
    <@templates.mainNav activeOrder=4 />
    <div class="row">
        <div class="col-md-8">
            <#if user_info??>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Base Profile</h4>
                    </div>
                    <div class="panel-body">
                        <div class="container-fluid">
                            <form method="post" action="/user/self/profile">
                                <div class="form-group">
                                    <label for="p_nickname">Nickname</label>
                                    <input class="form-control" type="text" name="nickname" value="${user_info.nickname!""}" id="p_nickname">
                                </div>
                                <div class="form-group">
                                    <label>Gender</label>
                                    <div>
                                        <div class="btn-group" data-toggle="buttons">
                                            <label class="btn btn-default ${((user_info.gender!"") == "male")?string("active","")}">
                                                <input type="radio" name="gender" ${((user_info.gender!"") == "male")?string("checked","")} value="male"> Male
                                            </label>
                                            <label class="btn btn-default ${((user_info.gender!"") == "female")?string("active","")}">
                                                <input type="radio" name="gender" ${((user_info.gender!"") == "female")?string("checked","")} value="female"> Female
                                            </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="container-fluid">
                                        <div><label for="p_headpic">Head Picture</label></div>
                                        <div class="col-md-2">
                                            <#if (user_info.headPic?? && user_info.headPic != "")>
                                                <img src="${user_info.headPic}" onload="DrawImage(this,64,64)">
                                            <#else >
                                                <img src="holder.js/64x64">
                                            </#if>
                                        </div>
                                        <div class="col-md-10">
                                            <input type="url" name="headPic" id="p_headpic" value="${(user_info.headPic!"")?html}" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-4 col-md-offset-4">
                                        <input class="btn btn-success btn-block" type="submit" value="Submit">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Profile Secret</h4>
                    </div>
                    <div class="panel-body">
                        <div class="container-fluid">
                            <form method="post" action="/user/self/profile/secret">
                                <div class="form-group">
                                    <label for="p_secret">Keep my profile secret </label>
                                    <div>
                                        <input type="checkbox" ${user_info.secret?string("checked","")} data-toggle="toggle" data-on="YES" data-off="NO" name="secret" id="p_secret">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-4 col-md-offset-4">
                                        <input class="btn btn-success btn-block" type="submit" value="Submit">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            <#else >
                <div class="panel panel-primary">
                    <div class="panel-body">
                        <div class="page-header">
                            <h3 align="center">You haven't create your profile.</h3>
                        </div>
                        <div class="col-md-4 col-md-offset-4">
                            <a class="btn btn-primary btn-block" href="/user/profile?action=create">Create</a>
                        </div>
                    </div>
                </div>
            </#if>
        </div>
        <div class="col-md-4">
            <@userTemp.rightPanel userModel=user_model userInfo=user_info!"" active=1/>
        </div>
    </div>
</div>
</@templates.body>