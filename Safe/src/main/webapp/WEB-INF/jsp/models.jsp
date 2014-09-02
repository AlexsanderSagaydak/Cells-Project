<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Модели</title>
        <LINK REL="SHORTCUT ICON" HREF="img/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="safe">
        <meta name="author" content="Kuzmenko Alexandr">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/jquery.pnotify.default.css" rel="stylesheet">
        <style>
            .addBtn {
                margin-bottom: 30px;
            }
        </style>
    </head>
    <body>
        <div class="container" style="width: 1000px">
            <%@include file="../jspf/header.jspf" %>
            <div class="addBtn">
                <a href="modeledit" class="btn btn-success">Добавить модель</a>

                <form class="pull-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Поиск по модели">
                    </div>
                </form>
            </div>
            <div class="model-table">
                <table class="table table-striped table-hover table-condensed">
                    <thead>
                        <tr>
                            <th>Название модели</th>
                            <th>Высота, мм</th>
                            <th>Ширина, мм</th>
                            <th>Глубина, мм</th>
                            <th>Редактирование</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <%@include file="../jspf/footer.jspf" %>
        </div>
        <script type="text/javascript">
            $(function() {
//TODO
            });
        </script>
    </body>
</html>