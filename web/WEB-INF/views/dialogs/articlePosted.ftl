<#import "../template/mainTemp.ftl" as template>
<@template.body title="Article Posted!">
<div class="container">
    <div class="page-header">
        <h1 align="center">Article posted!</h1>
        <p align="center">Your article &lt;&lt;${article_title}&gt;&gt; was saved!</p>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <a class="btn btn-block btn-success" href="/article/">Back to Article List</a>
        </div>
    </div>
</div>
</@template.body>