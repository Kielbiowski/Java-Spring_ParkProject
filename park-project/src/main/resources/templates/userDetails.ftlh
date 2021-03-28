<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Account Details</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<#include "navbar.ftlh">

<#if registration??>
    <div class="alert alert-dark" role="alert">Account registered successfully!</div>
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
                        <h1>Update your contact details</h1>
                    </div>
                </div>
                <form action="/user/details" method="post">
                    <div class="form-group">
                        <label for="name">Name</label><br>
                        <@spring.bind "userDTO.name"/>
                        <input type="text" name="name"
                                <#if userDTO.name??>
                                    value="${userDTO.name}"
                                <#else>
                                    placeholder="Enter your name"
                                </#if>
                               class="form-control" id="name"
                               aria-describedby="nameHelp">
                        <div id="nameHelp" class="form-text">
                            <#list spring.status.errorMessages as error>
                                <div class="alert alert-warning" role="alert">${error}</div>
                            </#list>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="surname">Surname</label><br>
                        <@spring.bind "userDTO.surname"/>
                        <input type="text" name="surname"
                                <#if userDTO.surname??>
                                    value="${userDTO.surname}"
                                <#else>
                                    placeholder="Enter your surname"
                                </#if>
                               class="form-control" id="surname"
                               aria-describedby="surnameHelp">
                        <div id="surnameHelp" class="form-text">
                            <#list spring.status.errorMessages as error>
                                <div class="alert alert-warning" role="alert">${error}</div>
                            </#list>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber">Phone Number</label><br>
                        <@spring.bind "userDTO.phoneNumber"/>
                        <input type="number" name="phoneNumber"
                                <#if userDTO.phoneNumber??>
                                    value="${userDTO.phoneNumber}"
                                <#else>
                                    placeholder="Enter your phone number"
                                </#if>
                               class="form-control" id="phoneNumber"
                               aria-describedby="phoneNumberHelp">
                        <div id="phoneNumberHelp" class="form-text">
                            <#list spring.status.errorMessages as error>
                                <div class="alert alert-warning" role="alert">${error}</div>
                            </#list>
                        </div>
                    </div>
                    <@spring.bind "userDTO.email"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="col-md-12 text-center mb-3">
                        <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<#include "footer.ftlh">
</body>
</html>