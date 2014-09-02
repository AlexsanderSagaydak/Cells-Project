<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Клиенты</title>
        <LINK REL="SHORTCUT ICON" HREF="img/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link rel="stylesheet" href="css/jquery-ui.css">
        <style>
            .addBtn {
                margin-bottom: 30px;
            }
        </style>
    </head>
    <body>
        <div class="container" style="width: 1000px " >
            <h4 align="center"><b>${departamentId.name}</b></h4>
                    <%@include file="../jspf/header.jspf" %>
            <h3 align="center">Карточка відділення:</h3>
            <hr>
            <h5><b><u>Заповніть інформаційні поля:</u></b></h5>
            <div id="dataClientId" class="form-horizontal">
                <div class="row" align="center">
                    <div style="margin-top:20px" >
                        <label class="col-xs-3" ><h5>Повна назва підрозділу:</h5></label>
                        <div class="col-xs-4">
                            <input type="text" name="namedept" id="namedept" class="form-control" value="${deptinfo.departmentName}">
                            <input type="hidden" name="deptid" id="deptid" value="${departamentId.id}">
                            <input type="hidden" name="departmentCardId" id="departmentCardId" value="${deptinfo.id}">
                        </div>
                    </div>
                    <div style="margin-top:20px" >
                        <label class="col-xs-2" ><h5>Місто:</h5></label>
                        <div class="col-xs-2">
                            <input type="text" name="city" id="city" class="form-control required" value="${deptinfo.city}">
                        </div>
                    </div>
                </div>
                <div class="row" align="center">
                    <div style="margin-top:10px" >
                        <label class="col-xs-3" ><h5>Адреса відділення:</h5></label>
                        <div class="col-xs-4">
                            <input type="text" name="addrdept" id="addrdept" class="form-control required" value="${deptinfo.address}">
                        </div>
                    </div>
                    <div style="margin-top:10px" >
                        <label class="col-xs-2" ><h5>Телефон:</h5></label>
                        <div class="col-xs-2">
                            <input type="text" name="phonedept" id="phonedept" class="form-control required" value="${deptinfo.phone}">
                        </div>
                    </div>
                </div>
                <div class="row" align="center">
                    <div style="margin-top:10px">
                        <label class="col-sm-3 control-label"><h5>МФО:</h5></label>
                        <div class="col-xs-2">
                            <input type="text" class="form-control required" name="mfo" id="mfo" value="${deptinfo.mfo}">
                        </div>
                    </div>
                    <div>
                        <label class="col-sm-4 control-label"><h5>Ідентифікаційний код:</h5></label>
                        <div class="col-xs-2">
                            <input type="text" class="form-control required" name="inn" id="inn" value="${deptinfo.inn}">
                        </div>
                    </div>
                </div>
                <h5><b><u>Відомості про начальника:</u></b></h5>
                <div class="row" align="center">
                    <div style="margin-top:20px" >
                        <label class="col-xs-1" ><h5>Прізвище:</h5></label>
                        <div class="col-xs-3">
                            <input type="text" name="surname" id="surname" class="form-control required" value="${deptinfo.surname}">
                        </div>
                    </div>
                    <div >
                        <label class="col-sm-1 control-label"><h5>Ім'я:</h5></label>
                        <div class="col-xs-2">
                            <input type="text" class="form-control required" name="name" id="name" value="${deptinfo.name}">
                        </div>
                    </div>
                    <div>
                        <label class="col-sm-2 control-label"><h5>По-батькові:</h5></label>
                        <div class="col-xs-3">
                            <input type="text" class="form-control required" name="patronymic" id="patronymic" value="${deptinfo.patronymic}">
                        </div>
                    </div>
                </div>
                <div class="row" align="center">
                    <div style="margin-top:20px" >
                        <label class="col-xs-1" ><h5>Прізвище:</h5></label>
                        <div class="col-xs-3">
                            <input type="text" name="surnameGenitive" id="surnameGenitive" class="form-control required" value="${deptinfo.surnameGenitive}" placeholder="у родовому відмінку">
                        </div>
                    </div>
                    <div >
                        <label class="col-sm-1 control-label"><h5>Ім'я:</h5></label>
                        <div class="col-xs-2">
                            <input type="text" class="form-control required" name="nameGenitive" id="nameGenitive" value="${deptinfo.nameGenitive}" placeholder="у родовому відмінку">
                        </div>
                    </div>
                    <div>
                        <label class="col-sm-2 control-label"><h5>По-батькові:</h5></label>
                        <div class="col-xs-3">
                            <input type="text" class="form-control required" name="patronymicGenitive" id="patronymicGenitive" value="${deptinfo.patronymicGenitive}" placeholder="у родовому відмінку">
                        </div>
                    </div>
                </div>
                <div class="row" align="center">
                    <div style="margin-top:20px" >
                        <label class="col-xs-1" ><h5>Довіреність:</h5></label>
                        <div class="col-xs-2">
                            <input type="text" name="trustletter" id="trustletter" class="form-control required" value="${deptinfo.trustLetter}">
                        </div>
                    </div>
                    <div >
                        <label class="col-sm-1 control-label"><h5>від:</h5></label>
                        <div class="col-xs-2">
                            <input type="text" class="form-control required" name="trustletterdate" id="trustletterdate"  value="<fmt:formatDate value="${deptinfo.trustLetterDate}" pattern="dd.MM.yyyy" />">
                        </div>
                    </div>
                </div>
                <h5><b><u>Рахунки:</u></b></h5>
                <div class="row" align="center">
                    <div style="margin-top:20px" >
                        <label class="col-xs-2" ><h5>Рахунок касси:</h5></label>
                        <div class="col-xs-3">
                            <input type="text" name="cashaccount" id="cashaccount" class="form-control required" value="${deptinfo.cashAccount}">
                        </div>
                    </div>
                    <div>
                        <label class="col-sm-3 control-label"><h5>Рахунок для заставної вартості:</h5></label>
                        <div class="col-xs-3">
                            <input type="text" class="form-control required" name="outpostaccaunt" id="outpostaccaunt" value="${deptinfo.outpostAccount}">
                        </div>
                    </div>
                </div>
                <div class="row" align="center">
                    <div style="margin-top:20px" >
                        <label class="col-xs-2" ><h5>Рахунок олати оренди:</h5></label>
                        <div class="col-xs-3">
                            <input type="text" name="rentaccount" id="rentaccount" class="form-control required" value="${deptinfo.rentaccount}">
                        </div>
                    </div>
                    <div>
                        <div class="col-xs-3">
                            <button id="saveinfo" type="button" class="btn btn-success" >Оновити данні</button>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../jspf/footer.jspf" %>
        </div>

        <script type="text/javascript">
            $(function() {
                $("#trustletterdate").datepicker({
                    dateFormat: "dd.mm.yy",
                    monthNames: ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"],
                    firstDay: 1,
                    dayNamesMin: ["Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб"]
                });

                $("#saveinfo").click(function() {
                    var fieldsRequired = "";
                    $("#dataClientId input.required").each(function(index) {
                        var frmInptValue = $(this).val();
                        if (frmInptValue === "") {
                            fieldsRequired += $(this).parent("div").prev().html() + "\r\n";
                        }
                    });

                    if (fieldsRequired === "") {
                        $("#dataClientId").submit();
                        var departmentCardId = $('[name=departmentCardId]').val();
                        var namedept = $('[name=namedept]').val();
                        var deptid = $('[name=deptid]').val();
                        var addrdept = $('[name=addrdept]').val();
                        var phonedept = $('[name=phonedept]').val();
                        var surname = $('[name=surname]').val();
                        var name = $('[name=name]').val();
                        var patronymic = $('[name=patronymic]').val();
                        var surnameGenitive = $('[name=surnameGenitive]').val();
                        var nameGenitive = $('[name=nameGenitive]').val();
                        var patronymicGenitive = $('[name=patronymicGenitive]').val();
                        var trustletter = $('[name=trustletter]').val();
                        var trustletterdate = $('[name=trustletterdate]').val();
                        var ds = trustletterdate.split('.');
                        var date1 = new Date(ds[2], parseInt(ds[1]) - 1, ds[0]);
                        var mfo = $('[name=mfo]').val();
                        var inn = $('[name=inn]').val();
                        var cashaccount = $('[name=cashaccount]').val();
                        var outpostaccaunt = $('[name=outpostaccaunt]').val();
                        var rentaccount = $('[name=rentaccount]').val();
                        var city = $('[name=city]').val();

                        var departmentObject = {
                            id: departmentCardId,
                            deptartmentId: deptid,
                            departmentName: namedept,
                            address: addrdept,
                            phone: phonedept,
                            surname: surname,
                            name: name,
                            patronymic: patronymic,
                            surnameGenitive: surnameGenitive,
                            nameGenitive: nameGenitive,
                            patronymicGenitive: patronymicGenitive,
                            trustLetter: trustletter,
                            trustLetterDate: date1,
                            mfo: mfo,
                            inn: inn,
                            cashAccount: cashaccount,
                            outpostAccount: outpostaccaunt,
                            rentaccount: rentaccount,
                            city: city
                        };

                        var jsonObject = $.toJSON(departmentObject);
                        getDepartmentInfo(jsonObject).done(function(data) {
                            if (data.status === "ok") {
                                alert("Данні поновлено!");
                            }
                        });

                    } else {
                        alert("Не заполнены необходимые поля: \r\n" + fieldsRequired);
                    }
                });

                function getDepartmentInfo(jsonObject) {
                    return $.ajax({
                        url: 'getDepartmentInfo',
                        type: 'POST',
                        dataType: 'json',
                        contentType: "application/json; charset=utf-8",
                        data: jsonObject
                    });
                }
            });
        </script>
    </body>
</html>