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
                <h1 class="h1-form">Contact Form</h1>
                <div class="container">
                    <form name="form" action="/Codictives/ProfileController" method="post"
                          class="contact-data">
                        <input class="contact-in" type="text" placeholder="First Name" 
                               name="firstName" id="contact-firstname" required>
                        <input class="contact-in" type="text" placeholder="Last Name" 
                               name="lastName" id="contact-lastname" required>
                        <input class="contact-in" type="text" placeholder="Subject" 
                               name="topic" id="contact-subject" required>
                        <textarea class="contact-in" placeholder="Message" name="message" ></textarea>
                        <input type="hidden" value="feedback" name="page" />
                        <input class="contact-in" id="submit-button" type="submit" name="task" value="Submit">
                    </form>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>

</html>