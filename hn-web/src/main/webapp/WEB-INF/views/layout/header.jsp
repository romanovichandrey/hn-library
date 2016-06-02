<%--
  Created by IntelliJ IDEA.
  User: Romanovich
  Date: 13.04.2016
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar">
    <div class="navbar-inner">
        <ul class="nav">
            <li><a href="/welcom"><spring:message code="home"/></a></li>
            <li><a href="/book/list"><spring:message code="all.book"/></a></li>
            <c:set var="user" value="${sessionScope.user}"/>
            <sec:authorize access="hasRole('PRIVATE')">
                <li>
                <p class="navbar-text"><sec:authentication property="principal.username"/></p>
                <c:choose>
                    <c:when test="${not empty user and user.login eq 'admin'}">
                        <li><a href="/category/new"><spring:message code="add.category"/></a></li>
                        <li><a href="/user/list/"><spring:message code="users"/></a></li>
                    </c:when>
                </c:choose>
            </sec:authorize>
            <c:choose>
                <c:when test="${not empty user}">
                    <li><a href="/book/new"><spring:message code="add.book"/></a></li>
                    <li><a href="/user/profile"><spring:message code="my.profile"/></a></li>
                    <li><a href="/user/logout"><spring:message code="logout"/></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/user/new"><spring:message code="registration"/></a></li>
                </c:otherwise>
            </c:choose>
            <spring:message code="locale.change" var="changeLocale" htmlEscape="true"/>
            <c:url var="locale" value="/welcom?locale=${changeLocale}"/>
            <li><a href="${locale}"><spring:message code="locale.name"/></a></li>

            <li>
                <div class="searchform">
                    <form class="navbar-search" method="GET" action="search" accept-charset="utf-8">
                        <input class="input-long search-query" type="text" placeholder="Search.." name="search"/>
                        <input class="btn btn-small" type="submit"/>
                    </form>
                </div>
            </li>
        </ul>
    </div>
</div>

