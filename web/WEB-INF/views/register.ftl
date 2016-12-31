<#import "template/temp.ftl" as template>
<@template.body title="Register">
    <div class="container">
        <div class="page-header">
            <h1 align="center">Register</h1>
        </div>
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-primary">
                <div class="panel-body">
                    <form action="/user/register" method="post">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" id="username" name="name" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" id="password" class="form-control" name="password">
                        </div>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <input type="submit" class="btn btn-success btn-block" value="Submit">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@template.body>