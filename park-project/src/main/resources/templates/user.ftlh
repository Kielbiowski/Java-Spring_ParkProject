<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User</title>
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

    <#list userDTO.carDTOs>
        Cars:
        <ul>
            <#items as carDTO>
                <li>
                    <ol>
                        <li>Number plate: ${carDTO.numberPlate}</li>
                        <li>Brand: ${carDTO.brand}</li>
                        <li>Model: ${carDTO.model}</li>
                        <li>Color: ${carDTO.color}</li>
                        <li>Spot number: ${carDTO.spotDTO.number}</li>
                    </ol>
                </li>
            </#items>
        </ul>
    </#list>

    <#list userDTO.spotDTOs>
        Spots:
        <ul>
            <#items as spotDTO>
                <li>
                    <ol>
                        <li>Country: ${spotDTO.parkingDTO.country}</li>
                        <li>City: ${spotDTO.parkingDTO.city}</li>
                        <li>Street: ${spotDTO.parkingDTO.street}</li>
                        <li>Street number:${spotDTO.parkingDTO.streetNumber}</li>
                        <li>Spot number:${spotDTO.number}</li>
                        <li>Size: ${spotDTO.size}</li>
                        <li>Price per hour: ${spotDTO.parkingDTO.pricePerHour}</li>
                        <li>Car parked:
                            <ol>
                                <li>Number plate: ${spotDTO.carDTO.numberPlate}</li>
                                <li>Brand: ${spotDTO.carDTO.brand}</li>
                                <li>Model: ${spotDTO.carDTO.model}</li>
                                <li>Color: ${spotDTO.carDTO.color}</li>
                            </ol>
                        </li>
                    </ol>
                </li>
            </#items>
        </ul>
    </#list>

</#if>
</body>
</html>