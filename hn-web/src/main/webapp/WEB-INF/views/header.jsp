<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 13.04.2016
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="user" value="${sessionScope.user}" />
<c:choose>
  <c:when test="${not empty user}">
    <p>Hello, ${user.reader.name}</p>
  </c:when>
  <c:otherwise>
    <center>
      <h3>Welcome to my library</h3>
    <form method="post" action="books">
      <table width="30%" border="1" cellpading="3">
        <tr>
          <th colspan="2">Authorization</th>
        </tr>
        <tbody>
        <tr>
          <td>Login</td>
          <td><input type="text" name="login" value="" /></td>
        </tr>
        <tr>
          <td>Password</td>
          <td><input type="text" name="password" value=""></td>
        </tr>
        <tr>
          <td colspan="2"><input type="submit" value="Come in" /></td>
        </tr>
        </tbody>
      </table>
    </form>
      <p>Yet Not Registered!!</p>
      <a href="user">Registration</a>
    </center>
  </c:otherwise>
</c:choose>

