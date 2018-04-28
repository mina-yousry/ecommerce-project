<%-- 
    Document   : Cart
    Created on : Feb 7, 2018, 8:39:45 PM
    Author     : Omar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>

    <body>
        <%@include file="Header.jsp" %>
        <jsp:include  page="\CalcTotalPrice" />
        <div class="banner">
        
            <c:if test="${param.ch=='true'}">
                <script type="text/javascript">
                    alert(${param.balance} + " EGP has been charged");
                    window.location = "Cart.jsp";
                </script>
            </c:if>
            <c:if test="${param.ch=='false'}">
                <script type="text/javascript">
                    alert("wrong or already charged code !");
                    window.location = "Cart.jsp";
                </script>
            </c:if>

            <%@include file="Sidebar.jsp" %>
            <div class="w3l_banner_nav_right">
                <div class="privacy about">
                    <h3>Check out</h3>


                    <div class="checkout-right">

                        <c:if test="${(empty cart.totalItems)||(cart.totalItems==0)}">
                            <h4>Your shopping cart contains: <span>0 Products</span></h4>
                        </c:if>
                        <c:if test="${(cart.totalItems>0)}">
                            <h4>Your shopping cart contains: <span>${cart.totalItems} Products</span></h4>
                        </c:if>
                        <c:if test="${(cart.totalItems > 0)}">
                            <table class="timetable_sub">
                                <thead>
                                    <tr>
                                        <th>SN</th>	
                                        <th>Product</th>
                                        <th>Quantity</th>
                                        <th>Product Name</th>

                                        <th>Price</th>
                                        <th>Remove</th>
                                    </tr>
                                </thead>
                                <tbody><tr class="rem1">

                                        <c:forEach items="${cart.productMap.keySet()}" var="productId">
                                            <c:if test="${(!empty Products.get(productId))}">

                                                <td class="invert">${productId}</td>
                                                <td class="invert-image"> <a href="Single.jsp?id=${productId}"><img src="GetImage?id=${productId}" alt=" " class="img-responsive" style=" max-height:80px;max-width:100px;"></a>
                                                </td>
                                                <td class="invert">
                                                    <div class="quantity"> 
                                                        <div class="quantity-select"> 
                                                            <c:if test="${(cart.productMap.get(productId)>1)}">
                                                                <a href="CartProductRemoval?removedProduct=${productId}&removedProductQty=1">
                                                                </c:if>
                                                                <div class="entry value-minus">   &nbsp;

                                                                </div>

                                                                <c:if test="${(cart.productMap.get(productId)>1)}">
                                                                </a> 
                                                            </c:if>
                                                            <div class="entry value">${cart.productMap.get(productId)}</div>
                                                            <c:if test="${((Products.get(productId).quantityStock-Products.get(productId).quantityCart)>0)}">
                                                                <a href="CartProductAddition?addedProduct=${productId}&addedProductQty=1">
                                                                </c:if>
                                                                <div class="entry value-plus active">
                                                                    &nbsp;
                                                                </div>

                                                                <c:if test="${((Products.get(productId).quantityStock-Products.get(productId).quantityCart)>0)}">
                                                                </a>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td class="invert">${Products.get(productId).productName}</td>

                                                <td class="invert">${Products.get(productId).price} EGP</td>
                                                <td class="invert">
                                                    <div class="rem">

                                                        <a href="CartProductRemoval?removedProduct=${Products.get(productId).productId}&removedProductQty=${cart.productMap.get(productId)}">
                                                            <div class="close1">
                                                            </div>
                                                        </a>

                                                    </div>

                                                </td>
                                            </tr>

                                        </c:if>

                                    </c:forEach>

                                </tbody></table>
                        </div>
                        <div class="checkout-left">	
                            <div class=" checkout-left-basket">
                                <a href="Home.jsp"> <h4>Continue to basket</h4></a>
                                <ul>
                                    <c:forEach items="${cart.productMap.keySet()}" var="productId">
                                        <c:if test="${(!empty Products.get(productId))}">
                                            <li>${Products.get(productId).productName} <i> </i>* ${cart.productMap.get(productId)} <i>-</i><span>${Products.get(productId).price*cart.productMap.get(productId)}</span></li>
                                            </c:if>
                                        </c:forEach>
                                      
                                    <li>Total Price <i>-</i> <span>${cart.totalPrice} EGP</span></li>

                                    <li>Your Balance <i>-</i> <span>${sessionScope.user.balance} EGP</span></li>
                                </ul>
                            </div>
                            <div class="checkout-right-basket">
                                <!--<a href="purchase">Check Out  <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a>-->
                                <script type="text/javascript" src="js/purchasevalidation.js"></script>
                                <form  action="Purchase" method="post" name="purchaseForm" onsubmit="return checkBalance(event, ${user.balance}, ${cart.totalPrice})" >
                                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                    <input type="submit" value="Check Out" class="glyphicon glyphicon-chevron-right" aria-hidden="true">
                                </form>
                            </div>
                        </c:if>
                        <div align="right">
                            <form action="balance" method="post" name="chargeForm" >
                                <input type="text" placeholder="Enter charge Code" name="code" id="chargeCode" required=" ">
                                <input type="submit" value="charge" >
                            </form>
                        </div>
                        <div class="clearfix"></div>

                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
