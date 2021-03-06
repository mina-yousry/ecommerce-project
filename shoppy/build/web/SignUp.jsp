<%-- 
    Document   : signup
    Created on : Feb 3, 2018, 3:13:28 PM
    Author     : hoda.CO
--%>
<!--
author: W3layouts
author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
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
        <SCRIPT SRC= "js/signup.js"></SCRIPT>
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
                    <h3>Sign Up</h3>
                    <div class="w3_login_module">
                        <div class="module form-module">
                            <form action="SignupServlet" name="registerform" method="post" onsubmit="return validate(event)">
                                <span id="s1"> </span><br>
                                <label>First Name<sup>*</sup></label> <input type="text" placeholder="First Name" name="fname" id="t1"><br><br>
                                <span id="s2"> </span><br>
                                <label>Last Name<sup>*</sup></label> <input type="text" name="lname" placeholder="Last Name" id="t2"><br><br>
                                <span id="s3"> </span><br>
                                <label>email<sup>*</sup></label> <input type="text" name="email" placeholder="email" id="t3"><br><br>
                                <span id="s4"> </span><br>
                                <label>Telephone<sup>*</sup></label> <input type="text" name="tele" placeholder="Phone" id="t4"><br><br>
                                <span id="s5"> </span><br>
                                <label>Address<sup>*</sup></label> <input type="text" name="add" placeholder="Address" id="t5"><br><br>
                                <span id="s6"> </span><br>
                                <label> Date of Birth<sup>*</sup></label> <input type="date" name="birth" id="t6"><br><br>
                                <span id="s7"> </span><br>
                                <label>Job<sup>*</sup></label> <input type="text" name="job" placeholder="Job" id="t7"><br><br>
                                <span id="s8"> </span><br>
                                <label>Password<sup>*</sup></label> <input type="password" name="pass" placeholder="Password" id="t8"><br><br>          
                                <input type="submit" value="Register">
                            </form>                     
                        </div>
                    </div>

                </div>



                <div class="clearfix"></div>
            </div>


            <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span>To Top</a>
            <!-- here stars scrolling icon -->
            <script type="text/javascript">
                $(document).ready(function () {
                    /*
                     var defaults = {
                     containerID: 'toTop', // fading element id
                     containerHoverID: 'toTopHover', // fading element hover id
                     scrollSpeed: 1200,
                     easingType: 'linear' 
                     };
                     */

                    $().UItoTop({easingType: 'easeOutQuart'});

                });
            </script>
            <!-- //here ends scrolling icon -->
    </body>
</html>
