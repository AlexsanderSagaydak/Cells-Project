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

        <!----------форма поиска клиента-->
        <div class="container" style="width: 1000px;">
            <h4 align="center"><b>${departament.name}</b></h4>
            <%@include file="../jspf/header.jspf" %>
            <div class="search-client" style="border: 2px solid #cccccc; border-radius: 10px; background-color: #cccccc">
                <div style="margin: 30px;"><h4>Поиск клиента</h4></div>
                <div class="row" style="margin: 10px;">
                    <div class="col-md-3">
                        <input  id="searchSurname" class="form-control required" type="text" placeholder="Фамилия">
                    </div>
                    <div class="col-md-3">
                        <input id="searchInn" class="form-control" type="text" placeholder="ИНН">
                    </div>
                    <button id="searchBtn" class="btn btn-default">Поиск</button>
                </div>
            </div>

            <!----------форма заполнения первого клиента-->
            <div class="add-client"
                 style="border: 2px solid #cccccc; border-radius: 10px; background-color: #cccccc; margin-top: 25px;">
                <div style="margin: 30px;"><h4>Добавление клиента</h4></div>

                <form id="dataClientId" class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-md-3 col-md-offset-2" style="text-align: center; color: #269abc">
                            Клиент
                        </div>
                        <div class="col-md-3 col-md-offset-3" style="text-align: center; color: #269abc">
                            Личные данные
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="surname" class="col-md-2 control-label ">Фамилия*:</label>
                        <div class="col-md-3">
                            <input  tabindex="1" id="surname" class="form-control required" type="text" maxlength="30">
                        </div>
                        <label for="gender" class="col-md-3 control-label">Пол:</label>
                        <div class="col-md-3">
                            <select tabindex="15" id="gender" class="form-control" type="text">
                                <option value="" selected disabled>Выберите...</option>
                                <option value="м">Мужской</option>
                                <option value="ж">Женский</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name" class="col-md-2 control-label">Имя*:</label>
                        <div class="col-md-3">
                            <input tabindex="2" id="name" class="form-control required" type="text" maxlength="20">
                        </div>
                        <label for="familyStatus" class="col-md-3 control-label">Семейное положение:</label>
                        <div class="col-md-3">
                            <select tabindex="16" id="familyStatus" class="form-control" type="text">
                                <option value="" selected disabled>Выберите...</option>
                                <option value="true">Состоит в браке</option>
                                <option value="false">Не состоит в браке</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="patronymic" class="col-md-2 control-label">Отчество:</label>
                        <div class="col-md-3">
                            <input  tabindex="3" id="patronymic" class="form-control" type="text" maxlength="20">
                        </div>
                        <label for="birthday" class="col-md-3 control-label">Дата рождения*:</label>
                        <div class="col-md-3">
                            <input tabindex="17" id="birthday" class="form-control" type="text" placeholder="дд.мм.гггг">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inn" class="col-md-2 control-label">ИНН*:</label>
                        <div class="col-md-3">
                            <input <input tabindex="4" id="inn" class="form-control required" type="text" placeholder="10 цифр">
                        </div>
                        <label for="birthplace" class="col-md-3 control-label">Место рождения:</label>
                        <div class="col-md-3">
                            <input tabindex="18" id="birthplace" class="form-control" type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-3 col-md-offset-2" style="text-align: center; color: #269abc">
                            Паспорт
                        </div>
                        <div class="col-md-3 col-md-offset-3" style="text-align: center; color: #269abc">
                            Контактные данные
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="passportSeries" class="col-md-2 control-label">Серия*:</label>
                        <div class="col-md-3">
                            <input  tabindex="5" id="passportSeries" class="form-control required" type="text" placeholder="XX" maxlength="3">
                        </div>
                        <label for="homePhone" class="col-md-3 control-label">Домашний телефон:</label>
                        <div class="col-md-3">
                            <input tabindex="19" id="homePhone" class="form-control" type="text" placeholder="(xxx)xxxxxxx">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="passportNumber" class="col-md-2 control-label">Номер*:</label>
                        <div class="col-md-3">
                            <input tabindex="6"  id="passportNumber" class="form-control required" type="text" maxlength="10">
                        </div>
                        <label for="mobilePhone1" class="col-md-3 control-label">Мобильный телефон*:</label>
                        <div class="col-md-3">
                            <input tabindex="20" id="mobilePhone1" class="form-control" type="text" placeholder="+38xxxxxxxxxx">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="passportIssued" class="col-md-2 control-label">Дата выдачи*:</label>
                        <div class="col-md-3">
                            <input tabindex="7" id="passportIssued" class="form-control required" type="text" placeholder="дд.мм.гггг">
                        </div>
                        <label for="mobilePhone2" class="col-md-3 control-label">Дополнительный телефон:</label>
                        <div class="col-md-3">
                            <input tabindex="21" id="mobilePhone2" class="form-control" type="text" maxlength="20">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="passportIssuedBy" class="col-md-2 control-label">Кем выдан*:</label>
                        <div class="col-md-3">
                            <input tabindex="7" id="passportIssuedBy" class="form-control required" type="text">
                        </div>
                        <label for="email" class="col-md-3 control-label">Email:</label>
                        <div class="col-md-3">
                            <input tabindex="22" id="email" class="form-control" type="email" placeholder="___________@_____" maxlength="30">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-3 col-md-offset-2" style="text-align: center; color: #269abc">
                            Адрес
                        </div>
                        <div class="col-md-3 col-md-offset-3" style="text-align: center; color: #269abc">
                            Информация о работе
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="country" class="col-md-2 control-label">Страна:</label>
                        <div class="col-md-3">
                            <input tabindex="8" id="country" class="form-control" type="text" maxlength="20">
                        </div>
                        <label for="organization" class="col-md-3 control-label">Организация:</label>
                        <div class="col-md-3">
                            <input tabindex="23" id="organization" class="form-control" type="text" maxlength="25">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="region" class="col-md-2 control-label">Область*:</label>
                        <div class="col-md-3">
                            <input tabindex="9" id="region" class="form-control required" type="text" maxlength="20">
                        </div>
                        <label for="position" class="col-md-3 control-label">Должность:</label>
                        <div class="col-md-3">
                            <input tabindex="24" id="position" class="form-control" type="text" maxlength="20">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="district" class="col-md-2 control-label">Район:</label>
                        <div class="col-md-3">
                            <input tabindex="10" id="district" class="form-control" type="text" maxlength="20">
                        </div>
                        <div class="col-md-3 col-md-offset-3" style="text-align: center; color: #269abc">
                            Резидентность*
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="city" class="col-md-2 control-label">Город*:</label>
                        <div class="col-md-3">
                            <input tabindex="11" id="city" class="form-control required" type="text" maxlength="25">
                        </div>
                        <div class="col-md-3 col-md-offset-3">
                            <div class="checkbox">
                                <label>
                                    <input tabindex="25" id="isResident" type="checkbox"> Резидент*
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="street" class="col-md-2 control-label">Улица*:</label>
                        <div class="col-md-3">
                            <input tabindex="12" id="street" class="form-control required" type="text" maxlength="25">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="home" class="col-md-2 control-label">Дом*:</label>
                        <div class="col-md-3">
                            <input tabindex="13" id="home" class="form-control required" type="text" maxlength="20">
                        </div>
                        <div class="col-md-3 col-md-offset-3">
                            <button id="joinRental" type="button" class="btn btn-default" disabled="disabled">Совместная аренда</button>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="flat" class="col-md-2 control-label">Квартира*:</label>
                        <div class="col-md-3">
                            <input tabindex="14" id="flat" class="form-control required" type="text" maxlength="20">
                        </div>
                        <div class="col-md-3 col-md-offset-3">
                            <button id="saveClient" type="button" class="btn btn-default" disabled="disabled">Сохранить</button>
                        </div>
                    </div>
                    <button id="nextBtn" class="btn btn-default" type="button"  disabled="disabled" style="width: 100%; margin-top: 25px;">Далее</button>
                </form>
            </div>

            <!----------форма заключения договора-->
            <div class="add-contract"
                 style="display: none; border: 2px solid #cccccc; border-radius: 10px; background-color: #cccccc; height: 400px;">
                <button id="returnBtn" class="btn btn-default" role="button" style="width: 100%; margin-bottom: 15px;">Вернуться к выбору клиента</button>
                <input id="clientId" type="hidden">
                <div style="margin: 30px;"><h4>Форма заключения договора</h4></div>

                <form class="form-horizontal" role="form" id ="saveContractForm">
                    <div class="form-group">
                        <label for="safeSize" class="col-md-2 control-label">Размер сейфа:</label>
                        <div class="col-md-3">
                            <select id="safeModel" class="form-control" required name="safeModel">
                                <option value="" selected disabled>Выберите...</option>
                                <c:forEach var="model" items="${modelList}">
                                    <option value="${model.id}">${model.height}x${model.width}x${model.depth}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <label for="safeNumber" class="col-md-3 control-label">Номер сейфа:</label>
                        <div class="col-md-3">
                            <select id="safeNumber" name="safeNumber" class="form-control" required>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="rentPeriod" class="col-md-2 control-label">Срок аренды:</label>
                        <div class="col-md-3">
                            <select id="rentPeriod" class="form-control required" required name="rentPeriod">
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
                            <input id="safeCosts"  class="form-control required" type="text" name="safeCosts">
                        </div>
                    </div>
                    <c:set var="now" value="<%=new java.util.Date()%>" />

                    <div class="form-group">
                        <label for="safeRentStartInput" class="col-md-2 control-label">Дата начала:</label>

                        <div class="col-md-3">
                            <input id="safeRentStart" class="form-control required" value="<fmt:formatDate pattern="dd.MM.yyyy" value="${now}" />" type="text" name = "safeRentStart">
                        </div>

                        <label for="safeRentEnd" class="col-md-3 control-label">Дата окончания:</label>
                        <div class="col-md-3">
                            <input id="safeRentEnd" class="form-control required"  type="text" name="safeRentEnd">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="safeKeyInput" class="col-md-2 control-label">Ключ, грн:</label>
                        <div class="col-md-3">
                            <input id="safeKey"  class="form-control required" type="text" name="safeKey">
                        </div>
                        <label for="createContractButton" class="col-md-3 control-label">Документы:</label>
                        <div class="col-md-3">
                            <button id="createContractButton" type="button" class="btn btn-default">&nbsp Сформировать &nbsp</button>
                        </div>
                    </div>
                    <input id="contractStatus" type ="hidden" value ="1" type="text">
                </form>
            </div>


            <!----------форма заполнения второго клиента-->
            <div class="previous-client"
                 style="display: none; border: 2px solid #cccccc; border-radius: 10px; background-color: #cccccc; margin-top: 25px;">
                <button id="returnBtnPrevious" class="btn btn-default" role="button" style="width: 100%; margin-bottom: 15px;">
                    Вернуться к первому клиенту
                </button>
                <!--                <div style="margin: 30px;"><h4>Добавление клиента</h4></div>-->
                <form class="form-horizontal" role="form2">

                    <div class="form-group">
                        <div class="col-md-3 col-md-offset-2" style="text-align: center; color: #269abc">
                            Клиент
                        </div>
                        <div class="col-md-3 col-md-offset-3" style="text-align: center; color: #269abc">
                            Личные данные
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="surname1" class="col-md-2 control-label">Фамилия*:</label>
                        <div class="col-md-3">
                            <input tabindex="26" id="surname1" readonly="readonly" class="form-control required" type="text" >
                        </div>
                        <label for="gender" class="col-md-3 control-label">Пол:</label>
                        <div class="col-md-3">
                            <select  tabindex="41" id="gender1" class="form-control" type="text">
                                <option value="" selected disabled>Выберите...</option>
                                <option value="м">Мужской</option>
                                <option value="ж">Женский</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="name1" class="col-md-2 control-label">Имя*:</label>
                        <div class="col-md-3">
                            <input  tabindex="27" id="name1" readonly="readonly" class="form-control required" type="text">
                        </div>
                        <label for="familyStatus1" class="col-md-3 control-label">Семейное положение:</label>
                        <div class="col-md-3">
                            <select  tabindex="42" id="familyStatus1" class="form-control" type="text">
                                <option value="" selected disabled>Выберите...</option>
                                <option value="true">Состоит в браке</option>
                                <option value="false">Не состоит в браке</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="patronymic1" class="col-md-2 control-label">Отчество*:</label>
                        <div class="col-md-3">
                            <input  tabindex="28" id="patronymic1" readonly="readonly" class="form-control required" type="text">
                        </div>
                        <label for="birthday1" class="col-md-3 control-label">Дата рождения*:</label>
                        <div class="col-md-3">
                            <input  tabindex="43" id="birthday1"  readonly="readonly" class="form-control required" type="text" placeholder="дд.мм.гггг">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inn1" class="col-md-2 control-label">ИНН*:</label>
                        <div class="col-md-3">
                            <input  tabindex="29" id="inn1" readonly="readonly" class="form-control required" type="text">
                        </div>
                        <label for="birthplace1" readonly="readonly" class="col-md-3 control-label">Место рождения*:</label>
                        <div class="col-md-3">
                            <input  tabindex="44" id="birthplace1" readonly="readonly"  type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-3 col-md-offset-2" style="text-align: center; color: #269abc">
                            Паспорт
                        </div>
                        <div class="col-md-3 col-md-offset-3" style="text-align: center; color: #269abc">
                            Контактные данные
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="passportSeries1" class="col-md-2 control-label">Серия*:</label>
                        <div class="col-md-3">
                            <input  tabindex="30" id="passportSeries1" readonly="readonly" class="form-control required" type="text">
                        </div>
                        <label for="homePhone1" class="col-md-3 control-label">Домашний телефон:</label>
                        <div class="col-md-3">
                            <input  tabindex="45" id="homePhone1" readonly="readonly" class="form-control" type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="passportNumber1" class="col-md-2 control-label">Номер*:</label>
                        <div class="col-md-3">
                            <input  tabindex="31" id="passportNumber1" readonly="readonly" class="form-control required" type="text">
                        </div>
                        <label for="mobilePhone1" class="col-md-3 control-label">Мобильный телефон*:</label>
                        <div class="col-md-3">
                            <input  tabindex="46" id="mobilePhone11" readonly="readonly" class="form-control required" type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="passportIssued1" class="col-md-2 control-label">Дата выдачи*:</label>
                        <div class="col-md-3">
                            <input  tabindex="32" id="passportIssued1" readonly="readonly" class="form-control required" type="text" >
                        </div>
                        <label for="mobilePhone21" class="col-md-3 control-label">Дополнительный телефон:</label>

                        <div class="col-md-3">
                            <input  tabindex="47" id="mobilePhone21" readonly="readonly" class="form-control" type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="passportIssuedBy1" class="col-md-2 control-label">Кем выдан*:</label>
                        <div class="col-md-3">
                            <input  tabindex="33" id="passportIssuedBy1" readonly="readonly" class="form-control required" type="text">
                        </div>
                        <label for="email1" class="col-md-3 control-label">Email:</label>

                        <div class="col-md-3">
                            <input  tabindex="48" id="email1" readonly="readonly" class="form-control" type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-3 col-md-offset-2" style="text-align: center; color: #269abc">
                            Адрес
                        </div>
                        <div class="col-md-3 col-md-offset-3" style="text-align: center; color: #269abc">
                            Информация о работе
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="country1" class="col-md-2 control-label">Страна*:</label>
                        <div class="col-md-3">
                            <input  tabindex="34" id="country1" readonly="readonly" class="form-control required" type="text">
                        </div>
                        <label for="organization1" class="col-md-3 control-label">Организация:</label>
                        <div class="col-md-3">
                            <input  tabindex="49" id="organization1" readonly="readonly" class="form-control" type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="region1" class="col-md-2 control-label">Область*:</label>
                        <div class="col-md-3">
                            <input  tabindex="35" id="region1" readonly="readonly" class="form-control required" type="text">
                        </div>
                        <label for="position1" class="col-md-3 control-label">Должность:</label>
                        <div class="col-md-3">
                            <input  tabindex="50" id="position1" readonly="readonly" class="form-control" type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="district1" class="col-md-2 control-label">Район*:</label>
                        <div class="col-md-3">
                            <input  tabindex="36" id="district1" readonly="readonly" class="form-control required" type="text">
                        </div>
                        <div class="col-md-3 col-md-offset-3" style="text-align: center; color: #269abc">
                            Резидентность
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="city1" class="col-md-2 control-label">Город*:</label>
                        <div class="col-md-3">
                            <input  tabindex="37" id="city1" readonly="readonly" class="form-control required" type="text">
                        </div>
                        <div class="col-md-3 col-md-offset-3">
                            <div class="checkbox">
                                <label>
                                    <input  tabindex="51" id="isResident1" type="checkbox"> Резидент*
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="street1" class="col-md-2 control-label">Улица*:</label>
                        <div class="col-md-3">
                            <input  tabindex="38" id="street1" readonly="readonly" class="form-control required" type="text">
                        </div>
                    </div> 

                    <div class="form-group">
                        <label for="home1" class="col-md-2 control-label">Дом*:</label>

                        <div class="col-md-3">
                            <input  tabindex="39" id="home1" readonly="readonly" class="form-control required" type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="flat1" class="col-md-2 control-label">Квартира*:</label>
                        <div class="col-md-3">
                            <input  tabindex="40" id="flat1" readonly="readonly" class="form-control required" type="text">
                        </div>
                        <div class="col-md-3 col-md-offset-3">
                            <!--                            <button id="saveClient2" type="button" class="btn btn-default" disabled="disabled">Сохранить</button>-->
                        </div>
                    </div>

                    <button id="nextBtnFromSecondClient" class="btn btn-default" type="button" disabled="disabled" style="width: 100%; margin-top: 25px;">Далее </button>

                    <input id="clientId2" type="hidden">

                    </div>
                    <%@include file="../jspf/footer.jspf" %>

                    <!------------------модальное окно-->
                    <div id="searchModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="Поиск клиента" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title">Поиск клиента</h4>
                                </div>
                                <div class="modal-body">
                                    Найден клиент:
                                    <div id="foundFullName"></div>
                                    <div id="foundPassport"></div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                                    <button id="choiceClientBtn" type="button" class="btn btn-primary">Выбрать</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <script type="text/javascript">
                        var buttonFlagIsChoiseSecondClient = false;
                        $(function() {
                            var createContract = $("#createContractButton");
                            var safeModelInput = $("#safeModel");
                            var rentPeriodInput = $("#rentPeriod");
                            var searchBtn = $('#searchBtn');
                            var choiceClientBtn = $('#choiceClientBtn');
                            var nextBtn = $('#nextBtn');
                            var nextBtnFromSecondClient = $('#nextBtnFromSecondClient');
                            var joinRental = $('#joinRental');
                            var returnBtn = $('#returnBtn');
                            var returnBtnPrevious = $('#returnBtnPrevious');
                            var searchModal = $('#searchModal');
                            var contractStatus = $('#contractStatus');
////////////////////////////данные первого арендатора
                            var client;
                            var nameInput = $('#name');
                            var surnameInput = $('#surname');
                            var familyStatusInput = $('#familyStatus');
                            var patronymicInput = $('#patronymic');
                            var birthdayInput = $('#birthday');
                            var innInput = $('#inn');
                            var birthplaceInput = $('#birthplace');
                            var passportSeriesInput = $('#passportSeries');
                            var homePhoneInput = $('#homePhone');
                            var passportNumberInput = $('#passportNumber');
                            var mobilePhone1Input = $('#mobilePhone1');
                            var passportIssuedInput = $('#passportIssued');
                            var mobilePhone2Input = $('#mobilePhone2');
                            var passportIssuedByInput = $('#passportIssuedBy');
                            var emailInput = $('#email');
                            var countryInput = $('#country');
                            var organizationInput = $('#organization');
                            var regionInput = $('#region');
                            var positionInput = $('#position');
                            var districtInput = $('#district');
                            var cityInput = $('#city');
                            var isResidentInput = $('#isResident');
                            var streetInput = $('#street');
                            var homeInput = $('#home');
                            var flatInput = $('#flat');
                            var genderInput = $('#gender');
                            var clientIdInput = $('#clientId');
////////////////////////////данные второго арендатора
                            var nameInput1 = $('#name1');
                            var surnameInput1 = $('#surname1');
                            var genderInput1 = $('#gender1');
                            var streetInput1 = $('#street1');
                            var homeInput1 = $('#home1');
                            var flatInput1 = $('#flat1');
                            var cityInput1 = $('#city1');
                            var isResidentInput1 = $('#isResident1');
                            var familyStatusInput1 = $('#familyStatus1');
                            var patronymicInput1 = $('#patronymic1');
                            var birthdayInput1 = $('#birthday1');
                            var innInput1 = $('#inn1');
                            var birthplaceInput1 = $('#birthplace1');
                            var passportSeriesInput1 = $('#passportSeries1');
                            var homePhoneInput1 = $('#homePhone1');
                            var passportNumberInput1 = $('#passportNumber1');
                            var mobilePhone1Input1 = $('#mobilePhone11');
                            var passportIssuedInput1 = $('#passportIssued1');
                            var mobilePhone2Input1 = $('#mobilePhone21');
                            var passportIssuedByInput1 = $('#passportIssuedBy1');
                            var emailInput1 = $('#email1');
                            var countryInput1 = $('#country1');
                            var organizationInput1 = $('#organization1');
                            var regionInput1 = $('#region1');
                            var positionInput1 = $('#position1');
                            var districtInput1 = $('#district1');
                            var clientIdInput1 = $('#clientId2');
////////////////////////////данные формы контракта
                            var safeModel = $('#safeModel');
                            var safeNumber = $('#safeNumber');
                            var rentPeriod = $('#rentPeriod');
                            var safeCosts = $('#safeCosts');
                            var safeRentStart = $('#safeRentStart');
                            var safeRentEnd = $('#safeRentEnd');
                            var safeKey = $('#safeKey');
////////////////////////////получение клиента с сервера и заполнение модального окна данними
                            searchBtn.on('click', function() {
                                var surname = $('#searchSurname').val();
                                var inn = $('#searchInn').val();
                                searchClient(surname, inn).done(function(data) {
                                    if (data !== '') {
                                        client = data;
                                        var fullName = 'ФИО: ' + data.surname + ' ' + data.name + ' ' + data.patronymic;
                                        var passport = 'Паспорт: ' + data.series + " " + data.number;
                                        $('#foundFullName').text(fullName);
                                        $('#foundPassport').text(passport);
                                        searchModal.modal('show');
                                    } else {
                                        alert("Проверьте данные, возможно такого клиента не существует!");
                                    }
                                });
                            });
////////////////////////////проверка на существование клиентов 1 и 2 в БД по ИНН и серии пасспорта                            
                            $('#inn').change(function() {
                                var inn = $(this).val();
                                checkClient(inn).done(function(data) {
                                    if (data.status === "error") {
                                        alert("Такой клиент уже существует!");
                                    }
                                });
                            });
/////////////////////////////маска ввода данных    
                            passportIssuedInput.mask("99.99.9999");
                            birthdayInput.mask("99.99.9999");
                            homePhoneInput.mask("(999)9999999");
                            mobilePhone1Input.mask("+389999999999");
                            innInput.mask("9999999999");

                            $('#passportNumber, #mobilePhone2').bind('keyup blur', function() {
                                $(this).val($(this).val().replace(/[^0-9]/g, ''));
                            });

                            $('#surname, #name, #patronymic, #country, #region,\n\
                                #city, #organization, #position').bind('keyup blur', function() {
                                $(this).val($(this).val().replace(/[^а-яёіїє ’]/i, ''));
                            });

////////////////////////////заполнение формы клиента1 и клиента2 соответственно                            
                            choiceClientBtn.on('click', function() {
                                  $("#joinRental").prop("disabled", false);
                                  $("#nextBtn").prop("disabled", false);
                                
                                if (buttonFlagIsChoiseSecondClient === false) {
                                    searchModal.modal('hide');
                                    nameInput.val(client.name);
                                    surnameInput.val(client.surname);
                                    familyStatusInput.val(client.isMarried.toString());
                                    patronymicInput.val(client.patronymic);
                                    birthdayInput.val(client.birthday);
                                    innInput.val(client.inn);
                                    birthplaceInput.val(client.birthplace);
                                    passportSeriesInput.val(client.series);
                                    homePhoneInput.val(client.homePhone);
                                    passportNumberInput.val(client.number);
                                    mobilePhone1Input.val(client.mobilePhone1);
                                    passportIssuedInput.val(client.issued);
                                    mobilePhone2Input.val(client.mobilePhone2);
                                    passportIssuedByInput.val(client.issuedBy);
                                    emailInput.val(client.email);
                                    countryInput.val(client.country);
                                    organizationInput.val(client.organization);
                                    regionInput.val(client.region);
                                    positionInput.val(client.position);
                                    districtInput.val(client.district);
                                    cityInput.val(client.city);
                                    streetInput.val(client.street);
                                    homeInput.val(client.home);
                                    flatInput.val(client.flat);
                                    genderInput.val(client.gender);
                                    setIsResident(isResidentInput, client.isResident);
                                    clientIdInput.val(client.id);
                                } else {
                                    searchModal.modal('hide');
                                    nameInput1.val(client.name);
                                    surnameInput1.val(client.surname);
                                    genderInput1.val(client.gender);
                                    streetInput1.val(client.street);
                                    homeInput1.val(client.home);
                                    flatInput1.val(client.flat);
                                    cityInput1.val(client.city);
                                    setIsResident(isResidentInput1, client.isResident);
                                    familyStatusInput1.val(client.isMarried.toString());
                                    patronymicInput1.val(client.patronymic);
                                    birthdayInput1.val(client.birthday);
                                    innInput1.val(client.inn);
                                    birthplaceInput1.val(client.birthplace);
                                    passportSeriesInput1.val(client.series);
                                    homePhoneInput1.val(client.homePhone);
                                    passportNumberInput1.val(client.number);
                                    mobilePhone1Input1.val(client.mobilePhone1);
                                    passportIssuedInput1.val(client.issued);
                                    mobilePhone2Input1.val(client.mobilePhone2);
                                    passportIssuedByInput1.val(client.issuedBy);
                                    emailInput1.val(client.email);
                                    countryInput1.val(client.country);
                                    organizationInput1.val(client.organization);
                                    regionInput1.val(client.region);
                                    positionInput1.val(client.position);
                                    districtInput1.val(client.district);
                                    clientIdInput1.val(client.id);
                                }
                                  if(surnameInput1.val() !==""){
                                      $("#nextBtnFromSecondClient").prop("disabled", false);
                                  }
                            });
                            nextBtn.on('click', function() {
                                $('.search-client').slideToggle('slow');
                                $('.add-client').slideToggle('slow');
                                $('.add-contract').slideToggle('slow');
                            });
                            returnBtn.on('click', function() {
                                $('.add-contract').slideToggle('slow');
                                $('.search-client').slideToggle('slow');
                                $('.add-client').slideToggle('slow');
                            });
                            function searchClient(surname, inn) {
                                return $.ajax({
                                    url: 'searchClient',
                                    type: 'POST',
                                    data: {
                                        surname: surname,
                                        inn: inn
                                    }
                                });
                            }

                            function addClient(jsonObject) {
                                return $.ajax({
                                    url: 'client',
                                    type: 'POST',
                                    dataType: 'json',
                                    contentType: "application/json; charset=utf-8",
                                    data: jsonObject
                                });
                            }

                            function setIsResident(element, boolean) {
                                if (boolean) {
                                    element.attr('checked', 'checked');
                                } else {
                                    element.attr('checked', '');
                                }
                            }

                            function getCheckBoxValue(element) {
                                if (element.is(':checked')) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }

                            function checkClient(checkfield) {
                                return $.ajax({
                                    url: 'checkClient',
                                    type: 'POST',
                                    data: {
                                        checkfield: checkfield
                                    }
                                });
                            }

                            joinRental.on('click', function() {
                                $('.add-client').slideToggle('slow');
                                $('.previous-client').slideToggle('slow');
                                buttonFlagIsChoiseSecondClient = true;
                            });
                            nextBtnFromSecondClient.on('click', function() {
                                $('.search-client').slideToggle('slow');
                                $('.previous-client').slideToggle('slow');
                                $('.add-contract').slideToggle('slow');
                            });
                            returnBtnPrevious.on('click', function() {
                                $('.add-client').slideToggle('slow');
                                $(".previous-client").hide();
                            });
                            $('.form-horizontal').change(function() {
                                $('#saveClient').attr("disabled", false);
                                $('#saveClient2').attr("disabled", false);
                            });
                            $('#saveClient, #saveClient2').on('click', function() {

                                var fieldsRequired = "";
                                $("#dataClientId input.required").each(function(index) {
                                    var frmInptValue = $(this).val();
                                    if (frmInptValue === "") {
                                        fieldsRequired += $(this).parent("div").prev().html() + "\r\n";
                                    }
                                });
                                if (fieldsRequired === "") {
                                    $("#dataClientId").submit();
                                } else {
                                    alert("Не заполнены необходимые поля: \r\n" + fieldsRequired);
                                }

                                var clientsObject = {
                                    surname: surnameInput.val(),
                                    name: nameInput.val(),
                                    patronymic: patronymicInput.val(),
                                    inn: innInput.val(),
                                    series: passportSeriesInput.val(),
                                    number: passportNumberInput.val(),
                                    issued: passportIssuedInput.val(),
                                    issuedBy: passportIssuedByInput.val(),
                                    country: countryInput.val(),
                                    region: regionInput.val(),
                                    district: districtInput.val(),
                                    city: cityInput.val(),
                                    street: streetInput.val(),
                                    home: homeInput.val(),
                                    flat: flatInput.val(),
                                    gender: genderInput.val(),
                                    isMarried: familyStatusInput.val(),
                                    birthday: birthdayInput.val(),
                                    birthplace: birthplaceInput.val(),
                                    homePhone: homePhoneInput.val(),
                                    mobilePhone1: mobilePhone1Input.val(),
                                    mobilePhone2: mobilePhone2Input.val(),
                                    email: emailInput.val(),
                                    organization: organizationInput.val(),
                                    position: positionInput.val(),
                                    isResident: getCheckBoxValue(isResidentInput),
                                    id: clientIdInput.val()
                                };
                                var jsonObject = $.toJSON(clientsObject);
                                addClient(jsonObject).done(function(data) {

                                });
                            });
                            $('#saveClient2').on('click', function() {
                                var clientsObject = {
                                    surname: surnameInput1.val(),
                                    name: nameInput1.val(),
                                    patronymic: patronymicInput1.val(),
                                    inn: innInput1.val(),
                                    series: passportSeriesInput1.val(),
                                    number: passportNumberInput1.val(),
                                    issued: passportIssuedInput1.val(),
                                    issuedBy: passportIssuedByInput1.val(),
                                    country: countryInput1.val(),
                                    region: regionInput1.val(),
                                    district: districtInput1.val(),
                                    city: cityInput1.val(),
                                    street: streetInput1.val(),
                                    home: homeInput1.val(),
                                    flat: flatInput1.val(),
                                    gender: genderInput1.val(),
                                    isMarried: familyStatusInput1.val(),
                                    birthday: birthdayInput1.val(),
                                    birthplace: birthplaceInput1.val(),
                                    homePhone: homePhoneInput1.val(),
                                    mobilePhone1: mobilePhone1Input1.val(),
                                    mobilePhone2: mobilePhone2Input1.val(),
                                    email: emailInput1.val(),
                                    organization: organizationInput1.val(),
                                    position: positionInput1.val(),
                                    isResident: getCheckBoxValue(isResidentInput1),
                                    id: clientIdInput1.val()
                                };
                                var jsonObject = $.toJSON(clientsObject);
                                addClient(jsonObject).done(function(data) {

                                });
                            });
                            safeModelInput.on('click', function() {
                                var modelId = $(this).val();
                                var safeNumberInput = " ";
                                getFreeSafes(modelId).done(function(data) {
                                    $.each(data, function(key, value) {
                                        safeNumberInput += '<option value=' + value.id + '>' + value.code + '</option>';
                                        $("#safeNumber").empty().append(safeNumberInput);
                                    });
                                });
                            });
                            function getFreeSafes(modelId) {
                                return $.ajax({
                                    url: 'getSafesForContract',
                                    type: 'POST',
                                    data: {
                                        modelId: modelId
                                    }
                                });
                            }
                            
                            rentPeriodInput.on('click', function() {
                                var rentPeriod = $(this).val();
                                var modelId = safeModelInput.val();
                                getPriceForReport(rentPeriod, modelId).done(function(data) {
                                    $("#safeCosts").val(data.price);
                                });
                                getEndContractPeriod(rentPeriod).done(function(data) {
                                    $("#safeRentEnd").val(data);
                                });
                            });
                            
                            function getPriceForReport(rentPeriod, modelId) {
                                return $.ajax({
                                    url: 'getPriceForContract',
                                    type: 'POST',
                                    data: {
                                        rentPeriod: rentPeriod,
                                        modelId: modelId
                                    }
                                });
                            }

                            function getEndContractPeriod(rentPeriod) {
                                return $.ajax({
                                    url: 'getEndContractPeriod',
                                    type: 'POST',
                                    data: {
                                        rentPeriod: rentPeriod
                                    }

                                });
                            }

                            createContract.on('click', function() {
                                if ((safeKey.val() && safeCosts.val() && safeRentEnd.val()) === "") {
                                    alert("Не все поля заполнены");
                                } else {
                                    var contractObject = {
                                        safeSize: safeModel.val(),
                                        safeNumber: safeNumber.val(),
                                        monthRent: rentPeriod.val(),
                                        price: safeCosts.val(),
                                        key: safeKey.val(),
                                        dateContractStart: safeRentStart.val(),
                                        dateContractEnd: safeRentEnd.val(),
                                        clientId1: clientIdInput.val(),
                                        clientId2: clientIdInput1.val(),
                                        contractStatus: contractStatus.val(),
                                        safeId: safeNumber.val()
                                    };
                                }
                                var jsonObject = $.toJSON(contractObject);
                                getDataFofContract(jsonObject).done(function(data) {
                                    window.location.href = 'test.htm';
                                    if (data.status !== "") {
                                        alert("Сделка успешно добавлена!");
                                        createContract.hide();
                                    } else {
                                        alert("Ошибка при добавлении в БД!");
                                    }
                                });
                            });
                            function getDataFofContract(jsonObject) {
                                return $.ajax({
                                    url: 'saveContract',
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