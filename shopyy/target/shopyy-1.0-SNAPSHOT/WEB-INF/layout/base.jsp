<%-- 
    Document   : base
    Created on : Aug 9, 2024, 1:36:55 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %><!--khai báo tiles-->
<!-- base sẽ là 1 trang trừu tượng có cấu trúc đầy đủ (gốm các cấu trúc trừu tượng insert Attribute title, header, content, footer)-->
<!-- đợi những thằng con kế thừa lại, cụ thể hóa cái cấu trúc content đó lại thui -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> 
            <tiles:insertAttribute name="title"/>
        </title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">      
        <link rel="stylesheet" href="<c:url value="/css/style.css"/>" />
        <link rel="stylesheet" href="<c:url value="/css/scroll.css"/>" />
        <link rel="stylesheet" href="<c:url value="/css/admin.css"/>" />
    </head>
    <body>
        <tiles:insertAttribute name="header"/>

        <div class="container">
            <tiles:insertAttribute name="content"/>
        </div>

        <tiles:insertAttribute name="footer"/>  


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


      
        

    </body>
</html>
