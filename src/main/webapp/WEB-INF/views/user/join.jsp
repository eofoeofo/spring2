<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
</head>
<body>
<form id="frm" action="join" method="post">
    <div>
        <input type="text" name="uid" placeholder="아이디">
    </div>

    <div>
        <input type="password" name="upw" placeholder="비밀번호">
    </div>
    <div>
        <input type="password" id="chkUpw" placeholder="비밀번호 확인">
    </div>

    <div>
        <input type="text" name="nm" placeholder="이름">
    </div>
    <div>
        <input type="submit" value="회원가입">
    </div>
</form>
</body>
</html>
