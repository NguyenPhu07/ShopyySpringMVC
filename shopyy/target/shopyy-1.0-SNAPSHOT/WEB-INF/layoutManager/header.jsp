<%-- 
    Document   : footer
    Created on : Aug 10, 2024, 4:19:16 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="navbar navbar-expand-md navbar-dark " style="background-color: #fb5330;">
    <a class="navbar-brand" href="#">Shopee</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav mr-auto">
            <c:url value="/admin" var="index"/>
            <li class="nav-item">
                <a class="nav-link" href="${index}">Trang chủ</a>
            </li>
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
                <div class="dropdownnav-content">
                    <a href="${stats}/frequency">Tần Suất</a>
                    <a href="${stats}/total">Tổng Sản Phẩm</a>
                </div>
            </li>
            <c:url value="/admin/approval" var="app"/>
            <li class="nav-item">
                <a class="nav-link" href="${app}">Chờ xét duyệt
                    <c:if test="${pendingUsers != null}">
                        <span class="badge badge-light">${pendingUsers.size()}</span>
                    </c:if>
                </a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Sign Up</a>
            </li>

        </ul>
    </div>
</header>

