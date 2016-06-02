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

<table class="table table-bordered" width="60%" cellpading="3" border="1">
  <caption>Каталог книг</caption><br/>
  <thead align="center">
  <tr>
    <th colspan="1">Название книги</th>
    <th colspan="1">Описание книги</th>
    <th colspan="1">Автор книги</th>
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
      <c:choose>
      <c:when test="${not empty book.autors}">
        <td>
        <table style="border: none;">
          <tbody>
          <c:forEach items="${book.autors}" var="autor">
            <tr>
            <td>${autor.firstname}</td>
            <td>${autor.lastname}</td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        </td>
      </c:when>
        <c:otherwise>
          <td>Автор не известен</td>
        </c:otherwise>
      </c:choose>
      <td>${book.yearPublishing}</td>
      <td>${book.category.name}</td>
      <td>${book.user.lastname}</td>
      <c:if test="${book.user.id eq user.id}">
        <td>
          <a href="updateBook?name=${book.name}&description=${book.description}&book_date=${book.yearPublishing}&id_book=${book.id}&name_cat=${book.category.name}">Изменить</a>
          <a href="deleteBook?id_book=${book.id}">Удалить</a></td>
      </c:if>
    </tr>
  </c:forEach>
  </tbody>
</table>
<c:if test="${currentPage != 1}">
  <td><a href="/book/list/${currentPage - 1}">Previous</a></td>
</c:if>
<table>
  <tr>
    <c:forEach begin="1" end="${noOfPages}" var="i">
    <c:choose>
      <c:when test="${currentPage eq i}">
        <td>${i}</td>
      </c:when>
      <c:otherwise>
        <td><a href=/book/list/${i}">${i}</a></td>
      </c:otherwise>
    </c:choose>
    </c:forEach>
  </tr>
</table>
<c:if test="${currentPage lt noOfPages}">
  <td><a href="/book/list/${currentPage + 1}">Next</a></td>
</c:if>

</body>
</html>

