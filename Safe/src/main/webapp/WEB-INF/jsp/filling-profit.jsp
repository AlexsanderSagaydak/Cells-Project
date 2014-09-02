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
            <h3 align="center">Отчет: заполняемость - доходы</h3>
            <form class="form-horizontal" role="form">
                <div class="form-group" >
                    <div class="col-md-2">
                        <input id="dateStart" class="form-control" type="text" placeholder="с:">
                    </div>
                    <div class="col-md-2">
                        <input id="dateEnd" class="form-control" type="text" placeholder="по:">
                    </div>
                    <div class="col-md-1">
                        <!--<button type="button" id="search" class="btn btn-success">Сформировать</button>-->
                        <sec:authorize access="hasAnyRole('controller')">
                        </div>
                        <div class="col-md-4">
                            <select id="department" class="form-control" required>
                                <c:forEach var="dept" items="${departmentList}">
                                    <option value="${dept.id}">${dept.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="button" id="searchAll" class="btn btn-success">По всем отделениям</button>
                    </sec:authorize>
                </div> 
                <br>
            </form>
            <form action="${pageContext.request.contextPath}/getDataForFillingCellsReportXls" method="POST">
                <input id="surnameHidden" class="form-control" name="status" type="hidden">
                <input id="currentDeptId" class="form-control" name="currentDeptId" type="hidden" value="${departmentId}">
                <input id="dateStartHidden" class="form-control" name="dateStartHidden" type="hidden" value="">
                <input id="dateEndHidden" class="form-control" name="dateEndHidden" type="hidden" value="">
                <input id="departmentIdHidden" class="form-control" name="deptIdHidden" type="hidden" value="">
                <div class="col-lg-offset-5">
                    <sec:authorize access="hasAnyRole('controller')">
                        <button type="submit" id="exportToExcel" disabled="disabled" class="btn btn-sm">Експортировать данные в Excel</button>
                    </sec:authorize>
                </div>
            </form>
            <br>
            <div class="profit-table">
                <table id="profitTable" class="table table-striped table-hover table-condensed">
                    <thead>
                        <tr>
                            <th>№ отд</th>
                            <th>Всего</th>
                            <th>Орендовано на начало</th>
                            <th>Открыто за период</th>
                            <th>Закрыто за период</th>
                            <th>Арендовано на конец</th>
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

                var profitTable = $("#profitTable").dataTable({
                    "aoColumns": [
                        {"sWidth": "37%"},
                        {"sWidth": "8%"},
                        {"sWidth": "16%"},
                        {"sWidth": "13%"},
                        {"sWidth": "13%"},
                        {"sWidth": "18%"}
                    ]
                });

                $("#department").change(function() {
                    var dateStart = $("#dateStart").val();
                    var dateEnd = $("#dateEnd").val();
                    var ds = dateStart.split('.');
                    var de = dateEnd.split('.');
                    var date1 = new Date(ds[2], parseInt(ds[1]) - 1, ds[0]);
                    var date2 = new Date(de[2], parseInt(de[1]) - 1, de[0]);
                    var departmentId = $("#department").val();
                    $("#dateStartHidden").val(date1);
                    $("#dateEndHidden").val(date2);
                    $("#departmentIdHidden").val(departmentId);

                    getInfoForFillingReport(date1, date2, departmentId).done(function(data) {
                        if (data && data.length > 0) {
                            createTable(data);
                            $('#exportToExcel').attr("disabled", false);
                        } else {
                            $('#exportToExcel').attr("disabled", true);
                        }
                    });
                });

                $("#searchAll").on('click', function() {
                    var dateStart = $("#dateStart").val();
                    var dateEnd = $("#dateEnd").val();
                    var ds = dateStart.split('.');
                    var de = dateEnd.split('.');
                    var date1 = new Date(ds[2], parseInt(ds[1]) - 1, ds[0]);
                    var date2 = new Date(de[2], parseInt(de[1]) - 1, de[0]);
                    var departmentId = null;
                    getInfoForFillingReport(date1, date2, departmentId).done(function(data) {
                        if (data && data.length > 0) {
                            createTable(data);
                            $('#exportToExcel').attr("disabled", false);
                        } else {
                            $('#exportToExcel').attr("disabled", true);
                        }
                    });
                });

                $("#exportToExcel").on('click', function() {
                    var dateStart = $("#dateStart").val();
                    var dateEnd = $("#dateEnd").val();
                    var ds = dateStart.split('.');
                    var de = dateEnd.split('.');
                    var date1 = new Date(ds[2], parseInt(ds[1]) - 1, ds[0]);
                    var date2 = new Date(de[2], parseInt(de[1]) - 1, de[0]);
                    $("#dateStartHidden").val(date1);
                    $("#dateEndHidden").val(date2);
                });

                function getInfoForFillingReport(date1, date2, departmentId) {
                    return $.ajax({
                        url: 'getInfoForFillingReport',
                        type: 'POST',
                        data: {
                            dateStart: date1,
                            dateEnd: date2,
                            departmentId: departmentId
                        }
                    });
                }

                function createTable(data) {
                    profitTable.fnClearTable();
                    $.each(data, function(key, val) {
                        profitTable.fnAddData([
                            val.departmentName,
                            val.countSafe,
                            val.openOnStartDate,
                            val.openContractByPeriod,
                            val.closeContractByPeriod,
                            val.openOnEndPeriod
                        ]);
                    });
                }
            });
        </script>
    </body>
</html>