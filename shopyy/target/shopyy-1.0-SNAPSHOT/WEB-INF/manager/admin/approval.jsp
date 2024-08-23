<%-- 
    Document   : approval
    Created on : Aug 10, 2024, 4:25:36 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->
<link rel="stylesheet" href="<c:url value="/css/approval.css"/>" />



<div id="Details">
    <h3>Leave Approve</h3>
    <c:if test="${notify != null}">
        <div class="alert alert-success alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
             ${notify}
        </div>
    </c:if>
    <table style="width:100%">
        <tr>
            <th>Tài Khoản</th>
            <th>Họ</th>
            <th>Tên</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Vai trò</th>
            <th>Chờ Duyệt</th>
        </tr>
        <c:url value="/admin/approval" var="app" />
        <c:forEach items="${pendingUsers}" var="user">
            <tr><!--list--pending--user-->
                <td>${user.username}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td>${user.userRole}</td>
                <td>
                    <a class="btn btn-primary pt-0" href="${app}?userId=${user.id}&accept=true" role="button">Accept</a>
                    <a class="btn btn-warning pt-0" href= "${app}?userId=${user.id}&accept=false" role="button">Reject</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>