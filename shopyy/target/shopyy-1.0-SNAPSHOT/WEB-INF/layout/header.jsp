<%-- 
    Document   : header
    Created on : Aug 9, 2024, 1:37:01 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="bg-custom-header text-white">
    <div class="container-fluid mt-3">
        <div class="row align-items-center py-3">
            <div class="col-md-3">
                <h1 class="mb-0" style="margin-left: 90px;">Shopee</h1>
            </div>
            <div class="col-md-6">
                <c:url  value="/products" var="p"/>
                <form class="form-inline" action="${p}">
                    <input class="form-control mr-sm-2 w-75" name="nameProd" type="search" placeholder="Tìm kiếm sản phẩm...">
                    <button class="btn btn-light" type="submit">Tìm kiếm</button>
                </form>
            </div>
            <div class="col-md-3 ">
                <a href="#" class="text-white mr-3">Đăng kí</a>
                <a href="#" class="text-white mr-3">Đăng nhập</a>
            </div>
        </div>
    </div>
</header><!--header-->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Danh mục</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Khuyến mãi</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Liên hệ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Giỏ hàng</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Tạo cửa hàng</a>
                </li>
                <!--gộp lại thành 1 mục là Doanh Thu-->
                 <c:url value="/stats" var="stats"/>
                <li class="nav-item">
                    <a class="nav-link" href="${stats}/revenue">Doanh Thu Sản Phẩm</a>
                </li>       <li class="nav-item">
                    <a class="nav-link" href="${stats}/cate">Doanh Thu Danh Mục </a>
                </li>       

            </ul>
        </div>
    </div>
</nav>


