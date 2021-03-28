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
            <div class="logo mb-3">
                <div class="col-md-12 text-center">
                    <br><br><br>
                    <img src="/images/logo.png" alt="Logo" style="width:300px;"><br><br>
                </div>
            </div>
            <div class="col-md-12 text-center">
                <h1>Your contact details</h1>
                <br>
                <ul class="list-group">
                    <li class="list-group-item active">Email: ${userDTO.email}</li>
                    <#if userDTO.name??>
                        <li class="list-group-item">Name: ${userDTO.name}</li>
                    </#if>
                    <#if userDTO.surname??>
                        <li class="list-group-item">Surname: ${userDTO.surname}</li>
                    </#if>
                    <#if userDTO.phoneNumber??>
                        <li class="list-group-item">Phone number: ${userDTO.phoneNumber}</li>
                    </#if>
                </ul>
                <br><br>
                <a class="btn btn-block mybtn btn-primary tx-tfm" href="/user/details" role="button">Update your contact details</a>
                <br><br>
            </div>
            <div class="col-md-12 text-center">
                <h1>Your account balance</h1>
                <br>
                <ul class="list-group">
                    <li class="list-group-item">Account balance: ${userDTO.accountBalance}</li>
                </ul>
                <br><br>
                <a class="btn btn-block mybtn btn-primary tx-tfm disabled" href="#" role="button">Manage your account balance</a>
                <br><br>
            </div>

        </div>
    </div>
</div>

<#include "footer.ftlh">
</body>
</html>