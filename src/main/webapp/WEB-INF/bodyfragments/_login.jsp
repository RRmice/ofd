<!DOCTYPE html>

<head>
    <title>Login Page</title>

    <meta charset="UTF-8">

</head>

<body>

<div style="padding-top: 30px" class="panel-body">

    <form action="/authenticateTheUser" method="POST" class="form-horizontal">

        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input type="text" name="username" placeholder="username" class="form-control">
        </div>
        <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input type="password" name="password" placeholder="password" class="form-control">
        </div>
        <div style="margin-top: 10px" class="form-group">
            <div class="col-sm-6 controls">
                <button type="submit" class="btn btn-success">Sign up</button>
            </div>
        </div>
    </form>
</div>


<%--<div>--%>
<%--    <form method="POST" action="/authenticateTheUser">--%>
<%--        <div>--%>
<%--            <input name="username" type="text" placeholder="Username"--%>
<%--                   autofocus="true"/>--%>
<%--            <input name="password" type="password" placeholder="Password"/>--%>
<%--            <button type="submit">Sign up</button>--%>
<%--            <h4><a href="/registration/user">Зарегистрироваться</a></h4>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>

</body>
</html>