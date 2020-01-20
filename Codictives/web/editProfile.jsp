<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
            <div class="conn-div">

                <h1 class="new-conn-h1">Update Profile</h1>
                <form class="conn-form" action="/Codictives/ProfileController" method="post">

                    <input type="hidden" value="${userProfile.userName}" name="username" />
                    <input type="hidden" value=${userProfile.password} name="password" />

                    <label class="conn-lbl">First Name</label><br>
                    <input class="conn-input" type="text" name="firstname" 
                           value="${userProfile.firstName}" required> 
                    <br>
                    <label class="conn-lbl">Last Name</label><br>
                    <input class="conn-input" type="text" name="lastname" 
                           value="${userProfile.lastName}" required>
                    <br>
                    <label class="conn-lbl">Email</label><br>
                    <input class="conn-input" type="text" name="email" 
                           value="${userProfile.email}" required>
                    <br>
                    <label class="conn-lbl">Home Address</label>
                    <br>
                    <input class="conn-input" type="text" name="address" 
                           value="${userProfile.address}" required>
                    <br>
                    <label class="conn-lbl">City</label><br>
                    <input class="conn-input" type="text" name="city" 
                           value="${userProfile.city}" required>
                    <br>
                    <label class="conn-lbl">Zip Code</label><br>
                    <input class="conn-input" type="text" name="zip" value="${userProfile.zip}" required>
                    <br>
                    <label class="conn-lbl">Country</label><br>
                    <input class="conn-input" type="text" name="country" 
                           value="${userProfile.country}" required>
                    <br>
                    <input type="hidden" value=${userSession} name="currentUser" />
                    <input type="hidden" value="editProfile" name="page" />

                    <label class="conn-lbl">&nbsp;</label><br>
                    <input class="conn-input" id="conn-submit" type="submit" value="Update Profile"/><br>
                </form>
            </div>
        </div>
        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>

</html>