<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
</head>
<body>
<h1>로그인</h1>
<div>${requestScope.errMsg}</div>
    <form action="login" method="post">
        <div>
              <input type="text" name="uid" placeholder="ID">
        </div>

        <div>
              <input type="password" name="upw" placeholder="PASSWORD">
        </div>

        <div>
              <input type="submit" value="LOGIN">
        </div>
    </form>
</body>
</html>
