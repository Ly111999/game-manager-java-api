<%--
  Created by IntelliJ IDEA.
  User: Huong Ly
  Date: 7/18/2019
  Time: 10:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .header {
            background-color: aquamarine;
            width: 100%;
            height: 100px;
        }

        .footer {
            background-color: aquamarine;
            width: 100%;
            height: 100px;
        }

        .left-bar {
            float: left;
            width: 20%;
            background: blueviolet;
        }

        .main-content {
            float: left;
        }

        .main:after {
            content: " ";
            clear: both;
            display: table;
        }
    </style>
</head>
<body>
<div class="header">
    This is header
</div>
</body>
</html>
