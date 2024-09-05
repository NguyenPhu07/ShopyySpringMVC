<%-- 
    Document   : footer
    Created on : Aug 10, 2024, 4:19:16 PM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="navbar navbar-expand-md navbar-dark " style="background-color: #fb5330;">
    <a class="navbar-brand" href="#">Shopee</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <ul class="navbar-nav mr-auto">
        <c:url value="/manager" var="index"/>
        <li class="nav-item">
            <a class="nav-link" href="${index}">Trang chủ</a>
        </li>
        <!-- Kiểm tra vai trò ADMIN -->
        <c:if test="${pageContext.request.isUserInRole('ADMIN')}">
            <li class="nav-item dropdownnav">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown">
                    Sản Phẩm
                </a>
                <div class="dropdownnav-content">
                    <a href="#">Quản Lí</a>
                    <a href="#">Tạo Mới</a>
                </div>
            </li>
            <li class="nav-item dropdownnav">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown">
                    Thống kê
                </a>
                <c:url value="/admin" var="stats"/>
                <c:url value="/stats" var="s"/>
                <div class="dropdownnav-content">
                    <a href="${stats}/frequency">Tần Suất</a>
                    <a href="${stats}/total">Tổng Sản Phẩm</a>
                    <a href="${s}/revenue">Doanh Thu Sản Phẩm</a>
                    <a href="${s}/cate">Doanh Thu Danh Mục</a>
                </div>
            </li>
        </c:if>
        <c:if test="${pageContext.request.isUserInRole('STAFF') || pageContext.request.isUserInRole('ADMIN')}">
            <c:url value="/approval" var="app"/>
            <li class="nav-item">
                <a class="nav-link" href="${app}">Chờ xét duyệt
                    <c:if test="${pendingUsers != null}">
                        <span class="badge badge-light">${pendingUsers.size()}</span>
                    </c:if>
                </a>
            </li>
        </c:if>
        <!-- Kiểm tra vai trò SELLER -->
        <c:if test="${pageContext.request.isUserInRole('SELLER')}">
            <li class="nav-item dropdownnav">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown">
                    Thống kê
                </a>
                <c:url value="/stats" var="s"/>
                <div class="dropdownnav-content">
                    <a href="${s}/revenue">Doanh Thu Sản Phẩm</a>
                    <a href="${s}/cate">Doanh Thu Danh Mục</a>
                </div>
            </li>
            <li class="nav-item">
                <c:url value="/shop" var="shop"/>
                <a class="nav-link" href="${shop}">Cửa Hàng</a>
            </li>
        </c:if>
    </ul>

    <sec:authorize access="isAuthenticated()">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <!-- Truy xuất avatar từ session bằng sessionScope mà controller đã gửi vào session ở bên dưới -->
                <img src="${sessionScope.avatar}" width="30" height="30" style="border-radius:20px; margin-top: 4px">
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">${pageContext.request.userPrincipal.name}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/logout" />">Logout</a>
            </li>
        </ul>
    </sec:authorize>

    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <c:url  value="/login" var="l"/>
                <a class="nav-link" href="${l}">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Sign Up</a>
            </li>
        </ul>
    </c:if>
</header>


