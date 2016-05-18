<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 18.05.2016
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title></title>
</head>
<body>
<c:import url="header.jsp"/>
<c:set var="user" value="${sessionScope.user}"/>
<p>Ваш профиль</p>
  <table width="30%" border="1" cellpading="3">
    <tbody>
    <tr>
      <td>Ваше имя</td>
      <td>${user.firstname}</td>
    </tr>
    <tr>
      <td>Ваша фамилия</td>
      <td>${user.lastname}</td>
    </tr>
    <tr>
       <td>Ваш номер телефона</td>
       <td>${user.telephone}</td>
    </tr>
    <tr>
      <td>Ваш адрес электронной почты</td>
      <td>${user.email}</td>
    </tr>
    <tr>
      <th colspan="2">Адресс проживания</th>
    </tr>
    <tr>
      <td>Улица</td>
      <td>${user.adress.street}</td>
    </tr>
    <tr>
      <td>Город</td>
      <td>${user.adress.city}</td>
    </tr>
    <tr>
      <td>Регион</td>
      <td>${user.adress.state}</td>
    </tr>
    <tr>
      <td>Страна</td>
      <td>${user.adress.country}</td>
    </tr>
    </tbody>
  </table>

<jsp:include page="footer.jsp" />
</body>
</html>