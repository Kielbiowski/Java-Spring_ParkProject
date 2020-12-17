<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html>
<head>
    <title>Zarejestruj</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h1>Zarejestruj</h1>
<form action="/register" method="post">
    email:<br>
    <@spring.formInput "userDTO.email"/>
    <@spring.showErrors "<br>"/>
    <br><br>
    password:<br>
    <@spring.formInput "userDTO.password"/>
    <@spring.showErrors "<br>"/>
    <br><br>
    <input type="submit" name="save" value="Zarejestruj!">
</form>
</body>
</html>