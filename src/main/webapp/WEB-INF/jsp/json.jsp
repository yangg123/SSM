<%--
  Created by IntelliJ IDEA.
  User: yg
  Date: 17/2/17
  Time: 下午4:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

${message}

<script src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
    var saveDataAry=[];
    var data1={"userName":"test","password":"gz"};
    var data2={"userName":"ququ","password":"gr"};
    saveDataAry.push(data1);
    saveDataAry.push(data2);
    console.log("JSON-String >>>> \n" + JSON.stringify(saveDataAry));
    $.ajax({
      type:"POST",
      url:"/user/saveJson",
      dataType:"json",
      contentType:"application/json;charset=UTF-8",
      data:JSON.stringify(saveDataAry),
      success:function(data){
        console.log("JSON-String 保存成功>>>> \n" + JSON.stringify(saveDataAry));
        alert(data.success);
      }
    });
  });
</script>
</body>
</html>
