<#if userDTO??&&userDTO.id??>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Logo -->
        <a class="navbar-brand" href="/">
            <img src="/images/logo.png" alt="Logo" style="width:30px;"> ParkProject
        </a>
        <!-- Links -->
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Cars</a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Spots</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user">User</a>
            </li>
            <form id="logoutForm" method="post" action="/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <li class="nav-item">
                <a class="nav-link" onclick="document.forms['logoutForm'].submit()">Logout</a>
            </li>
        </ul>
    </nav>
<#else>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Logo -->
        <a class="navbar-brand" href="/">
            <img src="/images/logo.png" alt="Logo" style="width:30px;"> ParkProject
        </a>
        <!-- Links -->
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="/register">Register</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
        </ul>
    </nav>
</#if>



