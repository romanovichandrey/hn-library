<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 13.05.2016
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
</head>
<body>
<div class="wrapper">
  <div>
    <h3 class="user-h3">Добавление категории книг</h3>
    <form:form method="POST" modelAttribute="category">
      <table>
        <tr>
          <td><label for="name">Название категории: </label></td>
          <td><form:input path="name" id="name"/></td>
          <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
          <td colspan="3"><input class="btn" type="submit" value="Добавить"/></td>
        </tr>
      </table>
    </form:form>
  </div>
</div>
</body>
</html>

