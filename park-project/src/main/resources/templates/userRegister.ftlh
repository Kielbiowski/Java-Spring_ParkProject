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
    <h1>Account registered successfully!</h1>
    Your email is: ${userDTO.email}
<#else>
    <h1>Register</h1>
    <form action="/register" method="POST">
        Email:<br>
        <@spring.formInput "userDTO.email"/><br>
        <@spring.showErrors "<br>"/><br><br>

        Password:<br>
        <@spring.formPasswordInput "userDTO.password"/><br>
        <@spring.showErrors "<br>"/><br><br>

        Confirm password:<br>
        <@spring.formPasswordInput "userDTO.passwordConfirm"/><br>
        <@spring.showErrors "<br>"/><br><br>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <input type="submit" name="save" value="Register!">
    </form>
</#if>

<#include "footer.ftlh">
</body>
</html>