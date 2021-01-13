<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<#include "navbar.ftlh">
<#if success??>
    <div class="alert alert-primary" role="alert">
        Account registered successfully!
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
                        <h1>Sign up</h1>
                    </div>
                </div>
                <form action="/register" method="post">
                    <div class="form-group">
                        <label for="email1">Email address</label><br>
                        <@spring.formInput "userDTO.email"/><br>
                        <@spring.showErrors "<br>"/><br><br>
<#--                        <input type="email" name="email" class="form-control" id="email1" aria-describedby="emailHelp1" placeholder="Enter email">-->
                    </div>
                    <div class="form-group">
                        <label for="password1">Password</label><br>
                        <@spring.formPasswordInput "userDTO.password"/><br>
                        <@spring.showErrors "<br>"/><br><br>
<#--                        <input type="password" name="password" id="password1" class="form-control" aria-describedby="passwordHelp1" placeholder="Enter Password">-->
                    </div>
                    <div class="form-group">
                        <label for="password2">Confirm password</label><br>
                        <@spring.formPasswordInput "userDTO.passwordConfirm"/><br>
                        <@spring.showErrors "<br>"/><br><br>
<#--                        <input type="password" name="password" id="password2" class="form-control" aria-describedby="passwordHelp2" placeholder="Enter Password">-->
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="col-md-12 text-center mb-3">
                        <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Sign up for free</button>
                    </div>
                    <div class="col-md-12 ">
                        <div class="form-group">
                            <p class="text-center"><a href="/login" id="login">Already have an account?</a></p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>


    <#include "footer.ftlh">
</body>
</html>