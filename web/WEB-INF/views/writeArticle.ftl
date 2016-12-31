<#import "template/temp.ftl" as template>
<@template.body title="Write Article">
    <div class="container">
        <div class="page-header">
            <h1>Write Article</h1>
        </div>
        <form method="post" action="/article/write">
            <div class="form-group">
                <label for="af_title">Title</label>
                <input class="form-control" id="af_title" type="text" name="title">
            </div>
            <div class="form-group">
                <label for="af_subtitle">Sub-title</label>
                <input class="form-control" id="af_subtitle" type="text" name="subtitle">
            </div>
            <div class="form-group">
                <label for="af_body">Contents</label>
                <textarea id="af_body" name="article_body" class="form-control"></textarea>
            </div>
            <div class="form-group">
                <label for="af_status">Status:</label>
                <select id="af_status" name="status">
                    <option selected value="published">Published</option>
                    <option value="draft">Draft</option>
                    <option value="submitted">Submitted</option>
                    <option value="private">Private</option>
                    <option value="deleted">Deleted</option>
                    <option value="banned">Banned</option>
                    <option value="auditing">Auditing</option>
                </select>
            </div>
            <div class="form-group">
                <div class="col-md-4 col-md-offset-4">
                    <input type="submit" class="btn btn-success btn-block" value="Post">
                </div>
            </div>
        </form>
    </div>
</@template.body>