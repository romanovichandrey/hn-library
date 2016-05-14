<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title></title>
</head>
<body>
<c:import url="header.jsp"/>
	<form method="post" action="addBook">
		<table width="30%" border="1" cellpading="3">
			<thead>
				<tr>
					<th colspan="2">Добавление книги в библиотеку</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Укажите название книги</td>
					<td><input type="text" name="name" value="" /></td>
				</tr>
				<tr>
					<td>Укажите описание книги</td>
					<td><input type="text" name="description" value="" /></td>
				</tr>
				<tr>
					<td>Укажите имя автора книги</td>
					<td><input type="text" name="firstname" value="" /></td>
				</tr>
				<tr>
					<td>Укажите фамилию автора книги</td>
					<td><input type="text" name="lastname" value="" /></td>
				</tr>
				<tr>
					<td>Укажите дату написания книги</td>
					<td><input type="text" name="book_date" value="" /></td>
				</tr>
				<tr>
					<td>Выберите категорию книги</td>
					<td><select name="id_cat">
							<c:forEach items="${categoryList}" var="category">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
					</select></td>
				</tr>
			</tbody>			
		</table>
		<c:set var="user" value="${sessionScope.user}" />
				<input type="hidden" name="id_user" value="${user.id}" />
			<input type="submit" value="Добавить книгу" />
	</form>
	<jsp:include page="footer.jsp"/>
</body>
</html>