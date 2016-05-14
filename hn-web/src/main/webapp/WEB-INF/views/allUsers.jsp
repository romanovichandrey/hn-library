<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 13.05.2016
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="user" value="${sessionScope.user}" />
<html>
<head>
  <title>allBooks</title>
</head>
<body>
<c:import url="header.jsp"/>
<table width="60%" cellpading="3" border="1">
  <caption>Пользователи зарегистрированные в библиотеке</caption><br/>
  <thead align="center">
  <tr bordercolor="red">
    <th colspan="1">Имя</th>
    <th colspan="1">Фамилия</th>
    <th colspan="1">Телефон</th>
    <th colspan="1">Email</th>
    <th colspan="1">Улица</th>
    <th colspan="1">Город</th>
  </tr>
  </thead>
  <tbody align="center">
  <c:forEach items="${userList}" var="user">
    <tr>
      <td>${user.firstname}</td>
      <td>${user.lastname}</td>
      <td>${user.telephone}</td>
      <td>${user.email}</td>
      <td>${user.adress.street}</td>
      <td>${user.adress.city}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<c:import url="footer.jsp"/>
</body>
</html>
