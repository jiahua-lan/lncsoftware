<#import "template/temp.ftl" as template>
<@template.body title="Welcome - "+login_username>
    <div class="container">
        <div class="page-header">
            <h1 align="center">Welcome ${login_username}</h1>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <a class="btn btn-block btn-success" href="/">Back to home</a>
            </div>
        </div>
    </div>
</@template.body>