<!DOCTYPE html>
<html lang="en">
<head>
    <title>ParkProject</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<style>
    #contact .card:hover i,#contact .card:hover h4{
        color: #0092cc;
    }
</style>
<body>
<#include "navbar.ftlh">

<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1 class="display-4">Welcome to ParkProject!</h1>
        <p class="lead">This site is a place for all parking spot owners, who want to rent their spots when not in use.</p>
        <hr class="my-4">
        <p>More information to come. Stay tuned!</p>
        <button type="button" class="btn btn-lg btn-dark" disabled>Learn more</button>
    </div>
</div>

<section id="contact">
    <div class="container">

        <h3 class="text-center text-uppercase">contact us</h3>

        <p class="text-center w-75 m-auto">Feel free to contact ParkProject and provide feedback.</p>

        <div class="row">
            <div class="col-sm-12 col-md-6 col-lg-3 my-5">
                <div class="card border-0">
                    <div class="card-body text-center">
                        <h4 class="text-uppercase mb-5">meet</h4>
                        <address>Rzeszów, Poland</address>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-3 my-5">
                <div class="card border-0">
                    <div class="card-body text-center">
                        <h4 class="text-uppercase mb-5">call</h4>
                        <p>668615060</p>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-3 my-5">
                <div class="card border-0">
                    <div class="card-body text-center">
                        <h4 class="text-uppercase mb-5">email</h4>
                        <p>kielbiowski.main@gmail.com</p>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-3 my-5">
                <div class="card border-0">
                    <div class="card-body text-center">
                        <h4 class="text-uppercase mb-5">visit</h4>
                        <a href="https://www.linkedin.com/in/szymon-kielbiowski/">LinkedIn</a></br>
                        <a href="https://github.com/Kielbiowski">GitHub</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>

<div class="">
<#include "footer.ftlh">
</body>
</html>