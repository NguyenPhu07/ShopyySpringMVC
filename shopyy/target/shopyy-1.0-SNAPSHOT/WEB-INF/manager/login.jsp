<%-- 
    Document   : login
    Created on : Aug 24, 2024, 3:34:08 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/css/login.css"/>" />




<c:if test="${param.error != null}">
    <div class="alert alert-danger">Bạn nhập sai thông tin hoặc Bạn chưa được duyệt!</div>
</c:if>
<c:if test="${param.accessDenied  != null}">
    <div class="alert alert-danger">
            Bạn đã truy cập không đúng quyền hạn!
        </div>
    <c:if test="${pageContext.request.isUserInRole('SELLER')}">
        <c:url value="/shop" var="shop"/>
        <a class="btn btn-primary" href="${shop}" role="button">Quay về Cửa Hàng</a>
    </c:if>
    <c:if test="${pageContext.request.isUserInRole('ADMIN') || pageContext.request.isUserInRole('STAFF')}">
        <c:url value="/manager" var="man"/>
        <a class="btn btn-primary" href="${man}" role="button">Quay về Trang Chủ</a>
    </c:if>
</c:if>
<c:if test="${param.active  != null}">
    <div class="alert alert-danger">
        Tài Khoản của bạn đang chờ duyệt!
    </div>
</c:if>


<c:if test="${param.accessDenied  == null}" > <!--ko có accessDenied để lỡ login bị accessDenied thì ko hiện cái này ra!-->
    <c:url value="/login" var="action"/>
    <form action="${action}" method="post">

        <div class="container">
            <label for="uname"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="username" required>

            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" required>

            <button type="submit">Login</button>
            <label>
                <input type="checkbox" checked="checked" name="remember"> Remember me
            </label>
        </div>

        <div class="container" style="background-color:#f1f1f1">
            <button type="button" class="cancelbtn">Cancel</button>
            <span class="psw">Forgot <a href="#">password?</a></span>
        </div>
    </form>
</c:if>



