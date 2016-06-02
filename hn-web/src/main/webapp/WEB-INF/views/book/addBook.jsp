<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
</head>
<body>
<div class="wrapper">
	<div>
		<h3 class="user-h3">Добавление книги</h3>
		<form:form method="POST" modelAttribute="book">
			<table>
				<tr>
					<td><label for="name">Укажите название книги: </label></td>
					<td><form:input path="name" id="name"/></td>
					<td><form:errors path="name" cssClass="error"/></td>
				</tr>
				<tr>
					<td><label for="description">Укажите описание книги: </label></td>
					<td><form:input path="description" id="description"/></td>
					<td><form:errors path="description" cssClass="error"/></td>
				</tr>
				<tr>
					<td><label>Укажите имя автора книги: </label></td>
					<td><input type="text" name="firstname" value=""/></td>
				</tr>
				<tr>
					<td><label>Укажите фамилию автора книги: </label></td>
					<td><input type="text" name="lastname" value=""/></td>
				</tr>
				<tr>
					<td><label for="yearPublishing">Укажите дату написания книги: </label></td>
					<td><form:input path="yearPublishing" id="yearPublishing"/></td>
					<td><form:errors path="yearPublishing" cssClass="error"/></td>
				</tr>
				<tr>
					<td>Выберите категорию книги</td>
					<td><select name="id">
						<c:forEach items="${categories}" var="category">
							<option value="${category.id}">${category.name}</option>
						</c:forEach>
					</select></td>
				</tr>
				<c:set var="user" value="${sessionScope.user}" />
				<input type="hidden" name="id_user" value="${user.id}" />
				<tr>
					<td colspan="3"><input class="btn" type="submit" value="Добавить"/></td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
</body>
</html>




