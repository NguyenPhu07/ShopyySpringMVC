<%-- 
    Document   : seller
    Created on : Aug 9, 2024, 3:00:49 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->
<style>
    .modal {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        z-index: 1; /* Sit on top */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        background-color: rgba(127, 127, 127, 0.8); /* #7f7f7f with opacity */
    }

</style>
<h1>${nsg}</h1>
<h1>${hello}</h1>
<h1>${errMsg}</h1>
<c:if test="${msgDelete == true}">
    <div class="alert alert-success alert-dismissible">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Thành Công!</strong> Xóa Thành Công.
    </div>
</c:if>
<c:if test="${msgDelete == false}">
    <div class="alert alert-success alert-dismissible">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Thất Bại!</strong> Xóa Thất Bại.
    </div>
</c:if>

<link rel="stylesheet" href="<c:url value="/css/shop.css"/>" />
<div class="container mt-4">
    <div class=" row shop-info-container ">
        <!-- Shop Avatar -->
        <img src="https://down-bs-vn.img.susercontent.com/vn-11134004-7r98o-lwlml445rt97e2_tn.webp"
             alt="Shop Avatar" style="border-radius: 50px;">
        <!-- Shop Information -->
        <div class="shop-details">
            <h1>${shop.name}</h1>
            <h5>Địa chỉ: <span class="font-weight-bold">${shop.address}</span></h5>
            <div class="shop-stats">
                <span class="stat">Sản Phẩm: <span class="font-weight-bold">12</span></span>
                <span class="stat">Chủ sỡ Hữu: <span class="font-weight-bold">${username}</span></span>
                <span class="stat"><a href="#" class="text-decoration-none">Đánh giá:</a> <span
                        class="font-weight-bold">30</span></span>
            </div>
        </div>
    </div>
</div>


<!--Sắp xếp-tạo sản phẩm --cập--sản phẩm-->
<div class="container mt-4">
    <div class="badge bg-primary text-wrap"
         style="width: 10rem; margin-left: auto; margin-top: 5px; margin-bottom: 10px; color: white;">
        Quản Lí Sản Phẩm
    </div>
    <div class="row">
        <!-- Sidebar -->

        <div class="col-md-2">
            <ul class="list-group">
                <c:url value="/shop/${nsg}" var="s" />
                <li class="list-group-item active">
                    <a href="${s}" class="text-decoration-none text-decoration-none" style="color: white">Danh Mục</a>
                </li>
                <!--list--cate-->
                <c:url value="/shop/${nsg}/cate" var="cate" />
                <c:forEach items="${cateShop}" var="c">
                    <li class="list-group-item">
                        <a href="${cate}?cateId=${c.id}" class="text-decoration-none text-dark">${c.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <!-- Main content -->
        <div class="col-md-10">
            <!-- Sort and Filter -->
            <div class="d-flex justify-content-between mb-3">
                <div class="btn-group" role="group">
                    <c:url value="/shop/${nsg}/createProd" var="p" />
                    <a href="${p}" class="btn btn btn-outline-danger" role="button">Đăng Bán</a>
                    <c:url value="/shop/${nsg}/createCate" var="c" />
                    <a href="${c}" class="btn btn btn-outline-danger" role="button">Tạo Danh Mục</a>
                </div>
                <form class="form-inline" action="#">
                    <label for="fitter" class="visually-hidden"></label>
                    <input type="text" id="fitter" name="prod" class="form-control mr-0 w-74" type="search"
                           placeholder="Tìm kiếm sản phẩm...">
                    <button class="btn btn-light " type="submit"
                            style="background-color: #fb5330; color: white;">Tìm Kiếm</button>
                </form>


                <div class="nav-item dropdownnav " style="background-color: #fb5330; border-radius: 4px;">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
                       style="color: white;">
                        Sắp xếp Giá
                    </a>
                    <c:url value="/shop/${nsg}/price" var="p" />
                    <div class="dropdownnav-content">
                        <a href="${p}?nPrice=14000000&above=1">Trên 14tr</a>
                        <a href="${p}?nPrice=14000000&above=0">Dưới 14tr</a>
                    </div>
                </div>
            </div>
            <!-- Product List -->
            <div class="container mt-3">
                <div class="row">
                    <!-- Product Card 1 -->
                    <c:forEach items="${prodShop}" var="p">
                        <!-- Product Card 1 -->
                        <div class="col-md-3 col-sm-6 mb-4">
                            <div class="product-card">
                                <img src="https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248652/dkeolz3ghc0eino87iec.jpg"
                                     alt="Product Image 1">
                                <h6>
                                    ${p.name}
                                    <c:if test="${p.active == true}" >
                                        <span class="badge badge-pill badge-warning label ">Đang Mở Bán</span> 
                                    </c:if>
                                    <c:if test="${p.active == false}" >
                                        <span class="badge badge-pill badge-warning label ">Đã Ngừng Bán</span> 
                                    </c:if>
                                </h6>
                                <p class="price">
                                    ${String.format("%,d", p.price)} VNĐ</p>
                                <h6>${p.manufacture}</h6>
                                <div class="d-flex justify-content-between mt-2 mb-3">
                                    <button class="btn btn-primary btn-sm" onclick="document.getElementById('${p.id}').style.display = 'block'">Xóa</button>
                                    <!-- Modal -->
                                    <div id="${p.id}" class="modal">
                                        <div class="modal-dialog modal-confirm">
                                            <div class="modal-content">
                                                <div class="modal-header flex-column">
                                                    <h4 class="modal-title w-100">Bạn có chắc xóa?</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <p>Bạn thực sự muốn xóa sản phẩm ${p.name} ? Quá trình này không thể khôi phục được!</p>
                                                </div>
                                                <div class="modal-footer justify-content-center">
                                                    <button type="button" class="btn btn-secondary" onclick="document.getElementById('${p.id}').style.display = 'none'">Hủy</button>
                                                     <c:url value="/shop/${nsg}/${p.id}/deleteProd" var="d" />
                                                    <a href="${d}" class="btn btn-danger"style="font-size: 12px" >Xóa</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <c:url value="/shop/${nsg}/${p.id}/updateProd" var="u" />
                                    <a href="${u}" class="btn btn-success btn-sm  "style="font-size: 12px" >Cập Nhật</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Pagination -->
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li class="page-item"><a class="page-link" href="#" style="color: #fb5330;">«</a></li>
                    <li class="page-item"><a class="page-link" href="#" style="color: #fb5330;">1</a></li>
                    <li class="page-item"><a class="page-link" href="#" style="color: #fb5330;">»</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>












