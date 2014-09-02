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
            <input id="clientLogin" type="hidden" value="${clientLogin}">
            <form name="saveForm" class="form-horizontal">
                <div class="form-group">
                    <label for="department" class="col-sm-2 control-label">Департамент:</label>
                    <div class="col-sm-7">
                        <select id="department" class="form-control" required>
                            <c:forEach var="dept" items="${departmentList}">
                                <option value="${dept.id}">${dept.name} ${dept.city} ${dept.address}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </form>
            <%@include file="../jspf/footer.jspf" %>
        </div>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#department").change(function() {
                    var departmentId = $("#department").val();
                    var clientLogin = $("#clientLogin").val();

                    getDeptIdAndLogin(departmentId, clientLogin).done(function(data) {
                        window.location.href = 'contractsinfo#all';
                    });
                });

                function getDeptIdAndLogin(departmentId, clientLogin) {
                    return $.ajax({
                        url: 'getDeptIdAndLogin',
                        type: 'POST',
                        data: {
                            departmentId: departmentId,
                            clientLogin: clientLogin
                        }
                    });
                }
            });
        </script>
    </body>
</html>
