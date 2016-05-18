<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 13.04.2016
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title></title>
  <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css"/>

</head>
<body>
<c:set var="user" value="${sessionScope.user}" />
<c:choose>
  <c:when test="${not empty user and user.login eq 'admin'}">
    <p>Hello, ${user.firstname}</p>
    <p> <a href="logOut">Выход</a></p>
      <center>
      <p>
        <a href="main.jsp">Главная страница</a>
        <a href="book">Каталог книг</a>
        <a href="addBook">Добавить книгу</a>
        <a href="addCategory">Добавить категорию книги</a>
      </p> </center>
      <a href="user">Список зарегистрированных пользователей</a>
  </c:when>
  <c:when test="${not empty user}">
    <p>Hello, ${user.firstname}</p>
    <p> <a href="logOut">Выход</a></p>
    <center>
      <p>
        <a href="main.jsp">Главная страница</a>
        <a href="book">Каталог книг</a>
        <a href="addBook">Добавить книгу</a>
      </p> </center>
  </c:when>
  <c:otherwise>
    <center>
      <p></p>
    <form method="post" action="/login">
      <table width="30%" border="1" cellpading="3">
        <tr>
          <th colspan="2">Вход в систему для зарегистрированных пользователей</th>
        </tr>
        <tbody>
        <tr>
          <td>Логин:</td>
          <td><input type="text" name="login" value=""/></td>
        </tr>
        <tr>
          <td>Пароль:</td>
          <td><input type="password" name="password" value=""/></td>
        </tr>
        <tr>
          <td colspan="2"><input type="submit" value="Войти"/></td>
        </tr>
        </tbody>
      </table>
    </form>
      <a href="/userRegistr">Регистрация</a>
    </center>
  </c:otherwise>
</c:choose>
<hr/>


</body>
</html>



