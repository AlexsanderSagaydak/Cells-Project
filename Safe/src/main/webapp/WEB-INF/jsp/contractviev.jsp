<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Сейфы: Заключение договора</title>
        <LINK REL="SHORTCUT ICON" HREF="img/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="safe">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/jquery.pnotify.default.css" rel="stylesheet">
        <style>
        </style>
    </head>
    <body>
        <div class="container" style="width: 1000px">
            <h4 align="center"><b>${departament.name}</b></h4>
            <%@include file="../jspf/header.jspf" %>

            <!----------форма договора который был заключен клиентом---------------------------------------------->
            <div class="createdContractForm" id ="createdContractForm"
                 style="border: 2px solid #cccccc; border-radius: 10px; background-color: #cccccc; height: 450px;">
                <!--<button id="returnBtn" class="btn btn-default" role="button" style="width: 100%; margin-bottom: 15px;">Вернуться к выбору клиента</button>-->
                <input id="clientId" type="hidden">
                <div style="margin: 30px;" align ="center"><h3>Договор №${contract.numberContract} </h3></div>
                <div style="margin: 30px;"><h5>ФИО: ${contract.client1.surname} ${contract.client1.name} ${contract.client1.patronymic} </h5></div>
                <div style="margin: 30px;"><h5>ИНН: ${contract.client1.inn} </h5></div>

                <form class="form-horizontal" role="form" id ="createdContractForm">
                    <div class="form-group">
                        <label for="safeSize" class="col-md-2 control-label">Размер сейфа:</label>
                        <div class="col-md-3">
                            <input id="rentPeriod" class="form-control" value="${contract.model.height}*${contract.model.width}*${contract.model.depth}">
                        </div>
                        <label for="safeNumber" class="col-md-3 control-label">Номер сейфа:</label>
                        <div class="col-md-3">
                            <input id="safeNumber" class="form-control" value="${contract.safeNumber}" type="text" name = "safeNumber">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="rentPeriod" class="col-md-2 control-label">Срок аренды:</label>
                        <div class="col-md-3">
                            <input id="rentPeriod" class="form-control" value="${contract.monthRent}" type="text" name = "rentPeriod">
                        </div>
                        <label for="safeCostsInput" class="col-md-3 control-label">Стоимость, грн:</label>
                        <div class="col-md-3">
                            <input id="safeCosts"  class="form-control" type="text" value="${contract.price}" name="safeCosts">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="safeRentStartInput" class="col-md-2 control-label">Дата начала:</label>
                        <div class="col-md-3">
                            <input id="safeRentStart" class="form-control" value="${contract.dateContractStart}" type="text" name = "safeRentStart">
                        </div>
                        <label for="safeRentEnd" class="col-md-3 control-label">Дата окончания:</label>
                        <div class="col-md-3">
                            <input id="safeRentEnd" class="form-control" value="${contract.dateContractEnd}" type="text" name="safeRentEnd">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="safeKeyInput" class="col-md-2 control-label">Ключ, грн:</label>
                        <div class="col-md-3">
                            <input id="safeKey" class="form-control" value="${contract.key}" type="text" name="safeKey">
                        </div>
                    </div>

                    <div class="col-lg-offset-3" id = "buttonGroup1">
                        <br>
                        <div class="col-md-3">
                            <button id="closeContract" type="button" class="btn btn-danger"> Закрыть договор </button>
                        </div>
                        <div class="col-md-3">
                            <button id="prolongContract" type="button" class="btn btn-success" > Пролонгировать </button>
                        </div>
                        <div class="col-md-3">
                            <button id="delayContract" type="button" class="btn btn-success">&nbsp &nbsp Просрочка &nbsp &nbsp</button>
                        </div>
                    </div>
                    <div class="col-lg-offset-2" id = "buttonGroup2">
                        <br>
                        <div class="col-md-3">
                            <button id="prodlitContract" type="button" class="btn btn-default">&nbsp Продлить срок &nbsp</button>
                        </div>
                        <div class="col-md-3">
                            <button id="closeContract1" type="button" class="btn btn-danger"> Закрыть договор </button>
                        </div>
                    </div>
                </form>
            </div>
            <br>

            <!--форма пролонгирования------------------------------------------------------------>
            <div class="prolongationForm"
                 style="display: none; border: 2px solid #cccccc; border-radius: 10px; background-color: #cccccc; height: 200px;">
                <!--<button id="returnBtn" class="btn btn-default" role="button" style="width: 100%; margin-bottom: 15px;">Вернуться к выбору клиента</button>-->
                <input id="clientId" type="hidden">
                <form class="form-horizontal" role="form" id ="prolongationForm">
                    <br>
                    <div class="form-group">
                        <label for="rentPeriodContract" class="col-md-2 control-label">Срок аренды:</label>
                        <div class="col-md-3">
                            <select id="rentPeriodContract" class="form-control" required name="rentPeriodContract">
                                <option value="" selected disabled>Выберите...</option>
                                <option value="24">1 сутки</option>
                                <option value="1">1 мес.</option>
                                <option value="3">3 мес.</option>
                                <option value="4">4 мес.</option>
                                <option value="5">5 мес.</option>
                                <option value="6">6 мес.</option>
                                <option value="7">7 мес.</option>
                                <option value="8">8 мес.</option>
                                <option value="9">9 мес.</option>
                                <option value="10">10 мес.</option>
                                <option value="11">11 мес.</option>
                                <option value="12">12 мес.</option>
                            </select>
                        </div>
                        <label for="safeCostsInput" class="col-md-3 control-label">Стоимость, грн:</label>
                        <div class="col-md-3">
                            <input id="priceProlong"  class="form-control" type="text" value="${contract.price}" name="safeCosts">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="safeRentStartInput" class="col-md-2 control-label">Дата окончания:</label>
                        <div class="col-md-3">
                            <input id="safeRentStart" class="form-control" value="${contract.dateContractEnd}" type="text" name = "safeRentStart">
                        </div>
                        <label for="safeRentEnd" class="col-md-3 control-label">Новая дата окончания:</label>
                        <div class="col-md-3">
                            <input id="safeRentEndCange" class="form-control" value="${contract.dateContractEnd}" type="text" name="safeRentEnd">
                        </div>
                    </div>

                    <div class="col-lg-offset-3">
                        <br>
                        <div class="col-md-3">
                            <button id="returnToContract" type="button" class="btn btn-success">&nbsp &nbsp Назад &nbsp &nbsp</button>
                        </div>
                        <div class="col-md-3">
                            <button id="saveProlongContract" type="button" class="btn btn-success">Сформировать документ для оплаты</button>
                        </div>
                    </div>

                    <input id = "contractId" type="hidden" value = "${contract.id}">

                </form>
            </div>

            <input id="clientId" type="hidden">
            <input id = "contractId" type="hidden" value = "${contract.id}">
            <input id = "rentPerDay" type="hidden" value = "${contract.priceRentPerDay}">
            <input id = "safeId" type="hidden" value = "${contract.safeId}">

            <div class="delayContractForm"
                 style="display: none; border: 2px solid #cccccc; border-radius: 10px; background-color: #cccccc; height:150px;">
                <br>
                <form class="form-horizontal" role="form" id ="delayContractForm">
                    <div class="form-group">
                        <label for="rentPeriod" class="col-md-2 control-label">Стоимость в сутки, грн:</label>
                        <div class="col-md-3">
                            <input id="sumRentPerDay" class="form-control" value = "${contract.priceRentPerDay}" type="text" name = "rentPeriod">
                        </div>
                        <label for="allDebt" class="col-md-3 control-label">Задолженность, грн:</label>
                        <div class="col-md-3">
                            <input id="allDebt"  class="form-control" type="text">
                        </div>
                    </div>

                    <div class="col-lg-offset-3">
                        <br>
                        <div class="col-md-3">
                            <button id="closeDelayContract" type="button" class="btn btn-success">&nbsp &nbsp Назад &nbsp &nbsp</button>
                        </div>
                        <div class="col-md-3">
                            <button id="delay" type="button" class="btn btn-success">Оплатить просрочку</button>
                        </div>
                    </div>
                </form>
            </div>
                        
            <div class="closeContractForm"
                 style="display: none; border: 2px solid #cccccc; border-radius: 10px; background-color: #cccccc; height:110px;">
                <!--               <div  align ="center"><h5>Вы действительно хотите закрыть договор?</h5></div>-->
                <!--                <br>-->
                <form class="form-horizontal" role="form" id ="closeContractForm">
                    <div class="form-group">
                    </div>

                    <div class="col-lg-offset-3">
                        <br>
                        <div class="col-md-6">
                            <h4>Вы хотите закрыть договор?</h4>
                        </div>
                        <div class="col-md-3" align ="center">
                            <button id="closeWindowCloseContract" type="button" class="btn btn-success">&nbsp &nbsp Назад &nbsp &nbsp</button>
                        </div>
                        <div class="col-md-3">
                            <button id="close" type="button" class="btn btn-danger" >&nbsp Закрыть &nbsp</button>
                        </div>
                    </div>
                </form>
            </div>
                        
            <!------------------модальное окно-->
            <div id="searchModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="Поиск клиента" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button id="test" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>

                        </div>
                        <div class="modal-body">
                            <h4 class="modal-title">Вам необходимо либо продлить договор, либо закрыть</h4>

                            <!--                                    <button id="closeContract" type="button" class="btn btn-danger"> Закрыть договор </button>
                                                                <button id="prolongContract" type="button" class="btn btn-success" > Пролонгировать </button>-->
                        </div>
                        <div class="modal-footer">

                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../jspf/footer.jspf" %>
        </div>
        <script type="text/javascript">
            $(function() {
                var test = $("#test");
                test.on('click', function() {
                    $("#delayContract").prop("disabled", true);
                });

                var prodlitContract = $("#prodlitContract");
                prodlitContract.on('click', function() {
                    $('.prolongationForm').slideToggle('slow');
                    $("#closeContract1").prop("disabled", true);
                });

                var prolongContract = $("#prolongContract");
                prolongContract.on('click', function() {
                    $('.prolongationForm').slideToggle('slow');
                    $("#closeContract").prop("disabled", true);
                    $("#delayContract").prop("disabled", true);
                });

                var returnToContract = $("#returnToContract");
                returnToContract.on('click', function() {
                    $('.prolongationForm').slideToggle('slow');
                    $("#closeContract").prop("disabled", false);
                    $("#delayContract").prop("disabled", true);
                    $("#closeContract1").prop("disabled", false);
                });

                var delayContract = $("#delayContract");
                delayContract.on('click', function() {
                    $('.delayContractForm').slideToggle('slow');
                });

                var closeDelayContract = $("#closeDelayContract");
                closeDelayContract.on('click', function() {
                    $('.delayContractForm').slideToggle('slow');
                    $("#closeContract").prop("disabled", true);
                    $("#prolongContract").prop("disabled", true);
                    $("#delayContract").prop("disabled", false);
                });

                var closeContract = $("#closeContract");
                closeContract.on('click', function() {
                    $('.closeContractForm').slideToggle('slow');
                    $("#prolongContract").prop("disabled", true);
                    $("#delayContract").prop("disabled", true);
                });

                var closeContract = $("#closeContract1");
                closeContract.on('click', function() {
                    $('.closeContractForm').slideToggle('slow');
                    $("#prodlitContract").prop("disabled", true);
                });

                var closeWindowCloseContract = $("#closeWindowCloseContract");
                closeWindowCloseContract.on('click', function() {
                    $('.closeContractForm').slideToggle('slow');
                    $("#prolongContract").prop("disabled", false);
                    $("#delayContract").prop("disabled", true);
                    $("#prodlitContract").prop("disabled", false);
                });

//////////////////////////закритие контракта///////////////////////////////////
                var close = $('#close');
                var contractStatus = 0;
                var safeId = $("#safeId").val();
                close.on('click', function() {
                    $('.closeContractForm').slideToggle('slow');
                    $('.createdContractForm').slideToggle('slow');
                    var object = {
                        id: contractId.val(),
                        contractStatus: contractStatus,
                        safeId: safeId
                    };

                    var jsonObject = $.toJSON(object);
                    getContractStatus(jsonObject).done(function(data) {
                        window.location.href = 'test1.htm';
                    });
                });
                function getContractStatus(jsonObject) {
                    return $.ajax({
                        url: 'getContractStatus',
                        type: 'POST',
                        dataType: 'json',
                        contentType: "application/json; charset=utf-8",
                        data: jsonObject
                    });
                }

//////////////////////////пролонгирование контракта/////////////////////////////
                var safeRentEnd = $("#safeRentEndCange");
                var contractId = $("#contractId");
                var safeCosts = $("#priceProlong");
                var monthRent = $("#rentPeriodContract");
                var saveProlongContract = $("#saveProlongContract");
                saveProlongContract.on('click', function() {

                    var object = {
                        price: safeCosts.val(),
                        dateContractEnd: safeRentEnd.val(),
                        monthRent: monthRent.val(),
                        id: contractId.val()
                    };

                    var jsonObject = $.toJSON(object);
                    saveProlongContract(jsonObject).done(function(data) {
                        window.location.href = 'test2.htm';
                    });
                    function saveProlongContract(jsonObject) {
                        return $.ajax({
                            url: 'saveProlongContract',
                            type: 'POST',
                            dataType: 'json',
                            contentType: "application/json; charset=utf-8",
                            data: jsonObject
                        });
                    }
                });

////////////////////////////количество дней просрочки///////////////////////////
                var allDebt = $("#allDebt");
                var rentPerDay = $("#rentPerDay");
                getDelayDays(contractId.val()).done(function(data) {
                    if (data > 2) {
                        $('#buttonGroup1').hide();
                    } else {
                        $('#buttonGroup2').hide();
                    }
                    if (data < 0) {
                        var rezult = (data * rentPerDay.val()) * (-1);
                        allDebt.val(rezult);
                    }
                    if ($("#allDebt").val() !== "") {
                        $("#closeContract").attr("disabled", "disabled");
                        $("#prolongContract").attr("disabled", "disabled");
                    } else {
                        $("#delayContract").attr("disabled", "disabled");

                    }
                });
                function getDelayDays(contractId) {
                    var contractId = {contractId: contractId};
                    return $.ajax({
                        url: 'getDelayDays',
                        type: 'POST',
                        data: contractId
                    });
                }

//////////////////////////оплата задолженности//////////////////////////////////
                var delay = $("#delay");
                var allDebt = $("#allDebt");
                delay.on('click', function() {
                    $("#prolongContract").prop("disabled", false);
                    $("#closeContract").prop("disabled", false);

                    getDelaySum(contractId.val(), allDebt.val()).done(function(data) {
                        window.location.href = 'test3.htm';
                        alert("После оплаты задолженности необходимо закрыть договор, либо пролонгировать!");
                        $('.delayContractForm').slideToggle('slow');

                    });
                    function getDelaySum(contractId, allDebt) {
                        return $.ajax({
                            url: 'getDelaySum',
                            type: 'POST',
                            data: "contractId=" + contractId + "&allDebt=" + allDebt
                        });
                    }
                });

////////берем цены для пролонгирования, и новую дату продленного контракта//////
                var safeId = $("#safeId").val();
                var endDate = $("#safeRentEnd");
                var rentPeriodContract = $("#rentPeriodContract");
                rentPeriodContract.on('click', function() {
                    getPriceForPrContract(rentPeriodContract.val(), safeId).done(function(data) {
                        $("#priceProlong").val(data.price);
                    });
                    getEndContractPeriod(rentPeriodContract.val(), endDate.val()).done(function(data) {
                        $("#safeRentEndCange").val(data);
                    });
                });
                function getPriceForPrContract(rentPeriodContract, safeId) {
                    return $.ajax({
                        url: 'getPriceForProlongContract',
                        type: 'POST',
                        data: {
                            rentPeriodContract: rentPeriodContract,
                            safeId: safeId
                        }
                    });
                }
                function getEndContractPeriod(rentPeriodContract, endDate) {
                    return $.ajax({
                        url: 'getProlongEndContractPeriod',
                        type: 'POST',
                        data: {
                            rentPeriod: rentPeriodContract,
                            endDate: endDate
                        }
                    });
                }
////////////////////////////////////////////////////////////////////////////////
            });
        </script>
    </body>
</html>