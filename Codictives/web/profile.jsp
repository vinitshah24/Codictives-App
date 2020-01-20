<%@page contentType="text/html" pageEncoding="utf-8"%>
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

            <!-- Main Content -->
            <h1 class="h1-form">Your Profile</h1>

            <div class="my-profile">

                <table class="user-info">
                    <tr>
                        <td><b>Username: </b></td>
                        <td><c:out value="${userProfile.userName}" default="No UserName"/></td>
                    </tr>
                    <tr>
                        <td><b>First Name: </b></td>
                        <td><c:out value="${userProfile.firstName}" default="No FirstName"/></td>
                    </tr>                    
                    <tr>
                        <td><b>Last Name: </b></td>
                        <td><c:out value="${userProfile.lastName}" default="No LastName"/></td>
                    </tr>                    
                    <tr>
                        <td><b>Email: </b></td>
                        <td><c:out value="${userProfile.email}" default="No Email"/></td>
                    </tr>                    
                    <tr>
                        <td><b>Home Address: </b></td>
                        <td><c:out value="${userProfile.address}" default="No Address"/></td>
                    </tr>                    
                    <tr>
                        <td><b>City: </b></td>
                        <td><c:out value="${userProfile.city}" default="No City"/></td>
                    </tr>
                    <tr>
                        <td><b>Zip Code: </b></td>
                        <td><c:out value="${userProfile.zip}" default="No ZipCode"/></td>
                    </tr>
                    <tr>
                        <td><b>Country: </b></td>
                        <td><c:out value="${userProfile.country}" default="No Country"/></td>
                    </tr>
                </table>
            </div>
            <form method="post" name="form" action="/Codictives/ProfileController">
                <input type="hidden" name="page" value="profile">
                <button class="editprof-button">Edit Profile</button>
            </form>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>

</html>