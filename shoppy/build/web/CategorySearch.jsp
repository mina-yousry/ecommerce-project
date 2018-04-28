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
        <!-- start-smoth-scrolling -->
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
        <jsp:include page="/CategorySearchProductServlet"/>
        <c:if test="${empty requestScope.searchedProducts}">
            <c:redirect url="/Notification.jsp?n=Product+Not+Found+in+this+category"/>
        </c:if>
    
    </head>

    <body>
        <%@include file="CategoryHeader.jsp" %>
        <div class="banner">
            <%@include file="Sidebar.jsp" %>

            <!-- Display Searched products list  -->


            <c:if test="${!empty requestScope.searchedProducts}">
                <div class="w3l_banner_nav_right">
                    <div class="w3ls_w3l_banner_nav_right_grid w3ls_w3l_banner_nav_right_grid_veg">
                        <h3 class="w3l_fruit"></h3>
                    </div>
                    <div class="w3ls_w3l_banner_nav_right_grid w3ls_w3l_banner_nav_right_grid_veg">
                        <h3 class="w3l_fruit">Founded</h3>
                        <c:forEach items="${searchedProducts.values()}" var="product">
                             <div class="col-md-3 w3ls_w3l_banner_left w3ls_w3l_banner_left_asdfdfd" style="margin-bottom:20px ">
                                <div class="hover14 column">
                                    <div class="agile_top_brand_left_grid w3l_agile_top_brand_left_grid">


                                        <c:if test="${!(user.type eq 'admin')}">
                                            <div class="agile_top_brand_left_grid1">
                                                <figure>
                                                    <div class="snipcart-item block">
                                                        <div class="snipcart-thumb">
                                                            <a href="Single.jsp?id=${product.getProductId()}"><img src="GetImage?id=${product.getProductId()}" alt=" " class="img-responsive" style="width:150px;height:150px; max-height:150px;max-width:150px;background-color: transparent;"></a>

                                                            <p>${product.productName}</p>
                                                            <p>Price : ${product.getPrice()} EGP</p>
                                                            <p>Qty : ${(product.quantityStock-product.quantityCart)}</p>

                                                            <div class="snipcart-details">
                                                                <c:if test="${(product.quantityStock-product.quantityCart > 0)}">

                                                                    <form action="CartProductAddition" method="post">
                                                                        <fieldset>
                                                                            <input type="hidden" name="addedProduct" value="${product.productId}">
                                                                            <input type="text" name="addedProductQty" value="1" min="1" max="${(product.quantityStock-product.quantityCart)}"style="margin:10px;width:50px " required=""
                                                                                   onblur="if (this.value >${(product.quantityStock-product.quantityCart)}) {
                                                                                               this.value =${(product.quantityStock-product.quantityCart)}
                                                                                           } else if (this.value < 1) {
                                                                                               this.value = 1;
                                                                                           }">
                                                                            <input type="submit" name="submit" value="Add to cart" class="button">
                                                                        </fieldset>
                                                                    </form>
                                                                </c:if>

                                                                <c:if test="${!((product.quantityStock-product.quantityCart)>0)}">
                                                                    <p>Out of Stock</p>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </figure>
                                            </div>
                                        </c:if>




                                        <c:if test="${user.type eq 'admin'}">
                                            <div class="agile_top_brand_left_grid1">
                                                <figure>
                                                    <div class="snipcart-item block">
                                                        <div class="snipcart-thumb">
                                                            <a href="Single.jsp?id=${product.getProductId()}"><img src="GetImage?id=${product.getProductId()}" alt=" " class="img-responsive" style="width:150px;height:150px; max-height:150px;max-width:150px;"></a>

                                                            <p>${product.productName}</p>
                                                            <p>Price : ${product.getPrice()} EGP</p>


                                                            <c:if test="${!((product.quantityStock-product.quantityCart)>0)}">
                                                                <p>Out of Stock</p>
                                                            </c:if>

                                                            <c:if test="${((product.quantityStock-product.quantityCart)>0)}">
                                                                <p>Qty in Stock : ${product.quantityStock}</p>

                                                                <p>Qty in Carts : ${product.quantityCart}</p>
                                                            </c:if>

                                                            <div class="snipcart-details">
                                                                <form action="EditProduct.jsp" method="post">
                                                                    <fieldset>
                                                                        <input type="hidden" name="editedProduct" value="${product.productId}">
                                                                        <!--should Enter qty-->
                                                                        <input type="submit" name="submit" value="Edit Product" class="button">
                                                                    </fieldset>
                                                                </form>
                                                            </div>        
                                                        </div>
                                                    </div>
                                                </figure>
                                            </div>
                                        </c:if>






                                    </div>
                                </div>
                            </div>

                            
                        </c:forEach>
                    </div>
                </div>
                <div class="clearfix"> </div>
            </c:if>
          
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