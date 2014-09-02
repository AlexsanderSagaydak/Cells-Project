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
            <h3 align="center">Отчет: просрочка на текущую дату</h3>
            <form class="form-horizontal" role="form">
                <div class="form-group" >
                    <div class="col-md-4">
                        <sec:authorize access="hasAnyRole('controller')">
                            <button type="button" id="searchAll" class="btn btn-success">По всем отделениям</button>
                        </div>
                        <div class="col-md-5">
                            <select id="department" class="form-control" required>
                                <c:forEach var="dept" items="${departmentList}">
                                    <option value="${dept.id}">${dept.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </sec:authorize>
                    <button type="button" id="searchByCurrentDept" class="btn btn-success">Сформировать</button>
                </div> 
                <br>
            </form>

            <input id="currentDeptId" class="form-control" name="deptId" type="hidden" value="${departmentId}">
            <form action="${pageContext.request.contextPath}/getDataForDelayReportXls" method="POST">
                <input id="departmentIdHidden" class="form-control" name="departmentId" type="hidden" value="">
                <div class="col-lg-offset-5">
                    <%--<sec:authorize access="hasAnyRole('controller')">--%>
                    <button type="submit" id="exportToExcel" class="btn btn-sm" disabled="disabled">Експортировать данные в Excel</button>
                    <%--</sec:authorize>--%>
                </div>
            </form>
            <br>
            <div class="profit-table">
                <table id="profitTable" class="table table-striped table-hover table-condensed">
                    <thead>
                        <tr>
                            <th>ФИО</th>
                            <th>№ тел</th>
                            <th>Размер</th>
                            <th>№</th>
                            <th>Окончание</th>
                            <th>Просрочено</th>
                            <th>Сумма</th>
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
                    dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"]
                });
                $("#searchByCurrentDept").click(function() {
                    var currentDeptId = $("#currentDeptId").val();
                    $("#departmentIdHidden").val(currentDeptId);
                    getCurrentDept().done(function(data) {
                        if (data && data.length > 0) {
                            createTable(data);
                            $('#exportToExcel').attr("disabled", false);
                        } else {
                            $('#exportToExcel').attr("disabled", true);
                        }
                    });
                });

                $("#department").change(function() {
                    var departmentId = $("#department").val();
                    $("#departmentIdHidden").val(departmentId);
                    getDeptId(departmentId).done(function(data) {
                        if (data && data.length > 0) {
                            createTable(data);
                            $('#exportToExcel').attr("disabled", false);
                        } else {
                            $('#exportToExcel').attr("disabled", true);
                        }
                    });
                });

                $("#searchAll").click(function() {
                    $("#departmentIdHidden").val(null);
                    getAllDept().done(function(data) {
                        if (data && data.length > 0) {
                            createTable(data);
                            $('#exportToExcel').attr("disabled", false);
                        } else {
                            $('#exportToExcel').attr("disabled", true);
                        }
                    });
                });

                function getDeptId(departmentId) {
                    return $.ajax({
                        url: 'getDataForDelayReport',
                        type: 'POST',
                        data: {
                            departmentId: departmentId
                        }
                    });
                }

                function getAllDept() {
                    return $.ajax({
                        url: 'getDataForAllDelayReport',
                        type: 'POST',
                        data: {
                        }
                    });
                }

                function getCurrentDept() {
                    return $.ajax({
                        url: 'getDataForCurrentDeptDelayReport',
                        type: 'POST',
                        data: {
                        }
                    });
                }

                var profitTable = $("#profitTable").dataTable({
                    "aoColumns": [
                        {"sWidth": "33%"},
                        {"sWidth": "15%"},
                        {"sWidth": "11%"},
                        {"sWidth": "5%"},
                        {"sWidth": "10%"},
                        {"sWidth": "10%"},
                        {"sWidth": "7%"}
                    ]
                });

                function createTable(data) {
                    profitTable.fnClearTable();
                    $.each(data, function(key, val) {
                        profitTable.fnAddData([
                            val.clientSurname + " " + val.clientName + " " + val.clientPatronymic,
                            val.clientPhone,
                            val.safeHeight + "*" + val.safeWidth + "*" + val.safeDepth,
                            val.safeNum,
                            val.dateEnd,
                            val.delayDays,
                            val.contractDebt
                        ]);
                    });
                }
            });
        </script>
    </body>
</html>