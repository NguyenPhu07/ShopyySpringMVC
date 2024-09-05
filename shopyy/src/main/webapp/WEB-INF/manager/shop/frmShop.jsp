<%-- 
    Document   : frmShop
    Created on : Aug 30, 2024, 6:50:41 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->


<c:url value="/shop/createShop" var="action" />
<form:form method="post" action="${action}" modelAttribute="shop" enctype="multipart/form-data" accept-charset="UTF-8">
    <div class="mb-3 mt-3">
        <label for="name" class="form-label">Tên Cửa Hàng</label>
        <form:input name="name" path="name" class="form-control" id="name" placeholder="Tên sản phẩm"  />
    </div>
    <div class="mb-3 mt-3">
        <label for="description" class="form-label">Số điện thoại</label>
        <form:input  name="phone" path="phone" type="text" class="form-control" id="phone" placeholder="Số điện thoại"  />
    </div>
    <div class="mb-3 mt-3">
        <label for="address" class="form-label">Địa chỉ</label>
        <form:input  name="address" path="address" type="text" class="form-control" id="address" placeholder="Địa chỉ"  />
    </div>
    <div class="mb-3 mt-3">
        <label for="file" class="form-label">Ảnh Cửa Hàng</label>
        <form:input path="file" accept=".png,.jpg" type="file" class="form-control" id="file" name="file" />
        <c:if test="${shop.id != null}">
            <img src="${shop.image}" alt="${shop.name}" width="120" />
        </c:if>
    </div>
    <label for="active" class="form-label">Trạng Thái</label>
    <form:select  path="active" id="active" class="form-control">
        <c:choose>
            <c:when test="${shop.active == true}">
                <option value="true" selected>Mở bán</option>
                <option value="false">Ngừng bán</option>
            </c:when>
            <c:when test="${shop.active == false}">
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
                <c:when test="${shop.id != null}">
                    Cập nhật
                </c:when>
                <c:otherwise>
                    Tạo
                </c:otherwise>
            </c:choose>
        </button>
        <c:url value="/shop" var="action"/>
        <a class="btn btn-primary" href="${action}" role="button" style="margin-left:10px; padding: 7.5px;">Quay về</a>
    </div>
</form:form>


<script>
    const imageInput = document.getElementById('file');
    const imagePreview = document.getElementById('imagePreview');

    imageInput.addEventListener('change', function () {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();

            reader.onload = function (e) {
                imagePreview.style.backgroundImage = `url(${e.target.result})`;
                imagePreview.innerHTML = ''; // Xóa nội dung văn bản khi hiển thị ảnh
            }

            reader.readAsDataURL(file);
        } else {
            imagePreview.style.backgroundImage = 'none';
            imagePreview.innerHTML = 'Chưa có hình ảnh';
        }
    });
</script>