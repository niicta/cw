<%@ page import="cw.auth.UserContainer" %>
<%@ page import="cw.model.User" %>
<%@ page import="javax.enterprise.inject.spi.CDI" %>
<%@ page import="cw.data.DAO" %>
<%@ page import="cw.model.Template" %>
<%@ page import="javax.ejb.EJB" %>
<%@ page import="cw.data.DAOContainer" %>
<%@ page import="cw.model.SpaceType" %>
<%@ page contentType="text/html;charset=utf-8" %>

<% User user = CDI.current().select(User.class).get(); %>
<% DAOContainer daoContainer = CDI.current().select(DAOContainer.class).get(); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Тарифы</title>
    <link rel="stylesheet" type="text/css" href="site/css/markup.css">
    <link rel="stylesheet" type="text/css" href="site/css/theme.css">
    <script src="site/script/jquery-3.2.1.js"></script>
    <script src="site/script/scripts.js"></script>
</head>
<body>
<div class="entire-page flex-column">
    <%@include file="header.jsp"%>
    <div class="content-backgroung">
        <img class="content-backgroung-image" src="site/images/main.jpg">
    </div>
    <div class="content flex-row">
        <p class="section-name">Тарифы:</p>
        <% for (Template template : daoContainer.getTemplateDAO().findAll()){ %>
        <div class="template-container flex-column">
            <div class="template-name-container"><p class="template-name-text"><%=template.getName()%></p></div>
            <div class="template-info-container">
                <p class="template-info-text">
                    <%if (template.getSpaceType().equals(SpaceType.MEETING_ROOM)) {%>
                    Переговорная комната
                    <%} else if (template.isFixed()){ %>
                    Фиксированное рабочее место
                    <%} else {%>
                    Плавающее рабочее место
                    <%}%>
                </p>
                <p class="template-info-text">
                    <%if (template.isFullWeek()){%>
                    Всю неделю
                    <%} else {%>
                    Только по будням
                    <%}%>
                </p>
                <%if (template.getCountOfPlaces() > 1){%>
                <p class="template-info-text">
                    Рабочих мест : <%=template.getCountOfPlaces()%>
                </p>
                <%}%>
                <p class="template-info-text">
                    Стоимость часа : <%=template.getBasePricePerHour()%>
                </p>
            </div>
            <div class="submit-button-container">
                <div class="button submit-template-button" data-template-id="<%=template.getId()%>">Подать заявку</div>
            </div>
            <div class="delete-template-button">
                <img src="site/icons/bin.png" alt="" class="delete-button-icon">
            </div>
        </div>
        <%}%>
        <div class="template-container flex-column">
            <div class="new-template-button button">Новый тариф</div>
        </div>
    </div>


    <%@include file="menu.jsp"%>
</div>

<div class="create-template-form-block form-block flex-column ">
    <div class="create-template-form form flex-column">

        <div class="create-template-text-block form-caption flex-row">
            <p class="create-template-text">Создание тарифа</p>
        </div>

        <div class="parameters-container flex-column">
            <div class="new-template-name-container flex-row form-input-container">
                <p class="new-template-name-text">Название: </p>
                <input type="text" class="template-name text-input"/>
            </div>

            <div class="space-type-container flex-row form-input-container">
                <p class="space-type-text">Переговорная комната: </p>
                <input type="checkbox" class="space-type checkbox-input"/>
            </div>

            <div class="fixed-place-container flex-row form-input-container">
                <p class="fixed-place-text">Фиксированное рабочее место: </p>
                <input type="checkbox" class="fixed-place checkbox-input"/>
            </div>

            <div class="full-week-container flex-row form-input-container">
                <p class="full-week-text">Полная неделя: </p>
                <input type="checkbox" class="full-week checkbox-input"/>
            </div>

            <div class="count-of-places-container flex-row form-input-container">
                <p class="count-of-places-text">Количество рабочих мест: </p>
                <input type="text" class="count-of-places text-input"/>
            </div>

            <div class="base-price-container flex-row form-input-container">
                <p class="base-price-text">Базовая цена за час: </p>
                <input type="text" class="base-price text-input"/>
            </div>

            <div class="buttons-container flex-row">
                <div class="button submit-create-template-button">Создать</div>
                <div class="button cancel-create-template-button">Отмена</div>
            </div>
        </div>

    </div>
</div>
</body>
</html>