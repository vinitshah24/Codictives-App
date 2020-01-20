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
            <h1 class="header-title">Welcome to Codictives</h1>
            <div class="out-div">
                <div class="container effect">
                    <img src="images/introimage-00.jpg" alt="Headphones" class="image">
                    <div class="middle">
                        <div class="text">Learn cutting edge technologies</div>
                    </div>
                </div>
                <div class="container effect">
                    <img src="images/introimage-01.jpeg" alt="Other Headphones" class="image">
                    <div class="middle">
                        <div class="text">Network with the industry professionals</div>
                    </div>
                </div>
                <div class="container effect">
                    <img src="images/introimage-09.jpg" alt="Other Headphones" class="image">
                    <div class="middle">
                        <div class="text">Share your skills among peers</div>
                    </div>
                </div>
            </div>
            <div class="out-div below-div">
                <div class="container effect">
                    <img src="images/introimage-10.jpg" alt="Headphones" class="image">
                    <div class="middle">
                        <div class="text">Create New Connections</div>
                    </div>
                </div>

                <div class="container effect">
                    <img src="images/introimage-02.jpeg" alt="Other Headphones" class="image">
                    <div class="middle">
                        <div class="text">Attend Interesting Connections</div>
                    </div>
                </div>

                <div class="container effect">
                    <img src="images/introimage-03.jpg" alt="Other Headphones" class="image">
                    <div class="middle">
                        <div class="text">Learn new Industry trends</div>
                </div>
            </div>
        </div>

    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>
</body>

</html>