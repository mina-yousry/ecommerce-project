<%-- 
    Document   : view_users
    Created on : Feb 12, 2018, 12:40:47 PM
    Author     : hoda.CO
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <c:if test="${empty requestScope.users_list}">
            <jsp:include page="/GetUserData"/>
        </c:if>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %></head>
    <body>
        <%@include file="Header.jsp" %>
        <%@include file="Sidebar.jsp" %>

        <div class="banner">
            <div class="w3l_banner_nav_right">
                <div class="privacy about">
                    <h3>Users List</h3><br><br>

                    <div id="container">
                        <div id="content">
                            <table class="timetable_sub">
                                <tr>
                                    <th> ID </th>
                                    <th> First Name </th>
                                    <th> Last Name </th>
                                    <th> Phone </th>                   
                                    <th> Birth Date </th>
                                    <th> Job </th>
                                    <th> Email </th>
                                    <th> Type </th>
                                    <th> Balance </th>
                                    <th> Address </th>
                                    <th> Image </th>
                                    <th>History</th>
                                    <th>Delete</th>
                                </tr>
                                <c:forEach var="userr" items="${users_list}">                   
                                    <tr>
                                        <td>${userr.userId}</td>
                                        <td>

                                            <c:if test="${userr.userId!=sessionScope.user.userId}">
                                                <a href="UserProfile.jsp?id=${userr.userId}">
                                                    ${userr.firstName}
                                                </a>
                                            </c:if>
                                            <c:if test="${userr.userId==sessionScope.user.userId}">
                                                <a href="Profile.jsp">
                                                    ${userr.firstName}
                                                </a>
                                            </c:if>
                                        </td>
                                        <td>${userr.lastName}</td>
                                        <td>${userr.phone}</td>
                                        <td>${userr.dob}</td>
                                        <td>${userr.job}</td>
                                        <td>${userr.mail}</td>
                                        <td>${userr.type}</td>
                                        <td>${userr.balance}</td>
                                        <td>${userr.address}</td>
                                        <td>
                                            <img style="width:30px;height:30px; max-height:30px;max-width:30px;" src="GetImage?idU=${userr.mail}" alt=" " class="img-responsive" >

                                        </td>
                                        
                                        <td class="invert">
                                            <c:if test="${userr.userId!=sessionScope.user.userId}">
                                                <div class="rem">

                                                    <a href="CartsHistory.jsp?id=${userr.userId}&n=${userr.firstName}">
                                                          History
                                                    </a>
                                                </div>
                                            </c:if>
                                        </td>

                                        <td class="invert">
                                            <c:if test="${userr.userId!=sessionScope.user.userId}">
                                                <div class="rem">

                                                    <a href="DeleteUser?id=${userr.userId}">
                                                        <div class="close1">
                                                        </div>
                                                    </a>
                                                </div>
                                            </c:if>
                                        </td>
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
