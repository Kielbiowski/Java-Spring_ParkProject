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

<#if message??>
    <h1>${message}</h1>
<#else>
    <h1>Login</h1><br>
    <#if error??>
        ${error}
        <br><br>
    </#if>
    <form action="/login" method="post">
        Email:<br>
        <input name="username" type="text"/>
        <br><br>

        Password:<br>
        <input name="password" type="password"/>
        <br><br>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="submit" name="login" value="Login!">
    </form>
</#if>

<#include "footer.ftlh">
</body>
</html>