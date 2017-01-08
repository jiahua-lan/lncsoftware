<#import "template/mainTemp.ftl" as template>
<@template.body title="Create Topic">
<div class="container">
    <div class="page-header">
        <h1>Create Topic</h1>
    </div>
    <form method="post" action="">
        <div class="form-group">
            <label for="f_name">Topic title</label>
            <input type="text" class="form-control" name="title" id="f_name">
        </div>
        <div class="form-group">
            <div class="col-md-2 col-md-offset-10">
                <input type="submit" class="btn btn-success btn-block" value="Create">
                <a class="btn btn-default btn-block" href="/article/">Back</a>
            </div>
        </div>
    </form>
</div>
</@template.body>