<%-- 
    Document   : frmShop
    Created on : Aug 10, 2024, 4:59:32 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->

<h2>Đăng ký Buôn Bán</h2>
<c:if test="${errMsg == true}">
    <div class="alert alert-success alert-dismissible">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Success!</strong> Indicates a successful or positive action.
    </div>
</c:if>
<c:url value="/register" var="action"/>
<form:form  method="post" action="${action}" modelAttribute="seller" >
    <div class="form-group">
        <lable for="firstName"> FirstName</lable>
            <form:input type="text" id="firstName" path="firstName" class="form-control"/> 
        <!--path tương ướng vs tên trường firstName của User pojo -->
    </div>
    <div class="form-group">
        <lable for="lastName"> LastName</lable>
            <form:input type="text" id="=lastName" path="lastName" class="form-control" />
    </div>
    <div class="form-group">
        <lable for="email"> email</lable>
            <form:input type="email" id="email" path="email" class="form-control" />
    </div>
    <div class="form-group">
        <lable for="phone"> phone</lable>
            <form:input type="number" id="phone" path="phone" class="form-control" />
    </div>
    <div class="form-group">
        <lable for="username"> Username</lable>
            <form:input type="text" id="username" path="username" class="form-control" />
    </div>   
    <div class="form-group">
        <lable for="password"> Password</lable>
            <form:input type="password" id="password" path="password" class="form-control" />
    </div>
    <input type="submit" value="Đăng ký" class="btn-danger mb-3" href="${action}" />
</form:form>

<!--    <form class="row g-3">
        <div class="col-md-6">
            <label for="inputEmail4" class="form-label">FirstName</label>
            <input type="email" class="form-control" id="inputEmail4">
        </div>
        <div class="col-md-6">
            <label for="inputPassword4" class="form-label">LastName</label>
            <input type="password" class="form-control" id="inputPassword4">
        </div>
         <div class="col-md-6">
            <label for="inputEmail4" class="form-label">Email</label>
            <input type="email" class="form-control" id="inputEmail4">
        </div>
        <div class="col-6">
            <lable for="phone"> phone</lable>
                <input type="number" id="phone" class="form-control" />
        </div>
         <div class="col-md-12">
            <label for="inputEmail4" class="form-label">UserName</label>
            <input type="email" class="form-control" id="inputEmail4">
        </div>
        <div class="col-md-6">
            <label for="inputPassword4" class="form-label">Password</label>
            <input type="password" class="form-control" id="inputPassword4">
        </div>
        <div class="col-md-6">
            <label for="inputPassword4" class="form-label">Confirm Password</label>
            <input type="password" class="form-control" id="inputPassword4">
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Sign in</button>
        </div>
    </form>-->