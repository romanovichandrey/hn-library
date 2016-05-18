<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 16.05.2016
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<center>
  <p>Ошибка:	Сбой аутентификации. Повторите попытку</p>
  <form method="post" action="/login">
    <table width="30%" border="1" cellpading="3">
      <tr>
        <th colspan="2">Вход в систему для зарегистрированных пользователей</th>
      </tr>
      <tbody>
      <tr>
        <td>Логин:</td>
        <td><input type="text" name="login" value="" /></td>
      </tr>
      <tr>
        <td>Пароль:</td>
        <td><input type="password" name="password" value=""></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" value="Войти" /></td>
      </tr>
      </tbody>
    </table>
  </form>
  <a href="/userRegistr">Регистрация</a>
</center>

