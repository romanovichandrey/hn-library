<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 14.04.2016
  Time: 6:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="user" value="${sessionScope.user}" />
<html>
<head>
  <title></title>
</head>
<body>
<jsp:include page="header.jsp" />
<table width="60%" cellpading="3" border="1">
  <caption>Библиотека</caption>
  <thead align="center">
  <tr bordercolor="red">
    <th colspan="1">Названиекниги</th>
    <th colspan="1">Описаниекниги</th>
    <th colspan="1">Авторкниги</th>
    <th colspan="1">Датавыпуска</th>
    <th colspan="1">Цена</th>
    <th colspan="1">Категория</th>
    <th colspan="1">Имя пользователя добавившего книгу</th>
    <th colspan="1">Редактировать</th>
  </tr>
  </thead>
  <tbody align="center">
  <c:forEach items="${bookList}" var="book">
    <tr>
      <td>${book.name}</td>
      <td>${book.description}</td>
      <td>${book.author}</td>
      <td>${book.book_date}</td>
      <td>${book.price}</td>
      <td>${book.category.name_cat}</td>
      <td>${book.user.login}</td>
      <c:if test="${book.user.login eq user.login}">

        <td><a
                href="updateBook?id_book=${book.id_book}&name=${book.name}&description=${book.description}&author=${book.author}&book_date=${book.book_date}
			&price=${book.price}&name_cat=${book.category.name_cat}&user_name=${book.user.login}">Изменить</a>
          <a href="deleteBook?id_book=${book.id_book}">Удалить</a></td>
      </c:if>
    </tr>
  </c:forEach>
  </tbody>
</table>
<p>
  <a href="addBook">Добавать книгу</a>
</p>
<jsp:include page="footer.jsp" />
</body>
</html>

