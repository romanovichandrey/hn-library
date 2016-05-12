<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 14.04.2016
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title></title>
</head>
<body>
<form method="post" action="updateBook">
  <table width="30%" border="1" cellpading="3">
    <thead>
    <tr>
      <th colspan="2">Изменение книги</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>Измените название книги</td>
      <td><input type="text" name="name" value="${name}" /></td>
    </tr>
    <tr>
      <td>Измените описание книги</td>
      <td><input type="text" name="description" value="${description}" /></td>
    </tr>
    <tr>
      <td>Измените автора книги</td>
      <td><input type="text" name="author" value="${author}" /></td>
    </tr>
    <tr>
      <td>Измените дату написания книги</td>
      <td><input type="text" name="book_date" value="${book_date}" /></td>
    </tr>
    <tr>
      <td>Измените примерную цену книги</td>
      <td><input type="text" name="price" value="${price}" /></td>
    </tr>
    <tr>
      <td>Измените категорию книги</td>
      <td><select name="id_cat">
        <c:forEach items="${categoryList}" var="category">
          <option value="${category.id_cat}">${category.name_cat}</option>
        </c:forEach>
      </select></td>
    </tr>
    </tbody>
  </table>
  <c:set var="user" value="${sessionScope.user}" />
  <input type="hidden" name="id_book" value="${id_book}" />
  <input type="submit" value="Изменить" />
</form>
<jsp:include page="footer.jsp" />
</body>
</html>
