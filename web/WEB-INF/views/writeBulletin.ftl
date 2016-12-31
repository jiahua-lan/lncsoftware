<#import "template/mainTemp.ftl" as template>
<@template.body title="Write Bulletin">
<div class="container">
    <div class="page-header">
        <h1>Write Bulletin</h1>
    </div>
    <form method="post" action="/bulletin/write">
        <div class="form-group">
            <label for="b_type">Type (Just a tag for searching)</label>
            <input class="form-control" id="b_type" type="text" name="type">
        </div>
        <div class="form-group">
            <label for="b_content">Content</label>
            <textarea class="form-control" id="b_content" name="content"></textarea>
        </div>
        <div class="form-group">
            <label for="b_imageLink">Image Link (Any URL to an image, optional)</label>
            <input type="url" id="b_imageLink" name="imageLink" class="form-control">
        </div>
        <div class="form-group">
            <label for="b_link">Link (Any link related to this bulletin)</label>
            <input type="url" id="b_link" name="link" class="form-control">
        </div>
        <div class="form-group">
            <div class="col-md-2 col-md-offset-10">
                <input type="submit" class="btn btn-success btn-block" value="Post">
                <a class="btn btn-default btn-block" href="/bulletin/">Back</a>
            </div>
        </div>
    </form>
</div>
</@template.body>