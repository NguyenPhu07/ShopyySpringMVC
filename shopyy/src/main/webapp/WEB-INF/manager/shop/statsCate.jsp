<%-- 
    Document   : statsCate
    Created on : Aug 15, 2024, 5:09:10 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--khai bao url tu dong noi contextPath-->
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> <!--khai bao form-->
<%-- 
    Document   : statsTotal
    Created on : Aug 13, 2024, 6:02:05 PM
    Author     : Admin
--%>

<h1>Thống kê Danh Mục</h1>
<div class="badge bg-primary text-wrap"
     style="width: 10rem; margin-left: 0px; margin-top: 5px; margin-bottom: 10px; color: white;">
    Year: ${selectedYear}
</div>
<!--Thêm điều kiện-->
<div class="badge bg-primary text-wrap"
     style="width: 10rem; margin-left: 0px; margin-top: 5px; margin-bottom: 10px; color: white;">
    ${selectedType}
</div>
<div>
    <form id="time-form" method="GET" action="<c:url value='/stats/cate' />">
        <label for="year-select">Chọn năm:</label>
        <select id="year-select" name="year">
            <!-- Options sẽ được thêm qua JavaScript -->
        </select>
        <label for="time-period">Chọn thời gian:</label>
        <select id="time-period" name="type">
            <option value="">Chọn thời gian</option>
            <option value="month">Tháng</option>
            <option value="quarter">Quý</option>
        </select>

        <button type="submit">Submit</button>
    </form>

</div>

<div class="row">
    <c:if test="${not empty cates}">
        <div class="col-md-5 col-12">
            <table class="table">
                <tr>
                    <th> ${selectedType}</th>
                    <th>Danh Mục</th>
                    <th>Doanh Thu</th>
                </tr>
                <c:forEach items="${cates}" var="r">
                    <tr>
                         <td>${r[1]}</td>
                        <td>${r[3]}</td>
                        <td>${String.format("%,d", r[4])} </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="col-md-7 col-12">
            <canvas id="myChart"></canvas>
        </div>
    </c:if>

    <c:if test="${empty cates}">
        <p>Không có dữ liệu thống kê cho thời gian đã chọn.</p>
    </c:if>

</div>


<script src="<c:url value="/JS/stats.js" />"></script> 
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>


    let labels = [];
    let data = [];
    <c:forEach items="${cates}" var="r">
    labels.push('${r[3]}');
    data.push(${r[4]});
    </c:forEach>

    window.onload = function () {
        const ctx = document.getElementById('myChart');

        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: labels,
                datasets: [{
                        label: '# Danh Mục',
                        data: data,
                        borderWidth: 1
                    }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

    }

</script>
