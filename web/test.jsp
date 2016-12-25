<%--
  Created by IntelliJ IDEA.
  User: Archibald
  Date: 12/12/2016
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>--%>
    <title>Title</title>

    <%
        request.setAttribute("aaa","I'm is jsp %");

    %>
    <script type="text/javascript">
        alert("${aaa}"+"qqq");
    </script>
</head>
<body>
年:<select>
    <c:forEach var="i" begin="1994" end="2016">
        <option value="${i}">${i}year</option>
    </c:forEach>
</select>
月:<select>
    <c:forEach var="i" begin="1" end="12">
        <option value="${i}">${i}month</option>
    </c:forEach>
</select>
日:<select>
    <c:forEach var="i" begin="1" end="30">
        <option value="${i}">${i}day</option>
    </c:forEach>
</select>
</body>
</html>
