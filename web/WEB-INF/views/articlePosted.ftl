<#import "template/temp.ftl" as template>
<@template.body title="Article Posted!">
<div class="container">
    <div class="page-header">
        <h1 align="center">Article posted!</h1>
        <p align="center">Your article &lt;${article_title}&gt; was posted!</p>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <a class="btn btn-block btn-success" href="/article/">Back to article list</a>
        </div>
    </div>
</div>
</@template.body>