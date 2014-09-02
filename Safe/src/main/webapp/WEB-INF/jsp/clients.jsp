<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Клиенты</title>
        <LINK REL="SHORTCUT ICON" HREF="img/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="safe">
        <link href="css/jquery.dataTables.css" rel="stylesheet">
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
            <h4 align="center"><b>${departament.name}</b></h4>
            <%@include file="../jspf/header.jspf" %>
            <h4 align="center">Поиск клиентов которые заключали договора</h4>
            <form class="form-horizontal" role="form">
                <div class="form-group" >
                    <label for="surname" class="col-md-3 control-label">Поиск:</label>
                    <div class="col-md-3">
                        <input id="surname" class="form-control" type="text" placeholder="по фамилии">
                        <!--<input id="surnameHidden" class="form-control" type="hidden">-->
                    </div>
                    <div class="col-md-2">
                        <input id="inn" class="form-control" type="text" placeholder="по ИНН">
                        <!--<input id="innHidden" class="form-control" type="hidden">-->
                    </div>
                    <div class="col-md-3">
                        <button type="button" id="search" class="btn btn-success">Найти</button>
                        <sec:authorize access="hasAnyRole('controller')">
                            <button type="button" id="searchAll" class="btn btn-sm">Всех клиентов</button>
                        </sec:authorize>
                    </div>
                    <div class="col-md-1">
                    </div>
                </div>  
            </form>

            <form action="${pageContext.request.contextPath}/getDataForExcelReport" method="POST">
                <input id="surnameHidden" class="form-control" name="status" type="hidden">
                <!--                <input id="innHidden" class="form-control" name="inn" type="hidden">-->
                <div class="col-lg-offset-5">
                    <sec:authorize access="hasAnyRole('controller')">
                        <button type="submit" id="exportToExcel" class="btn btn-sm di" disabled="disabled">Експортировать данные в Excel</button>
                    </sec:authorize>
                </div>
            </form>

            <div class="safe-table">
                <table id="table" class="table table-striped table-hover table-condensed">
                    <thead>
                        <tr>
                            <th>ФИО</th>
                            <th>ИНН</th>
                            <th>Паспорт</th>
                            <th>Телефон</th>
                            <th>Контракт</th>
                            <th>В отделении</th>
                            <th>Дата начала</th>
                            <th>Дата окончания</th>
                            <th>Дата закрытия</th>
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
                var table = $("#table").dataTable({
                    "aoColumns": [
                        {"sWidth": "25%"},
                        {"sWidth": "100px"},
                        {"sWidth": "100px"},
                        {"sWidth": "13%"},
                        {"sWidth": "10%"},
                        {"sWidth": "15%"},
                        {"sWidth": "100px"},
                        {"sWidth": "100px"},
                        {"sWidth": "100px"}
                    ]
                });

                var search = $('#search');
                var searchAll = $('#searchAll');
                var surnameHidden = $('#surnameHidden');
                var innHidden = $('#innHidden');
                var allClientsStatus = $('#surnameHidden');

                search.on('click', function() {
                    var surname = $("#surname").val();
                    var inn = $("#inn").val();
                    surnameHidden.val(surname);
                    innHidden.val(inn);

                    if (surname !== "") {
                        getClientBySurname(surname).done(function(data) {
                            createTable(data);
                        });
                    } else {
                        var inn = $("#inn").val();
                        getClientByInn(inn).done(function(data) {
                            createTable(data);
                        });
                    }
                });
                function getClientBySurname(surname) {
                    return $.ajax({
                        url: 'getClientBySurname',
                        type: 'POST',
                        data: {
                            surname: surname
                        }
                    });
                }
                function getClientByInn(inn) {
                    return $.ajax({
                        url: 'getClientByInn',
                        type: 'POST',
                        data: {
                            inn: inn
                        }
                    });
                }
                searchAll.on('click', function() {
                    allClientsStatus.val("Ok");
                    getAllClientsByAllDept().done(function(data) {
                        if (data && data.length > 0) {
                            createTable(data);
                            $('#exportToExcel').attr("disabled", false);
                        } else {
                            $('#exportToExcel').attr("disabled", true);
                        }
                    });
                });
                function getAllClientsByAllDept() {
                    return $.ajax({
                        url: 'getAllClientsByAllDept',
                        type: 'POST',
                        data: {
                        }
                    });
                }

                function createTable(data) {
                    table.fnClearTable();
                    $.each(data, function(key, val) {
                        if (val.contractStatus === 1) {
                            val.contractStatus = "Действует";
                        } else {
                            val.contractStatus = "Закрыт";
                        }
                        if(val.dateContractClose === undefined) {
                            val.dateContractClose = "---"
                        }
                        table.fnAddData([val.client1.surname + " " + val.client1.name + " " + val.client1.patronymic,
                            val.client1.inn,
                            val.client1.series + " " + val.client1.number,
                            val.client1.mobilePhone1,
                            val.contractStatus,
                            val.departmentName,
                            val.dateContractStart,
                            val.dateContractEnd,
                            val.dateContractClose
                        ]);
                    });
                }
            });
        </script>
    </body>
</html>