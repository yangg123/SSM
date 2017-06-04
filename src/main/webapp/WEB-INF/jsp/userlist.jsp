<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户表操作</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
      <form action="<%=basePath %>user/addUser.action" method="post">
          <table>
              <tr>
                  <td>用户名</td>
                  <td><input type="text" name="userName"></td>
              </tr>
                        
              <tr>
                  <td>年 龄</td>
                  <td><input type="text" name="age"></td>
              </tr>
              

              <tr>
                  <td><input type="submit" value="添加"></td>
                  <td><input type="reset" value="重置"></td>
              </tr>
          </table>
      </form>
      
      <br>
       
      <div class="dd">
          <table width="800px">
          <tr>
              <td width="25%">ID</td>
              <td width="20%">用户名</td>
              <td width="20%">年 龄</td>
          </tr>
          <c:forEach items="${userlist}" var="list" varStatus="status">
              <tr>
                  <td>${list.id }</td>
                  <td>${list.userName}</td>
                  <td>${list.age }</td>
              </tr>
          </c:forEach>
          </table>
      </div>
      <font size="2">共${page.totalPageCount }页</font>
      <font size="2">第${page.pageNow}页</font>
      <a href="<%=basePath %>user/getList.action?pageNow=1">首页</a>
      <c:choose>
        <c:when test="${page.pageNow - 1 > 0 }">
            <a href="<%=basePath %>user/getList.action?pageNow=${page.pageNow - 1}">上一页</a>
        </c:when>
        <c:when test="${page.pageNow - 1 <= 0 }">
            <a href="<%=basePath %>user/getList.action?pageNow=1">上一页</a>
        </c:when>
      </c:choose>
      <c:choose>
        <c:when test="${page.pageNow + 1 < page.totalPageCount }">
            <a href="<%=basePath %>user/getList.action?pageNow=${page.pageNow + 1}">下一页</a>
        </c:when>
        <c:when test="${page.pageNow + 1 >= page.totalPageCount }">
             <a href="<%=basePath %>user/getList.action?pageNow=${page.totalPageCount}">下一页</a>
        </c:when>
      </c:choose>
      <a href="<%=basePath %>user/getList.action?pageNow=${page.totalPageCount}">尾页</a>
     
  </body>
</html>
