<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>하이</title>
</head>
<body>
<div>
    <div>
        <form action="result" method="post">
        연도 :
        <select name="deal_year">
            <c:forEach begin="2000" end="2020" var="year">
                <option value="${year}">${year}년</option>
            </c:forEach>
        </select>

        월 :
        <select name="deal_month">
            <c:forEach begin="1" end="12" var="mon">
                <option value="${mon}">${mon}월</option>
            </c:forEach>
        </select>

        지역 : 대구시
        <select name="extrn_area_code">
            <c:forEach items="${requestScope.locationList}" var="item" >
                <option value="${item.extrn_area_code}">${item.area_name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="검색">
        </form>
    </div>
</div>
</body>
</html>
