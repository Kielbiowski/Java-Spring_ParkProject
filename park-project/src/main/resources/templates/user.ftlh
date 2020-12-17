<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html>
<head>
    <title>User</title>
    <meta charset="UTF-8"/>
</head>
<body>
<#if success??>
<h1>Account updated succesfully!</h1>
Your email is: ${userDTO.email}
<#else>
<h1>User information</h1>

Contact details:
<ul>
    <#if userDTO.name??>
    <li>Name: ${userDTO.name}</li>
    </#if>
    <#if userDTO.surname??>
    <li>Surname: ${userDTO.surname}</li>
    </#if>
    <#if userDTO.phoneNumber??>
    <li>Phone number: ${userDTO.phoneNumber}</li>
    </#if>
    <li>Email: ${userDTO.email}</li>
</ul>

Account balance: ${userDTO.accountBalance}<br>

<#--    TODO: spots,cars,parkings-->
</#if>

</body>
</html>