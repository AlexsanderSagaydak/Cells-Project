<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html ng-app="appLogin">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Страница авторизации</title>
        <LINK REL="SHORTCUT ICON" HREF="img/favicon.ico">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/signin.css" rel="stylesheet">
    </head>
    <body onload='document.f.j_username.focus();'>
        <div class="container" style="width: 1000px">
            <form class="form-signin" action="<c:url value='j_spring_security_check'/>" name="f" method="POST">
                <h1 align=center><b class="text-info"> С Е Й Ф Ы</b></h1>
                <br>
                <div class="form-group">
                    <input type="text" class="form-control" name="j_username" placeholder="Логин" autofocus>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="j_password" placeholder="Пароль">
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
            </form>
            <c:if test="${not empty loginFailed}">
                <div class="alert alert-danger"><h4>Ошибка! ${loginFailed}</h4>
                </div>
            </c:if>
            <%@include file="../jspf/footer.jspf" %>
        </div>
    </body>
</html>