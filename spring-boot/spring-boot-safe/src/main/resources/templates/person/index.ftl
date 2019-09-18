<!DOCTYPE html>
<html>
<head>
    <title>FreeMarker Spring MVC 之 表单提交</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div>用户登录表单</div>
<form name="frmLogin" action="/person/save/v3">
    <input type="hidden" name="token" value="${token}">
    name: <input type="text" name="name"><br/>
    age: <input type="text" name="age"><br/>
    <input type="submit">
</form>
</body>
</html>