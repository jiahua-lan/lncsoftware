<#import "../template/mainTemp.ftl" as template>
<@template.body title="Article Not Found">
<div class="container">
    <div class="page-header">
        <h1 align="center">Article Not Found</h1>
        <p align="center">The article you are looking for maybe deleted or not existed.</p>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <a class="btn btn-block btn-primary" href="/article/">Back to Article List</a>
            <a class="btn btn-block btn-default" href="/">Back to Homepage</a>
        </div>
    </div>
</div>
</@template.body>