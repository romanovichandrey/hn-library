<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 16.05.2016
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<center>
<p>Ошибка: пользователь с таким login или email уже зарегистрирован</p>
<form method="post" action="/userRegistr">
  <table width="30%" border="1" cellpading="3">
    <tr>
      <th colspan="2">Измените ваши регистрационные данные</th>
    </tr>
    <tbody>
    <tr>
      <td>Имя</td>
      <td><input type="text" name="firstname" value="" /></td>
    </tr>
    <tr>
      <td>Фамилия</td>
      <td><input type="text" name="lastname" value=""></td>
    </tr>
    <tr>
      <td>Телефон</td>
      <td><input type="text" name="telephone" value=""></td>
    </tr>
    <tr>
      <td>Email адресс</td>
      <td><input type="text" name="email" value=""></td>
    </tr>
    <tr>
      <td>Улица</td>
      <td><input type="text" name="street" value=""></td>
    </tr>

    <tr>
      <td>Город</td>
      <td><input type="text" name="city" value=""></td>
    </tr>
    <tr>
      <td>Регион</td>
      <td><input type="text" name="state" value=""></td>
    </tr>
    <tr>
      <td>Страна</td>
      <td><input type="text" name="country" value=""></td>
    </tr>
    <tr>
      <td>Login</td>
      <td><input type="text" name="login" value=""></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input type="text" name="password" value=""></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit" value="Зарегистрироваться" /></td>
    </tr>
    </tbody>
  </table>
</form>
</center>
