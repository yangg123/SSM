<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>上传文件测试页面</title>
</head>
<body>
    <form action="/doUpload" method="post" enctype="multipart/form-data">
        <input type="file" name="file"><br>
        <input type="submit" value="上传"><br>
    </form>
</body>
</html>