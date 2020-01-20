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
                <h1 class="about-h1">Codictives creates Possibilities</h1>
                <p>
                    Codictives is a platform for coding enthusiasts to make connections 
                    and get involved in the coding community. We organize meet-ups, 
                    connection nights, training lectures, and conferences by allowing the
                    professionals from the tech industry to provide insights on the 
                    latest news and programming techniques.
                </p>
                <br>
                <p>
                    Joining Codictives will allow you to gain connections with other 
                    programmers and would help you build a successful future by broadening
                    your technical as well as leadership skills to excel at your job.
                </p>

                <h2 class="about-h2">Let's get in touch</h2>
                <p class="about-p">
                    Feel free to send an email to inquiries@codictives.com or get in touch 
                    via social media for more updates.
                </p>
            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </body>

</html>