<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Codictives</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Poppins&display=swap" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css">
        <link rel="shortcut icon" href="images/bash_logo.png" />
    </head>

    <body>
        <div class="content">
            <!-- Header User Specific Nav -->
            <jsp:include page="header.jsp"/>

            <!-- Main Content-->
            <div class="main-div">

                <div class="temp-login">
                    <p id="tmp-dta1">Please use below username and password for testing: </p>
                    <p id="tmp-dta2">Account 1 - admin : admin </p>
                    <p id="tmp-dta3">Account 2 - user  : user </p>
                    <p id="tmp-dta4">Account 3 - vinit  : vinit </p>
                </div>

                <form id="form-box" name="form" action="/Codictives/ProfileController" method="post">
                    <div class="imgcontainer">
                        <img src="images/bash_logo.png" alt="Codictives" class="bash-logo">
                    </div>
                    <div class="container">
                        <div id="form-data">
                            <input class="login-signup-txtbx" type="text" placeholder="Username" 
                                   id="username" name="username" required>

                            <input class="login-signup-txtbx" type="password" placeholder="Password" 
                                   id="password" name="password" required>

                            <input type="hidden" value="login" name="page" />
                            
                            <input type="submit" value="Login" id="submit-button" name="login">
                        </div>
                        <input type="button" id="signup-button" 
                               onclick="location.href = 'signup.jsp';" value="Sign Up" />
                    </div>
                </form>
            </div>
        </div>
        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>