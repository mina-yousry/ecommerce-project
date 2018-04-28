<%-- 
    Document   : CartsHistory
    Created on : Feb 15, 2018, 1:03:00 PM
    Author     : hoda.CO
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <c:if test="${empty requestScope.carts_list}">
            <jsp:include page="/GetCartHistory"/>
        </c:if>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %></head>
    <body>
        <%@include file="Header.jsp" %>
        <%@include file="Sidebar.jsp" %>

        <div class="banner">
            <div class="w3l_banner_nav_right">
                <div class="privacy about">
                    <h3> ${username}'s History </h3><br><br>

                    <div id="container">
                        <div id="content">
                            <table class="timetable_sub">
                                <tr>
                                    <th> Time </th>
                                    <th> Cart Id </th>
                                    <th> Product Id </th>                                  
                                    <th> Quantity </th>                   
                                    <th> Total Price </th>
                                </tr>
                                <c:forEach var="carts" items="${carts_list}">                   
                                    <tr>
                                        <td>${carts.time}</td>
                                        <td>${carts.cartId}</td>                                       
                                        <td>
                                       <h:outputText value="Key:${carts.productMap}"/>  
                                        <c:forEach var="map" items="${carts.productMap}">
                                             ${map.key}
                                             <br>
                                        </c:forEach>
                                        </td>
                                        <td>
                                            <h:outputText value="Key:${carts.productMap}"/> 
                                             <c:forEach var="map" items="${carts.productMap}">
                                             ${map.value}
                                             <br>
                                        </c:forEach>
                                        </td>
                                        <td>${carts.totalPrice}</td>                                                                           
                                    </tr>
                                </c:forEach>
                            </table> 
                        </div>           
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

