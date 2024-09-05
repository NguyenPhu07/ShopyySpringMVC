<%-- 
    Document   : listShop
    Created on : Aug 24, 2024, 3:23:48 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->

<div class="container mt-3">
    <h1>Quản Lý Cửa Hàng</h1>
    <div class="btn-group" role="group">
        <c:url value="/shop/createShop" var="crS" />
        <a href="${crS}" class="btn btn btn-outline-danger" role="button">Tạo Cửa Hàng</a>
    </div>
</div>
<c:if test="${msgShop != null}">
    <c:if test="${msgShop == 1}">
        <div class="alert alert-success alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            Thao tác Thành Công!
        </div>
    </c:if>
    <c:if test="${msgShop == 2}">
        <div class="alert alert-danger alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            Cửa Hàng này đã tồn tại!
        </div>
    </c:if>
</c:if>



<c:forEach items="${shops}" var="shop" >
    <div class="container mt-3 mb-3">
        <div class="card mt-3">
            <div class="card-header">
                <h5 class="card-title font-weight-bold">${shop.name}</h5>
            </div>
            <div class="card-body d-flex">
                <div class="flex-grow-1">
                    <p class="card-title font-weight-bold">Địa chỉ: <span class="font-weight-bold">${shop.address}</span></p>
                    <div class="mb-3">
                        <span class="stat">Sản Phẩm: <span class="font-weight-bold">12</span></span>
                        <span class="stat">Chủ sỡ Hữu: <span class="font-weight-bold">${shop.userId.username}</span></span>
                        <span class="stat"><a href="#" class="text-decoration-none">Đánh giá:</a> <span class="font-weight-bold">30</span></span>
                        <br/>
                        <c:if test="${shop.active==true}">
                            <span class="stat"><a href="#" class="text-decoration-none">Trạng Thái:</a> <span class="font-weight-bold">Đang Kinh Doanh</span></span>
                        </c:if>
                        <c:if test="${shop.active==false}">
                            <span class="stat"><a href="#" class="text-decoration-none">Trạng Thái:</a> <span class="font-weight-bold">Ngừng Kinh Doanh</span></span>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <c:url value="/shop/${shop.id}" var="sId" />
                        <a href="${sId}" class="btn btn-primary">Chi tiết</a>
                        <c:url value="/shop/${shop.id}/updateShop" var="uId" />
                        <a href="${uId}" class="btn btn-success">Cập Nhật</a>
                        <a href="#" class="btn btn-danger">Xóa</a>
                    </div>
                </div>
                <img src="${shop.image}" class="card-img-right" alt="..." style="width: 150px;">
            </div>
        </div>
    </div>
</c:forEach>

