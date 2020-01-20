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
            <h1 class="h1-form">ERROR!</h1>
            <h1 class="h1-form"> <c:out value="${errorMessage}" default="ERROR"/></h1>
            <div id="ty-img-box">
                <img class="error-img" alt="" src="images/error.png">
            </div>
            
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>

</html>