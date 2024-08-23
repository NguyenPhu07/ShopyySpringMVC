<%-- 
    Document   : index
    Created on : Aug 5, 2024, 7:42:01 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->


<!--slide--show----------------------->
<div class="slideshow-container">
    <div class="mySlides fade">
        <img src="https://cf.shopee.vn/file/vn-11134258-7r98o-lygfmvwtyvtta1_xxhdpi" style="width:100%">
    </div>
    <div class="mySlides fade">
        <img src="https://cf.shopee.vn/file/sg-11134258-7rdvz-lyg8z3gakefbd1_xxhdpi" style="width:100%">
    </div>

    <div class="mySlides fade">
        <img src="https://cf.shopee.vn/file/sg-11134258-7rdw2-lygdsl8cl3zo30_xxhdpi" style="width:100%">
    </div>
</div>
<br>
<div style="text-align:center">
    <span class="dot"></span>
    <span class="dot"></span>
    <span class="dot"></span>
</div>
<!--slide--show---------------------------->

<!--auto--scroll-category------------------>
<div class="badge bg-primary text-wrap"
     style="width: 6rem; margin-left: 510px; margin-top: 5px; margin-bottom: 10px; color: white;">
    Danh mục
</div>
<div class="d-flex justify-content-center">
    <div class="scroller" data-speed="fast">
        <ul class="tag-list scroller__inner">
            <c:forEach items="${categories}" var="c">
                <li class="nav-item">
                    <a class="nav-link" href="#" style="color: black;">${c.name}</a>
                </li>
            </c:forEach>

        </ul>
    </div>
</div>
<!--auto--scroll---category----------------------->

<!--list-scroll--horizontal------------------------>
<div class="badge bg-primary text-wrap"
     style="width: 10rem; margin-left: 475px; margin-top: 5px; margin-bottom: 10px; color: white;">
    Sản phẩm nổi bật
</div>

<div class="container mt-3 mb-3">
    <div class="scrolling-wrapper">
        <!-- Product Card 1 -->
        <div class="product-card-scroll">
            <img src="https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248652/dkeolz3ghc0eino87iec.jpg" alt="Product Image 1">
            <h5>Xịt khoáng dưỡng da cấp ẩm</h5>
            <p class="price">999.999₫</p>
            <a href="product-details1.html" class="btn btn-primary btn-sm">Chi tiết</a>
            <a href="order1.html" class="btn btn-success btn-sm">Đặt hàng</a>
        </div>
    </div>
</div>

<!------------------------------------------------>
<script src="<c:url value="/JS/script.js" />"></script> 
<script src="<c:url value="/JS/scroll.js" />"></script> 
