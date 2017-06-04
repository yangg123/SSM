<%--
  Created by IntelliJ IDEA.
  User: yg
  Date: 2017/4/7
  Time: 上午11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>实现搜索框智能提示</title>
    <style type="text/css">
        #mydiv {
            position: absolute;
            left:50%;
            top:50%;
            margin-left:-200px;
            margin-top:-50px;
        }
        .mouseOver{
            background:#708090;
            color:#FFFAFA;
        }

        .mouseOut{
            background:#FFFAFA;
            color:#000000;
        }

    </style>

    <script type="text/javascript">

        var xmlHttp;
        // 获取用户输入内容的关联信息函数
        function getMoreContents() {
            //首先要获取用户的输入
            var content = document.getElementById("keyword");
            if(content.value == ""){
                cleatContent();
                return;
            }

            //然后要给服务器发送用户输入的内容,因为我们采用的是ajax异步发送数据功能
            //所以我们要使用一个对象，叫做xmlhttp对象
            xmlHttp = createXmlHttp();
            //要给服务器发送数据
            var url = "user/search/?keyword="+content.value;
            xmlHttp.open("GET",url,true); //true:异步
            xmlHttp.onreadystatechange=callback;
            xmlHttp.send(null);
        }

        // 获取xmlHttp对象
        function createXmlHttp() {
            var xmlHttp;
            if(window.XMLHttpRequest){ // 对于大不多浏览器都适用
                xmlHttp = new XMLHttpRequest();
            }
            if(window.ActiveXObject){ //要考虑浏览器的兼容性
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                if(!xmlHttp)
                    xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            }
            return xmlHttp;
        }


        function callback() {
            if (xmlHttp.readyState==4) {
                if (xmlHttp.status==200){
                    var result = xmlHttp.responseText;
                    // 解析获得的数据
                    var json = eval("("+result+")");
                    // 获取数据后，动态显示到下拉框
                    alert(json)
                    setContent(json);
                }
            }
        }

         //参数代表服务端传过来的数据
        function setContent(content) {
            cleatContent();
            setLocation();
            var size = content.length();
            for (var  i=0;i<size;i++) {
                var nextNode=content[i];
                var tr = document.createElement("tr");
                var td = document.createElement("td");
                td.setAttribute("bgcolor","0");
                td.setAttribute("bgcolor","FFFAFA");
                td.onmouseover=function () {
                    this.className='mouseOver';
                };
                td.onmouseout=function () {
                    this.className='mouseOut';
                };
                td.onclick=function () {
                    // 当鼠标点击关联数据，自动设置为输入框的数据
                };
                var text = document.createTextNode(nextNode);
                td.appendChild(text);
                tr.appendChild(td);
                document.getElementById("content_table_body").appendChild(tr);
            }
        }

        // 清空之前的数据
        function cleatContent() {
            var contentTableBody = document.getElementById("content_table_body");
            var size = contentTableBody.childNodes.length;
            for (var i=size-1;i>=0;i--){
                contentTableBody.removeChild(contentTableBody.childNodes[i]);
            }

            document.getElementById("popDiv").style.border="none";
        }

        function keywordBlur() {
            cleatContent();
        }

        // 设置显示关联信息的位置
        function setLocation() {
            var content = document.getElementById("keyword");
            var width = content.offsetWidth;
            var left = content["offsetLeft"];
            var top = content["offsetTop"] + content.offsetHeight;
            var popDiv = document.getElementById("popDiv");
            popDiv.style.border="black 1px solid";
            popDiv.style.left = left+"px";
            popDiv.style.top = top+"px";
            popDiv.style.width = width+"px";
            document.getElementById("content_table").style.width=width+"px";
        }

    </script>
</head>
<body>
<div id="mydiv">
    <input type="text" size="50" id="keyword" onkeyup="getMoreContents()" onblur="keywordBlur()" onfocus="getMoreContents()">
    <input type="button" value="百度一下" width="50px">
    <!-- 下面是内容的展示区域-->
    <div id="popDiv">
        <table id="content_table" bgcolor="#FFFAFA" border="0" cellpadding="0" cellspacing="0">
            <tbody id="content_table_body">
            <tr><tfd>ajax1</tfd></tr>
            <tr><tfd>ajax2</tfd></tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
