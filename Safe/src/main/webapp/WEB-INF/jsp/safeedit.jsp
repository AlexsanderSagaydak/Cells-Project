<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Редактирование цен</title>
        <LINK REL="SHORTCUT ICON" HREF="img/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="safe">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/jquery.dataTables.css" rel="stylesheet">
        <link href="css/jquery.pnotify.default.css" rel="stylesheet">
        <style>
        </style>
    </head>
    <body>
        <div class="container" style="width: 1000px;">
            <h4 align="center"><b>${departament.name}</b></h4>
            <%@include file="../jspf/header.jspf" %>
            <h3 align="center">Редактирование цен по департаментам</h3>
            <form name="saveForm" class="form-horizontal">
                <input id="safeId" type="hidden" value="${safe.id}">

                <div class="form-group">
                    <label for="department" class="col-sm-2 control-label">Департамент:</label>
                    <div class="col-sm-4">
                        <select id="department" class="form-control" required>
                            <option value="${safe.department.id}" selected>${safe.department.name}</option>
                            <c:forEach var="dept" items="${departmentList}">
                                <option value="${dept.id}">${dept.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-4">
                        <button id="saveBtn" class="btn btn-success" type="button">Редактировать</button>
                        <a href="safes" class="btn btn-default">Назад</a>
                    </div>
                </div>
            </form>
                            
            <div class="safe-table">
                <table id="table" class="table table-striped table-striped">
                    <thead>
                        <tr>
                            <th>Высота, мм</th>
                            <th>Ширина, мм</th>
                            <th>Глубина, мм</th>
                            <th>Период, мес</th>
                            <th>Цена:</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <%@include file="../jspf/footer.jspf" %>
        </div>
        <script type="text/javascript">
            var oldValue;
            $(document).on("focus", "input.input-test", function() {
                oldValue = $(this).val();
            });
            $(document).on("blur", "input.input-test", function() {
                var newValue = $(this).val();
                var priceId = $(this).attr("model_id");

                if (oldValue !== newValue) {
                    getChangedModelPrice(priceId, newValue).done(function(data) {
                    });
                    function getChangedModelPrice(priceId, priceValue) {
                        return $.ajax({
                            url: 'getChangedModelPrice',
                            type: 'POST',
                            data: {
                                priceId: priceId,
                                priceValue: priceValue
                            }
                        });
                    }
                }
            });

            $(function() {
                var tableSafe = $("#table").dataTable();
                var saveBtn = $("#saveBtn");
                saveBtn.on('click', function() {
                    var deptId = $("#department option:selected").val();
                    var safeId = $("#safeId").val();
                    
                    getPricesAndModelsByDeptId(safeId, deptId).done(function(data) {
                        tableSafe.fnClearTable();
                        $.each(data, function(key, val) {
                            if ((val.price) === undefined) {
                                val.price = val.priceRent24;
                            }
                            if ((val.rentPeriod) === undefined) {
                                val.rentPeriod = "Цена за сутки";
                            }
                            tableSafe.fnAddData([
                                val.model.height,
                                val.model.width,
                                val.model.depth,
                                val.rentPeriod,
                                "<input model_id='" + val.id + "'size='2' style='text-align:center;' type='text' class='input-test' value='" + val.price + "' />"
                            ]);
                        });
                    });
                });

                function getPricesAndModelsByDeptId(modelId, deptId) {
                    return $.ajax({
                        url: 'getPricesAndModelsByDeptId',
                        type: 'POST',
                        data: {
                            modelId: modelId,
                            deptId: deptId
                        }
                    });
                }
            });
        </script>
    </body>
</html>