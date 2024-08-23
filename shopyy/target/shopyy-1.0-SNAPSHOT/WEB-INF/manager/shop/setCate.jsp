<%-- 
    Document   : frmAddProdIntoCate
    Created on : Aug 19, 2024, 5:32:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->


<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
<script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>


<style>
    /* Đổi màu của placeholder */
    .choices__input[placeholder] {
        color: gray !important;
    }

    /* Đổi màu khi hover hoặc focus vào các lựa chọn */
    .choices__list--dropdown .choices__item--selectable.is-highlighted {
        background-color: gray !important;
        color: white !important;
    }
</style>
<h1>Chỉnh sửa Danh Mục</h1>
<div class="row d-flex justify-content-center mt-0">
    <div class="col-md-6" >
        <c:url value="/shop/${shopId}/setCate" var="action"/>
        <form id="multiselectForm"  method="get" action="${action}" >
            <select  id="choices-multiple-remove-button" name="tags" placeholder="Select up to 5 tags" multiple  >
                <c:forEach items="${products}" var="p">
                    <option value="${p.id}">${p.name}</option>
                </c:forEach>
            </select>
        </form>
    </div>
    <button type="button" id="submitBtn" class="btn btn-primary mb-3">Submit</button>
</div>
<c:forEach items="${tags}"  var="tag" >
    <li>${tag}</li>
    </c:forEach>



<script>
            $(document).ready(function () {
                // Initialize Choices.js for multiselect
                var multipleCancelButton = new Choices('#choices-multiple-remove-button', {
                    removeItemButton: true,
                    maxItemCount: 5,
                    searchResultLimit: 10,
                    renderChoiceLimit: 10
                });

                // Handle the submit button click event
                $('#submitBtn').click(function () {
                    // Get selected optionsmultipleCancelButton
                    var selectedValues = multipleCancelButton.getValue(true); // Get the selected values as an array

                    // Construct the query string
                    var queryString = selectedValues.map(function (value) {
                        return 'tags=' + encodeURIComponent(value);
                    }).join('&');

                    // Redirect to the new URL with query parameters
                    // nhớ giải cái url trong cái script này
    <c:url value="/shop/${shopId}/setCate" var="action"/>
                    var url = '${action}?' + queryString;
                    window.location.href = url;
                });
            });
</script>
