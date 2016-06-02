<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 13.04.2016
  Time: 10:41
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
        <h3 class="user-h3">Регистрация нового пользователя</h3>
        <form:form method="POST" modelAttribute="user" >
            <table>
                <tr>
                    <td><label for="firstname">Имя: </label></td>
                    <td><form:input path="firstname" id="firstname"/></td>
                    <td><form:errors path="firstname" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label for="lastname">Фамилия: </label></td>
                    <td><form:input path="lastname" id="lastname"/></td>
                    <td><form:errors path="lastname" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label for="telephone">Телефон: </label></td>
                    <td><form:input path="telephone" id="telephone"/></td>
                    <td><form:errors path="telephone" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label for="email">Email: </label></td>
                    <td><form:input path="email" id="email"/></td>
                    <td><form:errors path="email" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label>Улица: </label></td>
                    <td><input type="text" name="street" value=""/></td>
                </tr>
                <tr>
                    <td><label>Город: </label></td>
                    <td><input type="text" name="city" value=""/></td>
                </tr>
                <tr>
                    <td><label>Регирн: </label></td>
                    <td><input type="text" name="state" value=""/></td>
                </tr>
                <tr>
                    <td><label>Страна: </label></td>
                    <td><input type="text" name="country" value=""/></td>
                </tr>
                <tr>
                    <td><label for="login">Login: </label></td>
                    <td><form:input path="login" id="login"/></td>
                    <td><form:errors path="login" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label for="password">Password: </label></td>
                    <td><form:password path="password" id="password"/></td>
                    <td><form:errors path="password" cssClass="error"/></td>
                </tr>
                <tr>
                    <td colspan="3"><input class="btn" type="submit" value="Регистрация"/></td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
</body>
</html>


