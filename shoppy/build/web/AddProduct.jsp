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
        <SCRIPT SRC= "js/addPrduct.js"></SCRIPT>
        <!-- start-smoth-scrolling -->
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %></head>

    <body>
        <%@include file="Header.jsp" %>
        <div class="banner">
            <%@include file="Sidebar.jsp" %>

            <div class="w3l_banner_nav_right">
                <!-- login -->
                <div class="w3_login">
                    <h3>Add Product</h3>
                    <div class="w3_login_module">
                        <div class="module form-module">
                            <form action="addproduct" name="addProductForm" method="post" onsubmit="return validate(event)" enctype="multipart/form-data">
                                <span id="productNameAlert"> </span><br>
                                <label>Name<sup>*</sup></label> <input type="text" placeholder="Product Name" name="productName" id="tProductName"><br><br>
                                <span id="productDescriptionAlert"> </span><br>
                                <label>Description</label> <input type="text" name="description" placeholder="Product description" id="tProductDescription"><br><br>
                                <span id="priceAlert"> </span><br>
                                <label>Price<sup>*</sup></label> <input type="text" name="price" placeholder="price" id="tPrice"><br><br>
                                <span id="quantityAlert"> </span><br>
                                <label>Quantity in stock<sup>*</sup></label> <input type="number" name="quantity" placeholder="quantity" id="tQuantity"><br><br>
                                <span id="VendorAlert"> </span><br>
                                <label>Vendor<sup>*</sup></label> <input type="text" name="vendor" placeholder="vendor" id="tVendor"><br><br>
                                <span id="categoryAlert"> </span><br>
                                <label>Category<sup>*</sup></label> 
                                <select id="tCategory" name="category">
                                    <option value="Category">CATEGORY</option>
                                    <option value="FOOD">FOOD</option>
                                    <option value="CLOTHES">CLOTHES</option>
                                    <option value="DRINKS">DRINKS</option>
                                    <option value="ELECTRONICS">ELECTRONICS</option>
                                    <option value="FURNITURE">FURNITURE</option>
                                    <option value="STATIONARY">STATIONARY</option>
                                    <option value="ACCESSORIES">ACCESSORIES</option>
                                    <option value="OTHERS">OTHERS</option>
                                </select> 
                                <input type="file" id="fileF" name="file" accept="image/*">
                                <input type="submit" value="Add Product">
                            </form>                     
                        </div>
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
