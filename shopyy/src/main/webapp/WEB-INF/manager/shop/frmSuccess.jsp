<%-- 
    Document   : frmSuccess
    Created on : Aug 19, 2024, 1:57:50 AM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->

<!--view Cate-->
<c:if test="${viewCate==true}">
    <c:if test="${errMsg == false}">
        <div class="alert alert-success alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Success!</strong> Tạo Danh Mục ${cateName} Thành Công!
        </div>
        <c:url value="/shop/${shopId}" var="action"/>
        <a class="btn btn-primary" href="${action}" role="button">Quay về Trang Chủ</a>
    </c:if>
    <c:if test="${errMsg == true}">
        <div class="alert alert-danger alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Fail!</strong> Danh mục này đã tồn tại!
        </div>
        <c:url value="/shop/${shopId}/createCate" var="action"/>
        <a class="btn btn-primary mb-3" href="${action}" role="button">Tạo Lại</a>
    </c:if>
</c:if>
<!--view Product-->
<c:if test="${viewProd==true}">
    <c:if test="${errMsg == true}">
        <div class="alert alert-success alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Success!</strong> Thao tác thành Công!
        </div>
        <c:url value="/shop/${shopId}" var="action"/>
        <a class="btn btn-primary" href="${action}" role="button">Quay về Trang Chủ</a>
    </c:if>
    <c:if test="${errMsg == false}">
        <div class="alert alert-danger alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Fail!</strong> Sản Phẩm này đã tồn tại!
        </div>
        <c:url value="/shop/${shopId}/createProd" var="action"/>
        <a class="btn btn-primary mb-3" href="${action}" role="button">Tạo Lại</a>
    </c:if>
</c:if>
