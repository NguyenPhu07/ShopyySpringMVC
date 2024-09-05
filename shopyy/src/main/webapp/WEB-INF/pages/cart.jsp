<%-- 
    Document   : cart
    Created on : Sep 5, 2024, 12:28:31 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-danger">Giỏ Hàng</h1>

<c:if test="${msg != nul}">
    <div class="alert alert-success alert-dismissible">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Thành Công!</strong> ${msg}
    </div>
</c:if>
<table class="table">
    <tr>
        <th>Mã Sản Phẩm</th>
        <th>Tên Sản Phẩm</th>
        <th>Đơn giá</th>
        <th>Số lượng</th>
        <th>Tăng</th>
        <th>Giảm</th>
        <th>Xóa</th>
        <th>Tổng</th>
    </tr>
    <c:forEach items="${carts}" var="c">
        <tr id="product-${c.id}">
            <th>${c.id}</th>
            <th>${c.name}</th>
            <th>${String.format("%,d", c.price)}đ</th>
            <th ><span class="quantity-${c.id}">${c.quantity}</span></th>
                <c:url value="/api/addProduct/{productId}" var="insc"/>
            <th><button type="button" class="btn btn-danger" onClick="inscProduct(${c.id})">+</button></th>
                <c:url value="/api/reduceProduct/{productId}" var="desc"/>
            <th> <button type="button" class="btn btn-danger" onClick="descProduct(${c.id})">-</button></th>
                <c:url value="/api/removeCart/{productId}" var="rev"/>
            <th><button type="button" class="btn btn-danger" onClick="removeProduct(${c.id})">x</button></th>
            <th id="total-${c.id}">${String.format("%,d", c.price*c.quantity)}đ</th>
        </tr>
    </c:forEach>
</table>
<!-- Hiển thị tổng tiền của tất cả các sản phẩm -->
<c:if test="${total == null}">
    <p><strong>Tổng tiền giỏ hàng: </strong><span id="grand-total">0đ</span></p>
</c:if>
<c:if test="${total != null}">
    <p><strong>Tổng tiền giỏ hàng: </strong><span id="grand-total">${String.format("%,d", total)}đ</span></p>
</c:if>

<c:url value="/cart/pay" var="p" />
<a href="${p}" class="btn btn-danger btn-sm mb-3">Thanh Toán </a>