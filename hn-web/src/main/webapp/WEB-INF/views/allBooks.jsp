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
  <title>allBooks</title>
</head>
<body>
<c:import url="header.jsp"/>
<table width="60%" cellpading="3" border="1">
  <caption>Каталог книг</caption><br/>
  <thead align="center">
  <tr bordercolor="red">
    <th colspan="1">Название книги</th>
    <th colspan="1">Описание книги</th>
    <th colspan="1">Имя автора книги</th>
    <th colspan="1">Фамилия автора книги</th>
    <th colspan="1">Дата выпуска</th>
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
      <c:forEach items="${book.autors}" var="autor">
        <td>${autor.firstname}</td>
        <td>${autor.lastname}</td>
      </c:forEach>
      <td>${book.yearPublishing}</td>
      <td>${book.category.name}</td>
      <td>${book.user.lastname}</td>
      <c:if test="${book.user.login eq user.login}">
        <td><a
                href="updateBook?&name=${book.name}&description=${book.description}&book_date=${book.yearPublishing}
			&name_cat=${book.category.name}">Изменить</a>
          <a href="deleteBook?id_book=${book.id}">Удалить</a></td>
      </c:if>
    </tr>
  </c:forEach>
  </tbody>
</table>
<c:import url="footer.jsp"/>
</body>
</html>

