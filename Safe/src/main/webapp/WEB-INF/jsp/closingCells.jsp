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
             <h4 align="center"><b>${departament.name}</b></h4>
            <%@include file="../jspf/header.jspf" %>
            <h3 align="center">Отчет: прогноз закрытыя ячеек</h3>
            <div class="form-group">
                <label for="dateStart" class="col-md-2 control-label">Введите период:</label>
                <div class="col-md-2">
                    <input id="dateStart" class="form-control" type="text" placeholder="с:">
                </div>
                <div class="col-md-2">
                    <input id="dateEnd" class="form-control" type="text" placeholder="по:">
                </div>
                <sec:authorize access="hasAnyRole('controller')">
                    <div class="col-md-4">
                        <select id="department" class="form-control" required>
                            <c:forEach var="dept" items="${departmentList}">
                                <option value="${dept.id}">${dept.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </sec:authorize>
                <div class="col-md-2">
                    <button id="find" type="button" class="btn btn-success">Найти</button>
                </div>
                <br>
            </div>
            <div class="safe-table">
                <br>
                <input id="currentDeptId" class="form-control" name="currentDeptId" type="hidden" value="${departmentId}">  
                <form action="${pageContext.request.contextPath}/getDataForClosingCellsReportXls" method="POST">
                    <input id="dateStartHidden" class="form-control" name="dateStartHidden" type="hidden" value="">
                    <input id="dateEndHidden" class="form-control" name="dateEndHidden" type="hidden" value="">
                    <input id="departmentIdHidden" class="form-control" name="deptIdHidden" type="hidden" value="">
                    <div class="col-lg-offset-5">
                        <button type="submit" id="exportToExcel" class="btn btn-sm" disabled="disabled">Експортировать данные в Excel</button>
                    </div>
                </form>
                <table id="table" class="table table-striped table-hover table-condensed">
                    <thead>
                        <tr>
                            <th>ФИО</th>
                            <th>№ яч.</th>
                            <th>№ телефона</th>
                            <th>Дата открытия</th>
                            <th>Дата закрытия</th>
                            <th>Тариф, грн</th>
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
                        {"sWidth": "37%"},
                        {"sWidth": "8%"},
                        {"sWidth": "16%"},
                        {"sWidth": "13%"},
                        {"sWidth": "13%"},
                        {"sWidth": "18%"}
                    ]
                });

                var find = $("#find");
                find.on('click', function() {
                    var dateStart = $("#dateStart").val();
                    var dateEnd = $("#dateEnd").val();
                    var currentId = $("#currentDeptId").val();
                    var ds = dateStart.split('.');
                    var de = dateEnd.split('.');
                    var dateStartFormat = new Date(ds[2], parseInt(ds[1]) - 1, ds[0]);
                    var dateEndFormat = new Date(de[2], parseInt(de[1]) - 1, de[0]);

                    $("#dateStartHidden").val(dateStartFormat);
                    $("#dateEndHidden").val(dateEndFormat);
                    $("#departmentIdHidden").val(currentId);
                    getContractByPeriodAndDeptId(dateStartFormat, dateEndFormat, currentId).done(function(data) {
                        if (data && data.length > 0) {
                            createTable(data);
                            $('#exportToExcel').attr("disabled", false);
                        } else {
                            $('#exportToExcel').attr("disabled", true);
                        }
                        createTable(data);
                    });

                    function getContractByPeriodAndDeptId(dateStartFormat, dateEndFormat, currentId) {
                        return $.ajax({
                            url: 'getContractByPeriodAndDeptId',
                            type: 'POST',
                            data: {
                                dateStart: dateStartFormat,
                                dateEnd: dateEndFormat,
                                departmentId: currentId
                            }
                        });
                    }
                });

                $("#department").change(function() {
                    var dateStart = $("#dateStart").val();
                    var dateEnd = $("#dateEnd").val();
                    var ds = dateStart.split('.');
                    var de = dateEnd.split('.');
                    var dateStartFormat = new Date(ds[2], parseInt(ds[1]) - 1, ds[0]);
                    var dateEndFormat = new Date(de[2], parseInt(de[1]) - 1, de[0]);
                    var departmentId = $("#department").val();

                    $("#dateStartHidden").val(dateStartFormat);
                    $("#dateEndHidden").val(dateEndFormat);
                    $("#departmentIdHidden").val(departmentId);
                    getContractByPeriodAndDeptId(dateStartFormat, dateEndFormat, departmentId).done(function(data) {
                        if (data && data.length > 0) {
                            createTable(data);
                            $('#exportToExcel').attr("disabled", false);
                        } else {
                            $('#exportToExcel').attr("disabled", true);
                        }
                    });

                    function getContractByPeriodAndDeptId(dateStartFormat, dateEndFormat, departmentId) {
                        return $.ajax({
                            url: 'getContractByPeriodAndDeptId',
                            type: 'POST',
                            data: {
                                dateStart: dateStartFormat,
                                dateEnd: dateEndFormat,
                                departmentId: departmentId
                            }
                        });
                    }
                });

                function createTable(data) {
                    tableSafe.fnClearTable();
                    $.each(data, function(key, val) {
                        tableSafe.fnAddData([
                            val.clientSurname + " " + val.clientName + " " + val.clientPatronymic,
                            val.safeNum,
                            val.clientPhone,
                            val.dateStart,
                            val.dateEnd,
                            val.safePrice

                        ]);
                    });
                }
            });
        </script>
    </body>
</html>