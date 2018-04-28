<%-- 
    Document   : signin
    Created on : Feb 4, 2018, 9:36:06 PM
    Author     : hoda.CO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Grocery Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
          Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
        function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //for-mobile-apps -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <!-- font-awesome icons -->
    <link href="css/font-awesome.css" rel="stylesheet" type="text/css" media="all" /> 
    <!-- //font-awesome icons -->
    <!-- js -->
    <script src="js/jquery-1.11.1.min.js"></script>
    <!-- //js -->
    <script src='js/okzoom.js'></script>
    <script>
        $(function () {
            $('#example').okzoom({
                width: 150,
                height: 150,
                border: "1px solid black",
                shadow: "0 0 5px #000"
            });
        });
    </script>
    <link href='//fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,400italic,500,500italic,700,700italic' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <!-- start-smoth-scrolling -->
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
    </script>
    <!-- start-smoth-scrolling -->
    <%@ page session = "false" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %></head>

<body>
    <%@include file="Header.jsp" %>
    <div class="banner">
        <%@include file="Sidebar.jsp" %>

        <div class="w3l_banner_nav_right">
            <!-- login -->
            <div class="w3_login">
                <h3>Sign In</h3>
                <div class="w3_login_module">
                    <div class="module form-module">

                        <form action="SigninServlet" method="post">
                            <input type="text" placeholder="email" name="email" value="${cookie.userEmail.value}" required=" " onfocus="document.getElementById('error').innerHTML = '';">
                            <input type="password" placeholder="Password" name="password"  value="${cookie.userPass.value}" required=" " onfocus="document.getElementById('error').innerHTML = '';">
                            <input type="submit" value="Login">
                        </form> 
                        <c:if test="${!empty param.n}">
                            <h3  id="error" style="color: RED;">${ param.n}</h3>
                        </c:if>
                    </div>
                </div>

            </div>
            <div class="clearfix"></div>


        </div>
</body>
</html>
