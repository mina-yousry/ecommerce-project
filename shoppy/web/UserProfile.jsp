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
        <title>User Profile Page</title>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    </head>
    <body onload="setEditFlag(false);
            changeEditability();">

        <!--<script type="text/javascript" src="js/AdminEditprofilepage.js"></script>-->
        <script type="text/javascript" src="js/editprofilevalidation.js"></script>
        <%@include file="Header.jsp" %>        
        <%@include file="Sidebar.jsp" %>
        <jsp:include page="/GetUserData"/>
        <div class="w3l_banner_nav_right">
            <!-- login -->
            <div class="w3_login">
                <h3> ${editedUser.firstName}'s Profile</h3>
                <div>

                    <div class="module form-module">
                        <label id="picL"style="text-align: center;margin: 10px">Profile Picture</label>
                        <img style="width:100px; height:100px;display:inline-block;" src="GetImage?idU=${editedUser.mail}" alt=" " class="img-responsive" style="display: inline-block;">
                        <c:if test="${!empty editedUser.profileImage}">
                            <a href="AdminEditUsersProfile?delPP=${editedUser.userId}">
                                <div class="close1" >
                                </div>
                            </a>
                        </c:if>
                    </div></div>



                <div class="w3_login_module">
                    <div class="module form-module">
                        <form action="AdminEditUsersProfile" method="POST" name="profileEditForm" onsubmit="return validate(event)" enctype="multipart/form-data">
                            <input type="file" id="fileF" name="file"accept="image/*"><br><br>

                            <span id="s1"> </span><br>
                            <label>First Name</label>
                            <input type="text" id="firstNameF" name="firstName" value="${editedUser.firstName}">
                            <br>
                            <span id="s2"> </span><br>
                            <label>Last Name</label>
                            <input type="text" id="lastNameF" name="lastName"readonly="" value="${editedUser.lastName}">
                            <br>

                            <span id="s3"> </span><br>
                            <label>Email</label>
                            <input type="text" id="mailF" name="mail" readonly="" value="${editedUser.mail}">
                            <br>

                            <span id="s4"> </span><br>
                            <label>Phone</label>
                            <input type="text" id="phoneF" name="phone" readonly="" value="${editedUser.phone}">
                            <br>

                            <span id="s5"> </span><br>
                            <label>Address</label>
                            <input type="text" id="addressF" name="address" readonly="" value="${editedUser.address}">            
                            <br>

                            <span id="s6"> </span><br>
                            <label>Date of Birth</label>
                            <input type="date" id="dobF" name="dob" readonly="" value="${editedUser.dob}">
                            <br>

                            <span id="s7"> </span><br>
                            <label>Job</label>
                            <input type="text" id="jobF" name="job" readonly="" value="${editedUser.job}">
                            <br>

                            <label>Balance</label>
                            <input type="text" id="balance" name="balance"  value="${editedUser.balance}">
                            <br>

                            <label>User Type</label>
<!--                            <input type="text" id="type" name="type"  value="${editedUser.type}">-->
                            <c:if test="${editedUser.type=='user'}">
                                <select id="tCategory" name="category">
                                    <option value="user" selected="true">user</option>
                                    <option value="admin" >admin</option>
                                </select>
                            </c:if>
                            <c:if test="${editedUser.type=='admin'}">
                                <select id="tCategory" name="category">
                                    <option value="user" >user</option>
                                    <option value="admin" selected="true">admin</option>
                                </select>
                            </c:if>
                            <br>

                            <span id="s8"> </span><br>
                            <label>Password</label>
                            <input type="password" id="passwordF" name="password"readonly="" value="${editedUser.password}">
                            <br>


                            <input type="hidden" id="userId" name="userId" readonly="" value="${editedUser.userId}">

                            <input type="submit" value="Save Changes" id="saveButton">
                            <br>



                        </form>

                    </div>

                </div>
            </div>
        </div>

    </body>
</html>
