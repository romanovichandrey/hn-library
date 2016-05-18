<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title></title>
</head>
<body>
<c:import url="header.jsp"/>
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
						<td><input type="text" name="name" value="${param.name}" /></td>
					</tr>
					<tr>
						<td>Измените описание книги</td>
						<td><input type="text" name="description" value="${param.description}" /></td>
					</tr>
					<c:forEach items="${autorSet}" var="autor">
					<tr>
						<td>Измените имя автора книги</td>
						<td><input type="text" name="autor_firstname" value="${autor.firstname}"></td>
					</tr>
						<tr>
							<td>Измените фамилию автора книги</td>
							<td><input type="text" name="autor_lastname" value="${autor.lastname}"></td>
						</tr>
					</c:forEach>
					<tr>
						<td>Измените дату написания книги</td>
						<td><input type="text" name="book_date" value="${param.book_date}" /></td>
					</tr>
					<tr>				
						<td>Измените категорию книги</td>
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
		<input type="hidden" name="id_book" value="${param.id_book}" />
		<input type="submit" value="Изменить" />
	</form>
	<jsp:include page="footer.jsp" />
</body>
</html>