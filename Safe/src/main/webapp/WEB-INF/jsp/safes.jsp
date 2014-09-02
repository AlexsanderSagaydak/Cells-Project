<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Сейфы: Список сейфов</title>
        <LINK REL="SHORTCUT ICON" HREF="img/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="safe">

        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/jquery.dataTables.css" rel="stylesheet">
        <link href="css/jquery.pnotify.default.css" rel="stylesheet">
        <style>
            .safe-table {
                margin-top: 30px;;
            }

            .addBtn {
                margin-bottom: 30px;
            }
        </style>
    </head>
    <body>
        <div class="container" style="width: 1000px;">
            <h4 align="center"><b>${departament.name}</b></h4>
                    <%@include file="../jspf/header.jspf" %>
            <ul class="nav nav-tabs box">
                <li id="f" class="active"><a href="#free">Свободные</a></li>
                <li id="r"><a href="#reserved">Занятые</a></li>
            </ul>
            <sec:authorize access="hasAnyRole('controller')">
                <input id="service" type="hidden" value="ok">
            </sec:authorize>
            <div class="safe-table">
                <table id="table" class="table table-striped table-striped">
                    <thead>
                        <tr>
                            <th>Код сейфа</th>
                            <th>Модель</th>
                            <th>Высота, мм</th>
                            <th>Ширина, мм</th>
                            <th>Глубина, мм</th>
                            <th>Занят до:</th>
                                <sec:authorize access="hasAnyRole('controller')">
                                <th>Редактирование</th>
                                </sec:authorize>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <%@include file="../jspf/footer.jspf" %>
        </div>

        <input type="hidden" id="testHiddenInput" value="ok" />


        <script type="text/javascript">
            $(function() {
                var tableSafe = $("#table").dataTable();
                var box = $('.box');
                box.on('click', 'li', function() {
                    $(this).siblings('li').removeClass('active');
                    $(this).addClass('active');
                    var status = $(this).attr('id');

                    getSafes($(this).attr('id')).done(function(data) {
                        createTable(data);
                    });
                });

                function getSafes(status) {
                    var status = {status: status};
                    return $.ajax({
                        url: 'safe',
                        type: 'GET',
                        data: status
                    });
                }

                getSafes('f').done(function(data) {
                    createTable(data);
                });

                function createTable(data) {
                    tableSafe.fnClearTable();
                    $.each(data, function(key, val) {

                        if (val.date === undefined) {
                            val.date = "Свободен";
                        }
                        if ($("#service").val() === "ok") {
                            tableSafe.fnAddData([
                                val.code,
                                val.model.name,
                                val.model.height,
                                val.model.width,
                                val.model.depth,
                                val.date,
                                "<a class=\"btn btn-warning\" href=\"safeedit?id=" + val.id + "\">Редактирование</a>"]);
                        } else {
                            tableSafe.fnAddData([
                                val.code,
                                val.model.name,
                                val.model.height,
                                val.model.width,
                                val.model.depth,
                                val.date
                            ]);
                        }
                    });
                }
            });
        </script>
    </body>
</html>