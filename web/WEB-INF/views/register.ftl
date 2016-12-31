<#import "template/mainTemp.ftl" as template>
<@template.body title="Register">
    <div class="container">
        <div class="page-header">
            <h1 align="center">Register</h1>
        </div>
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-success">
                <div class="panel-body">
                    <form action="/user/register" method="post">
                        <div class="form-group">
                            <label for="name">Username</label>
                            <input type="text" id="name" name="name" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" id="password" class="form-control" name="password">
                        </div>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <input type="submit" class="btn btn-success btn-block" value="Submit">
                                <a class="btn btn-block btn-default" href="/">Back</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@template.body>