<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<#include "navbar.ftlh">
<#if logout??>
    <div class="alert alert-primary" role="alert">
        ${logout}
    </div>
</#if>

<div class="container">
    <div class="row">
        <div class="col-md-5 mx-auto">
            <div class="myform form ">
                <div class="logo mb-3">
                    <div class="col-md-12 text-center">
                        <br><br><br>
                        <img src="/images/logo.png" alt="Logo" style="width:300px;"><br><br>
                    </div>
                    <div class="col-md-12 text-center">
                        <h1>Login</h1>
                    </div>
                </div>
                <form action="/login" method="post">
                    <div class="form-group">
                        <label for="email1">Email</label>
                        <input type="email" name="username" class="form-control" id="email1" placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <label for="password1">Password</label>
                        <input type="password" name="password" id="password1" class="form-control"
                               placeholder="Enter Password">
                    </div>
                    <#if error??>
                        <div class="form-group">
                            <div class="alert alert-warning" role="alert">
                                ${error}
                            </div>
                        </div>
                    </#if>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="col-md-12 text-center mb-3">
                        <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Login</button>
                    </div>
                    <div class="form-group">
                        <p class="text-center">Don't have account? <a href="/register" id="signup">Sign up here</a></p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<#include "footer.ftlh">
</body>
</html>