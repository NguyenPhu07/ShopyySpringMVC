<%-- 
    Document   : compareProducts
    Created on : Aug 21, 2024, 1:54:17 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->
<link rel="stylesheet" href="<c:url value="/css/compare2.css"/>" />
<div class="compare-container">
    <div class="compare-list">
        <h3>So sánh sản phẩm</h3>
        <ul>
            <!--list sản phẩm so sánh-->
            <c:forEach items="${products}" var="p"> 
                <li>${p.name}</li>
                </c:forEach>
        </ul>
    </div>

    <div class="product-compare">
        <c:forEach items="${products}" var="p"> 
            <div class="product">
                <img src="${p.image}" alt="Product 1">
                <p>${p.name}</p>
                <p><strong>${String.format("%,d", p.price)} VNĐ</strong></p>
                <button>Xem sản phẩm</button>
            </div>
        </c:forEach>
    </div>



    <div class="product-details">
        <table>

            <tr>
                <th>Thông tin chung</th>
                <!--list tên sản phẩm-->
                <th>${products[0].name}</th>
                <th>${products[1].name}</th>
                <th>${products[2].name}</th>
            </tr>
            <tr>
                <td>Thể Loại</td>
                <!--list category-->
                <td>Điện thoại</td>
                <td>Điện thoại</td>
                <td>Mở Bán</td>
            </tr>
            <tr>
                <td>Nhà Sản Xuất</td>
                <!--list manufacture-->
                <td>${products[0].manufacture}</td>
                <td>${products[1].manufacture}</td>
                <td>${products[2].manufacture}</td>
            </tr>    
            <tr>
                <td>Trạng Thái</td>
                <!--list active-->
                <c:forEach items="${products}" var="p" >
                    <c:if test="${p.active == true}" >
                        <td>Đang Mở Bán</td>
                    </c:if>
                    <c:if test="${p.active == false}" >
                        <td>Đã Ngừng Bán</td>
                    </c:if>
                </c:forEach>
            </tr>
            <tr>
                <td>Mô tả</td>
                <!--list description-->
                <td>${products[0].description}</td>
                <td>${products[1].description}</td>
                <td>${products[2].description}</td>
            </tr>
            <tr>
                <td>Cửa Hàng</td>
                <!--list descriptions-->
                <td>${products[0].shopId.name}</td>
                <td>${products[1].shopId.name}</td>
                <td>${products[2].shopId.name}</td>
            </tr>
        </table>
    </div>
</div>


