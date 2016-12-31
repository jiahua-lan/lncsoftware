<#import "../template/mainTemp.ftl" as template>
<@template.body title="Welcome "+reg_username>
<div class="container">
    <div class="page-header">
        <h1 align="center">Register Successful</h1>
        <p align="center">Your name : ${reg_username}</p>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <a href="/" class="btn btn-success btn-block">Back to Homepage</a>
        </div>
    </div>
</div>
</@template.body>