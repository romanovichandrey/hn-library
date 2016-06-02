<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 13.05.2016
  Time: 6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${param.status=='error'}">
  <label style="color:red">Invalid username or password!</label>
</c:if>

<c:url var="login" value="/user/login"/>
<s:form action="${login}" modelAttribute="user" method="post" class="form-horizontal" role="form">
  <fieldset>
    <div class="form-group">
      <label for="login" class="col-sm-2 control-label">Login: </label>
      <div class="col-sm-10">
        <s:input id="login" type="text" path="login" class="form-control"/><br/>
      </div>
    </div>
    <div class="form-group">
      <label for="password" class="col-sm-2 control-label">Password: </label>
      <div class="col-sm-10">
        <s:input id="password" type="password" maxlength="255" path="password" class="form-control"/><br/>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button class="btn btn-default" type="submit">Login: </button>
      </div>
    </div>
  </fieldset>
</s:form>
