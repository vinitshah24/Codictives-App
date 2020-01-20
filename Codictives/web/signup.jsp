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
                <form id="form-box" name="form" action="/Codictives/ProfileController" 
                      method="post">
                    <div class="imgcontainer">
                        <img src="images/bash_logo.png" alt="Codictives" class="bash-logo">
                    </div>
                    <div class="container">
                        <div id="form-data">
                            <input class="login-signup-txtbx" type="text" placeholder="First Name" 
                                   name="firstname" id="firstname" required>
                            <input class="login-signup-txtbx" type="text" placeholder="Last Name" 
                                   name="lastname" id="lastname" required>
                            <input class="login-signup-txtbx" type="text" placeholder="Username" 
                                   name="username"  required>
                            <input class="login-signup-txtbx" type="password" placeholder="Password" 
                                   id="password" name="password" required>
                            <input class="login-signup-txtbx" type="text" placeholder="Email" 
                                   name="email"  required>
                            <input class="login-signup-txtbx" type="text" placeholder="Address" 
                                   name="address"  required>
                            <input class="login-signup-txtbx" type="text" placeholder="City" 
                                   name="city"  required>
                            <input class="login-signup-txtbx" type="text" placeholder="State" 
                                   name="state"  required>
                            <input class="login-signup-txtbx" type="text" placeholder="Zip Code" 
                                   name="zip"  required>
                            <input class="login-signup-txtbx" type="text" placeholder="Country" 
                                   name="country"  required>                            
                            <input type="hidden" value="signup" name="page" />
                            <input id="submit-button" type="submit" value="Sign Up" name="signup">
                        </div>
                        <input type="submit" id="signup-button" 
                               onclick="location.href = 'login.jsp';" value="Login" />
                    </div>
                </form>
            </div>
        </div>
        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>