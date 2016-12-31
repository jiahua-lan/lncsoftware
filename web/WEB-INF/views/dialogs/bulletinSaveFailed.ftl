<#import "../template/mainTemp.ftl" as template>
<@template.body title="Bulletin Save Failed">
<div class="container">
    <div class="page-header">
        <h1 align="center">Bulletin Save Failed</h1>
        <p align="center">Please check if your information match pattern.</p>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <a href="/bulletin/write" class="btn btn-primary btn-block">Write again</a>
            <a href="/bulletin/" class="btn btn-default btn-block">Back to List</a>
        </div>
    </div>
</div>
</@template.body>