<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <meta charset="UTF-8"/>
</head>
<body>
<#if success??>
    <h1>Account registered succesfully!</h1>
    Your email is: ${userDTO.email}
    <#else>
        <h1>Register</h1>
        <form action="/register" method="post">
            Email:<br>
            <@spring.formInput "userDTO.email"/>
            <@spring.showErrors "<br>"/>
            <br><br>
            Password:<br>
            <@spring.formPasswordInput "userDTO.password"/>
            <@spring.showErrors "<br>"/>
            <br><br>
            <input type="submit" name="save" value="Register!">
        </form>
</#if>

</body>
</html>