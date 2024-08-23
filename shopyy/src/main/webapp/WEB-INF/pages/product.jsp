<%-- 
    Document   : product
    Created on : Aug 9, 2024, 3:02:12 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->
<link rel="stylesheet" href="<c:url value="/css/compare.css"/>" />
<div class="container mt-3">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-2">
            <div class="dropdown">
                <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false" style="background-color: #fb5330; color: white;">
                    Cửa Hàng
                </button>
                <c:url value="/products/shop" var="sop" />
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <c:forEach items="${shops}" var="s">
                        <a class="dropdown-item" href="${sop}?sop=${s.id}">${s.name}</a>
                        <!-- Add more items as needed -->
                    </c:forEach>

                </div>
            </div>
        </div>
        <!-- Main content -->
        <div class="col-md-10">
            <!-- Sort and Filter -->
            <div class="d-flex justify-content-between mb-3">
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-outline-danger">Phổ biến</button>
                    <button type="submit" class="btn btn-outline-danger">Tìm Kiếm</button>
                </div>

                <div class="dropdown">
                    <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false" style="background-color: #fb5330; color: white;">
                        Danh Mục
                    </button>
                    <c:url value="/products/cate" var="cate" />
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <c:forEach items="${categories}" var="c">
                            <a class="dropdown-item" href="${cate}?cateId=${c.id}">${c.name}</a>
                            <!-- Add more items as needed -->
                        </c:forEach>
                    </div>
                </div>

                <div class="nav-item dropdownnav " style="background-color: #fb5330; border-radius: 4px;">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button"
                       data-toggle="dropdown" style="color: white;">
                        Sắp xếp Gía
                    </a>
                    <c:url value="/products/fitlerPrice" var="price" />
                    <div class="dropdownnav-content">
                        <!--gắn query param vào href-->
                        <a href="${price}?price=14000000&above=1&pages=${p}">Trên 14tr</a>
                        <a href="${price}?price=14000000&above=0&pages=${p}">Dưới 14tr</a>
                    </div>
                </div>
            </div>
            <!-- Product List -->
            <div class="container mt-3">
                <div class="row">
                    <c:forEach items="${prods}" var="p">
                        <!-- Product Card 1 -->
                        <div class="col-md-3 col-sm-6 mb-4">
                            <div class="product-card">
                                <img src="https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248652/dkeolz3ghc0eino87iec.jpg"
                                     alt="Product Image 1">
                                <h6>
                                    ${p.name}
                                    <c:if test="${p.active==false}">
                                        <span class="badge badge-pill badge-warning label ">Ngưng Bán</span>
                                    </c:if>
                                </h6>
                                <p class="price">${String.format("%,d", p.price)} VNĐ</p>
                                <h6>${p.manufacture}</h6>
                                <div class="d-flex justify-content-between mt-2 mb-2">
                                    <a href="product-details1.html" class="btn btn-primary btn-sm">Chi tiết</a>
                                    <c:if test="${p.active==true}">
                                        <a href="order1.html" class="btn btn-success btn-sm ">Đặt hàng</a>
                                    </c:if>
                                </div>
                                <button  onclick="addToCompare('${p.id}', '${p.name}')">So sánh</button>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Pagination -->
            <c:url value="/products" var="url" /> 
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${p>1}">
                        <li class="page-item">
                            <a class="page-link" href="${url}?pages=${p-1}" style="color: #fb5330;">«</a>
                        </li>
                    </c:if>
                    <c:if test="${p==1}">
                        <li class="page-item">
                            <a class="page-link" href="${url}?pages=1" style="color: #fb5330;">«</a>
                        </li>
                    </c:if>
                    <li class="page-item"><a class="page-link" href="${url}?pages=1" style="color: #fb5330;">1</a></li>
                    <li class="page-item"><a class="page-link" href="${url}?pages=5" style="color: #fb5330;">5</a></li>
                        <c:if test="${p<5}">
                        <li class="page-item">
                        <li class="page-item"><a class="page-link" href="${url}?pages=${p+1}" style="color: #fb5330;">»</a></li>
                        </li>
                    </c:if>
                    <c:if test="${p==5}">
                        <li class="page-item">
                            <a class="page-link" href="${url}?pages=5" style="color: #fb5330;">»</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>
</div>
<!-- Chỗ chứa danh sách so sánh -->
<div id="compareListContainer" class="compare-list-container expanded">
    <div class="compare-list" id="compareList">
        <!-- Các sản phẩm được thêm vào so sánh sẽ hiển thị ở đây -->
    </div>
    <div class="compare-actions">
        <button onclick="submitComparison()">So sánh ngay</button>
        <button onclick="clearCompareList()">Xóa tất cả sản phẩm</button>
    </div>
</div>

<!-- Nút thu gọn -->
<button id="toggleButton" class="compare-toggle" onclick="toggleCompareList()">
    <span class="icon"></span>
    <span class="text">So sánh (2)</span>
</button>

<script src="<c:url value="/JS/compare.js" />"></script> 

