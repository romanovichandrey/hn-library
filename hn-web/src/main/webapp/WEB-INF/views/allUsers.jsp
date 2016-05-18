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
  <title>allUsers</title>
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
    <th colspan="1">Имя книги</th>
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
      <c:choose>
        <c:when test="${not empty user.books}">
          <td>
            <table style="border: none;">
              <tbody>
              <c:forEach items="${user.books}" var="book">
                <tr>
                  <td>${book.name}</td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </td>
        </c:when>
        <c:otherwise>
          <td>Книга не известна или не была добавлена</td>
        </c:otherwise>
      </c:choose>
    </tr>
  </c:forEach>
  </tbody>
</table>
<c:if test="${currentPage != 1}">
  <td><a href="user?page=${currentPage - 1}">Previous</a></td>
</c:if>
<table>
  <tr>
    <c:forEach begin="1" end="${noOfPages}" var="i">
      <c:choose>
        <c:when test="${currentPage eq i}">
          <td>${i}</td>
        </c:when>
        <c:otherwise>
          <td><a href="user?page=${i}">${i}</a></td>
        </c:otherwise>
      </c:choose>
    </c:forEach>
  </tr>
</table>
<c:if test="${currentPage lt noOfPages}">
  <td><a href="book?page=${currentPage + 1}">Next</a></td>
</c:if>
<c:import url="footer.jsp"/>
</body>
</html>
