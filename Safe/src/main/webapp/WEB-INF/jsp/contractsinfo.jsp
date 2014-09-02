<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Сейфы: Список сейфов</title>
        <LINK REL="SHORTCUT ICON" HREF="img/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="safe">
        <link href="css/jquery.dataTables.css" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet">
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
            <div  id="delayDays">
            </div>
            <ul class="nav nav-tabs box">
                <li id="end" class="active"><a href="#all">Без окончания срока</a></li>
                <li id="all"><a href="#free">С окончанием срока</a></li>
            </ul>
            <div class="safe-table">
                <table id="table" class="table table-striped table-hover table-condensed">
                    <thead>
                        <tr>
                            <th>ФИО</th>
                            <th>ИНН</th>
                            <th>Телефон</th>
                            <th>№яч.</th>
                            <th>Начало</th>
                            <th>Окончание</th>
                            <th>Редактировать</th>
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
                var tableSafe = $("#table").dataTable({
                    "aoColumns": [
                        {"sWidth": "38%"},
                        {"sWidth": "100px"},
                        {"sWidth": "100px"},
                        {"sWidth": "7%"},
                        {"sWidth": "10%"},
                        {"sWidth": "12%"},
                        {"sWidth": "100px"}
                    ]
                });

                getDelayContracts('f').done(function(data) {
                    createTable(data);
                });
                
                var box = $('.box');
                box.on('click', 'li', function() {
                    $(this).siblings('li').removeClass('active');
                    $(this).addClass('active');
                    var status = $(this).attr('id');
                    getDelayContracts($(this).attr('id')).done(function(data) {
                        createTable(data);
                    });
                });

                function getDelayContracts(status) {
                    var status = {status: status};
                    return $.ajax({
                        url: 'getDelayContracts',
                        type: 'GET',
                        data: status
                    });
                }

                function createTable(data) {
                    tableSafe.fnClearTable();
                    $.each(data, function(key, val) {
                        tableSafe.fnAddData([val.client1.surname + " " + val.client1.name + " " + val.client1.patronymic,
                            val.client1.inn,
                            val.client1.mobilePhone1,
                            val.safeNumber,
                            val.dateContractStart,
                            val.dateContractEnd, "<a class=\"btn btn-warning\" href=\"contractviev?id=" + val.id + "\">Просмотр</a>"]);
                    });
                }
            });
        </script>
    </body>
</html>