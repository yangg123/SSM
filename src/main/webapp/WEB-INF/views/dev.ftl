<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title></title>
    <style>
        html body {
            font-size: 12px;
            font-family: "Monaco";
        }

        .dbTableCon {
            float: left;
            width: 246px;
            border: solid 1px #d3d3d3;
            line-height: 20px;
            padding: 10px 10px;
            margin: 10px 10px;
            background-color: #2B2B2B;
        }

        .dbTableCon a {
            color: #E0BF67;
        }

        .codeCon {
            float: left;
            padding: 10px 10px;
            width: 800px;
            height: 600px;
            margin: 0px 0px;
        }

        .codeCon textarea {
            font-size: 12px;
            border: solid 1px #d3d3d3;
            width: 100%;
            height: 100%;
            background-color: #2B2B2B;
            color: #CC7832;
            padding: 10px 10px;
            font-family: "Monaco";
            line-height: 20px;
        }
    </style>
</head>
<body>
<div class="dbTableCon">
    <#list tableNames as tableName> <a href="?tn=${tableName}">${tableName}</a><br />
    </#list>
</div>
<div class="codeCon">
    <textarea>${codeStr?if_exists}</textarea>
</div>
</body>
</html>