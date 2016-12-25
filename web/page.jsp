<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 12/14/2016
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <script>
        function next(p) {
            var mm=document.getElementById("page");
            var frist1=document.getElementById("frist");
            var up1=document.getElementById("up");
            var down1=document.getElementById("down");
            var end1=document.getElementById("end");
            mm.value=p;
            document.forms[0].submit();
        }
        function onloadf() {
            var frist1=document.getElementById("frist");
            var up1=document.getElementById("up");
            var down1=document.getElementById("down");
            var end1=document.getElementById("end");
            var p="${curpage}";
            if(p<=1){
                frist1.disabled=true;
                up1.disabled=true;
            }else {
                frist1.disabled=false;
                up1.disabled=false;
            }
            if(p>='${allpage}'){
                down1.disabled=true;
                end1.disabled=true;
            }else {
                down1.disabled=false;
                end1.disabled=false;
            }
        }
    </script>
</head>
<body onload="onloadf()">
<form action="ShowByPage" method="post">
    curpage:${curpage}<br/>
    allpage:${allpage}<br/>
<table>

    <tr>
        <td>phone</td>
        <td>password</td>
        <td>status</td>
        <td>email</td>
    </tr>
    <c:forEach var="i" items="${Users}">
        <tr>
        <td>${i.phone}</td>
        <td>${i.password}</td>
        <td>${i.status}</td>
        <td>${i.emailAddress}</td>
        </tr>
    </c:forEach>
    <tr>
        <td><input id="frist" disabled="disabled" type="button" onclick="next(1)" value="frist"></td>
        <td><input id="up" disabled="disabled" type="button" onclick="next(${curpage}-1)" value="up"></td>
        <td><input id="down" type="button" onclick="next(${curpage}+1)" value="down"></td>
        <td><input id="end" type="button" onclick="next(${allpage})" value="end"></td>
    </tr>
    <tr>
        <td colspan="4">
            <input type="hidden" id="page" name="page">
        </td>
    </tr>
    <tr>
        <td colspan="4">
            ${curpage} page/${allpage} page
        </td>
    </tr>
</table>
</form>
</body>
</html>
