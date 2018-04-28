<%-- 
    Document   : Profile
    Created on : Feb 6, 2018, 5:27:53 PM
    Author     : Omar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %></head>
    <body onload="setEditFlag(false);
            changeEditability();">
        <%@include file="Header.jsp" %>        
        <%@include file="Sidebar.jsp" %>
        <script type="text/javascript" src="js/editprofilevalidation.js"></script>
        <div class="w3l_banner_nav_right">
            <!-- login -->
            <div class="w3_login">
                <h3> ${sessionScope.user.firstName}'s Profile</h3>
                <div>

                    <div class="module form-module">
                        <label id="picL"style="text-align: center;margin: 10px">Profile Picture</label>
                        <img style="width:100px; height:100px;display:inline-block;" src="GetImage?idU=${sessionScope.user.mail}" alt=" " class="img-responsive" style="display: inline-block;">
                        <c:if test="${!empty sessionScope.user.profileImage}">
                            <a href="ProfileUpdate?delPP=${sessionScope.user.userId}">
                                <div class="close1" >
                                </div>
                            </a>
                        </c:if>
                    </div></div>



                <div class="w3_login_module">
                    <div class="module form-module">
                        <form action="ProfileUpdate" method="POST" name="profileEditForm" onsubmit="return validate(event)" enctype="multipart/form-data">
                            <input type="file" id="fileF" name="file" accept="image/*"><br><br>

                            <label>Balance : ${sessionScope.user.balance} </label>
                            <br>

                            <span id="s1"> </span><br>
                            <label>First Name</label>
                            <input type="text" id="firstNameF" name="firstName" value="${sessionScope.user.firstName}">
                            <br>
                            <span id="s2"> </span><br>
                            <label>Last Name</label>
                            <input type="text" id="lastNameF" name="lastName" value="${sessionScope.user.lastName}">
                            <br>

                            <span id="s3"> </span><br>
                            <label>Email</label>
                            <input type="text" id="mailF" name="mail"  value="${sessionScope.user.mail}">
                            <br>

                            <span id="s4"> </span><br>
                            <label>Phone</label>
                            <input type="text" id="phoneF" name="phone"  value="${sessionScope.user.phone}">
                            <br>

                            <span id="s5"> </span><br>
                            <label>Address</label>
                            <input type="text" id="addressF" name="address"  value="${sessionScope.user.address}">            
                            <br>

                            <span id="s6"> </span><br>
                            <label>Date of Birth</label>
                            <input type="date" id="dobF" name="dob"  value="${sessionScope.user.dob}">
                            <br>

                            <span id="s7"> </span><br>
                            <label>Job</label>
                            <input type="text" id="jobF" name="job"  value="${sessionScope.user.job}">
                            <br>


                            <span id="s8"> </span><br>
                            <label>Password</label>
                            <input type="password" id="passwordF" name="password" value="${sessionScope.user.password}">
                            <br>


                            <input type="hidden" id="userId" name="userId"  value="${sessionScope.user.userId}">

                            <input type="submit" value="Save Changes" id="saveButton">
                            <br>



                        </form>

                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
