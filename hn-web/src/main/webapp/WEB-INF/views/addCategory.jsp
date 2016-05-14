<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 13.05.2016
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:import url="header.jsp"/>
<form method="post" action="addCategory">
  <table width="30%" border="1" cellpading="3">
    <thead>
    <tr>
      <th colspan="2">Добавление категорий книг</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>Укажите название категории</td>
      <td><input type="text" name="name" value="" /></td>
    </tr>
    </tbody>
  </table>
  <c:set var="user" value="${sessionScope.user}" />
  <input type="hidden" name="id_user" value="${user.id}" />
  <input type="submit" value="Добавить категорию" />
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>
