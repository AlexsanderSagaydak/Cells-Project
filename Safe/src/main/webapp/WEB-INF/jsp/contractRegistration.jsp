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
        <link rel="stylesheet" href="css/jquery-ui.css">
        <style>
            .addBtn {
                margin-bottom: 30px;
            }
        </style>
    </head>
    <body>
        <div class="container" style="width: 1000px">
            <h4 align="center"><b>${department.name}</b></h4>
            <%@include file="../jspf/header.jspf" %>
            <h3 align="center">Журнал регистрации договоров</h3>
            <div class="form-group">
                <label for="dateStart" class="col-md-2 control-label">Введите период:</label>
                <div class="col-md-2">
                    <input id="dateStart" class="form-control" type="text" placeholder="с:">
                </div>
                <div class="col-md-2">
                    <input id="dateEnd" class="form-control" type="text" placeholder="по:">
                </div>
                <div class="col-md-2">
                    <button id="find" type="button" class="btn btn-success">Найти</button>
                </div>
            </div>
            <form action="${pageContext.request.contextPath}/getDataForContractRegistrationReportXls" method="POST">
                <input id="dateStartHidden" class="form-control" name="dateStartHidden" type="hidden">
                <input id="dateEndHidden" class="form-control" name="dateEndHidden" type="hidden" >
                <input id="deptId" class="form-control" name="deptId" type="hidden" value="${department.id}">
                <div class="col-lg-offset-5">
                    <button type="submit" id="exportToExcel" class="btn btn-sm" disabled="disabled">Експортировать данные в Excel</button>
                </div>
            </form>
                <input id="deptId" class="form-control" type="hidden" value="${departament.id}">
            <br>
            <div class="safe-table">
                <table id="table" class="table table-striped table-hover table-condensed">
                    <thead>
                        <tr>
                            <th>№ договора</th>
                            <th>Дата открытия</th>
                            <th>№ сейфа</th>
                            <th>ФИО</th>
                            <th>Дата закрытия</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <br>
            <%@include file="../jspf/footer.jspf" %>
        </div>

        <script type="text/javascript">
            $(function() {
                $("#dateStart, #dateEnd").datepicker({
                    dateFormat: "dd.mm.yy",
                    monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
                    firstDay: 1,
                    dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"],
                    showButtonPanel: true

                });

                var tableSafe = $("#table").dataTable({
                    "aoColumns": [
                        {"sWidth": "20%"},
                        {"sWidth": "20%"},
                        {"sWidth": "10%"},
                        {"sWidth": "35%"},
                        {"sWidth": "20%"}
                    ]
                });
                
                var find = $("#find");
                find.on('click', function() {
                    var dateStart = $("#dateStart").val();
                    var dateEnd = $("#dateEnd").val();
                    var deptId = $("#deptId").val();
                    var de = dateEnd.split('.');
                    var ds = dateStart.split('.');
                    var dateStartFormat = new Date(ds[2], parseInt(ds[1]) - 1, ds[0]);
                    var dateEndFormat = new Date(de[2], parseInt(de[1]) - 1, de[0]);
                    $("#dateStartHidden").val(dateStartFormat);
                    $("#dateEndHidden").val(dateEndFormat);

                    getContractByPeriod(dateStartFormat, dateEndFormat, deptId).done(function(data) {
                        if (data && data.length > 0) {
                            createTable(data);
                            $('#exportToExcel').attr("disabled", false);
                        } else {
                            $('#exportToExcel').attr("disabled", true);
                        }
                        createTable(data);
                    });
                    function getContractByPeriod(dateStartFormat, dateEndFormat, deptId) {
                        return $.ajax({
                            url: 'getContractByPeriod',
                            type: 'POST',
                            data: {
                                dateStart: dateStartFormat,
                                dateEnd: dateEndFormat,
                                deptId: deptId
                            }
                        });
                    }
                    function createTable(data) {
                        tableSafe.fnClearTable();
                        $.each(data, function(key, val) {
                            tableSafe.fnAddData([
                                val.numberContract,
                                val.dateContractStart,
                                val.safeNumber,
                                val.client1.surname + " " + val.client1.name + " " + val.client1.patronymic,
                                val.dateContractEnd
                            ]);
                        });
                    }
                });
            });
        </script>
    </body>
</html>