<#import "template/temp.ftl" as template>
<@template.body title="Welcome "+reg_username>
<div class="container">
    <div class="page-header">
        <h1 align="center">Register successful</h1>
        <p align="center">Your username : ${reg_username}</p>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <a href="/" class="btn btn-success btn-block">Back to homepage</a>
        </div>
    </div>
</div>
</@template.body>