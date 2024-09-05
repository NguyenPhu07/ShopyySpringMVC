<%-- 
    Document   : frmDanhMuc
    Created on : Aug 19, 2024, 12:04:33 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->

<h1>Thêm Danh Mục </h1>
<div class="badge bg-primary text-wrap"
     style="width: 10rem; margin-left: 0px; margin-top: 5px; margin-bottom: 10px; color: white;">
    Cửa Hàng: ${nsg} 
</div>

<c:url value="/shop/shopCate" var="action" />
<form:form  method="post" action="${action}" modelAttribute="category" >
    <div class="form-group">
        <lable for="name"> Danh mục</lable>
            <form:input type="text" id="name" path="name" class="form-control"/> 
        <!--path tương ướng vs tên trường firstName của User pojo -->
    </div>
    <div class="form-group mt-3">
        <lable for="description"> Mô tả</lable>
            <form:input type="text" id="=description" path="description" class="form-control" />
    </div>
    <input type="submit" value="Tạo Danh Mục" class="btn-success mb-3" />
    <c:url value="/shop/${shopId}" var="action"/>
    <a class="btn btn-primary" href="${action}" role="button" style="margin-left: 800px">Quay về Trang Chủ</a>
</form:form>







