<%-- 
    Document   : frmProduct
    Created on : Aug 19, 2024, 12:07:23 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->

<c:choose>
    <c:when test="${product.id != null}">
      <h1>Cập Nhật Sản Phẩm</h1>
    </c:when>
    <c:otherwise>
        <h1> Thêm Sản Phẩm</h1>
    </c:otherwise>
</c:choose>
<div class="badge bg-primary text-wrap"
     style="width: 10rem; margin-left: 0px; margin-top: 5px; margin-bottom: 10px; color: white;">
    Cửa Hàng: ${nsg} 
</div>
<c:url value="/shopProds" var="action" />
<form:form method="post" action="${action}" modelAttribute="product">
    <div class="mb-3 mt-3">
        <label for="name" class="form-label">Tên sản phẩm</label>
        <form:input name="name" path="name" class="form-control" id="name" placeholder="Tên sản phẩm"  />
    </div>
    <div class="mb-3 mt-3">
        <label for="description" class="form-label">Mô Tả</label>
        <form:input  name="description" path="description" type="text" class="form-control" id="description" placeholder="Mô tả"  />
    </div>
    <div class="mb-3 mt-3">
        <label for="price" class="form-label">Gía sản phẩm</label>
        <form:input name="price" path="price" type="number" class="form-control" id="price" placeholder="Gía sản phẩm"  />
    </div>
    <div class="mb-3 mt-3">
        <label for="manufacture" class="form-label">Nhà sản Xuất</label>
        <form:input  name="manufacture" path="manufacture" type="text" class="form-control" id="manufacture" placeholder="Nhà sản Xuất"  />
    </div>
    <!--trường image-->
    <div class="mb-3 mt-3">
        <label for="cate" class="form-label">Danh mục sản phẩm</label>
        <form:select  path="categoryId" id="cate" class="form-control">
            <c:forEach items="${cates}" var="c">
                <c:choose>
                    <c:when test="${c.id == product.categoryId.id}">
                        <option value="${c.id}" selected>${c.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>
    <!--image-->
    <!--active-->

    <label for="active" class="form-label">Trạng Thái</label>
    <form:select  path="active" id="active" class="form-control">
        <c:choose>
            <c:when test="${product.active == true}">
                <option value="true" selected>Mở bán</option>
                <option value="false">Ngừng bán</option>
            </c:when>
            <c:when test="${product.active == false}">
                <option value="true">Mở bán</option>
                <option value="false" selected>Ngừng bán</option>
            </c:when>  
            <c:otherwise>
                <option value="true">Mở bán</option>
                <option value="true">Ngừng bán</option>
            </c:otherwise>
        </c:choose>
    </form:select>

    <div class="mb-3 mt-3">
        <button class="btn btn-success" type="submit">
            <form:hidden path="id"/> <!-- khi submit cập nhật từ form tự động lấy id sản phẩm đó về nếu đang cập nhật-->
            <c:choose>
                <c:when test="${product.id != null}">
                    <option value="${c.id}" selected>Cập nhật sản phẩm</option>
                </c:when>
                <c:otherwise>
                    Thêm sản phẩm
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>