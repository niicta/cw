<%@ page import="javax.enterprise.inject.spi.CDI" %>
<%@ page import="cw.data.DAOContainer" %>
<%@ page import="java.util.Collection" %>
<%@ page import="cw.model.*" %>
<%@ page import="cw.data.DAO" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="cw.model.operations.TimeOperatons" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%
    User user = CDI.current().select(User.class).get();
    DAOContainer daoContainer = CDI.current().select(DAOContainer.class).get();
    DAO<Order> orderDao = daoContainer.getOrderDao();
    Collection<Order> orders = new LinkedList<>();
    if (user != null && user.getUserRole() != null) {
        if (user.getUserRole().equals(UserRole.ADMIN)) {
            orders = orderDao.findAll();
        } else {
            for (Order order : orderDao.findAll()){
                if (order.getUser().getId() == user.getId()){
                    orders.add(order);
                }
            }
        }
    }
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать!</title>
    <link rel="stylesheet" type="text/css" href="site/css/markup.css">
    <link rel="stylesheet" type="text/css" href="site/css/theme.css">
    <script src="site/script/jquery-3.2.1.js"></script>
    <script src="site/script/scripts.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
<div class="content visit-content flex-column">

    <div class="visit-block flex-column">
        <p class="visit-header">Посещения: </p>
        <%for (Order order : orders)  {%>
            <p class="visit-template-header">Тариф <%=order.getTemplate().getName()%>: </p>
            <%for (Visit visit : order.getVisits()) {%>
                <div class="visit-container flex-row">
                    <div class="visit-date-container flex-column">
                        <p class="visit-date-text"><%=TimeOperatons.calendarToDateString(visit.getStartDate())%></p>
                    </div>
                    <div class="visit-time-container flex-column">
                        <p class="visit-time-text"><%=visit.getStartDate().get(Calendar.HOUR_OF_DAY)%>:00-<%=visit.getEndDate().get(Calendar.HOUR_OF_DAY)%>:00</p>
                    </div>
                    <div class="visit-space-container flex-column">
                        <p class="visit-space-text">Рабочее место №<%=visit.getSpace().getId()%></p>
                    </div>
                    <div class="delete-visit-button">
                        <img src="site/icons/bin.png" alt="" class="delete-button-icon"/>
                    </div>
                </div>
            <%}%>
        <div class="space-container new-visit-button-container flex-row">
            <div class="new-visit-button button" id="<%=order.getId()%>">Новое посещение</div>
        </div>
        <%}%>

        <p class="visit-template-header">Продвинутый тариф: </p>

        <div class="visit-container flex-row">
            <div class="visit-date-container flex-column">
                <p class="visit-date-text"></p>
            </div>
            <div class="visit-time-container flex-column">
                <p class="visit-time-text">11:00-12:00</p>
            </div>
            <div class="visit-space-container flex-column">
                <p class="visit-space-text">Рабочее место №11</p>
            </div>
            <div class="delete-visit-button">
                <img src="site/icons/bin.png" alt="" class="delete-button-icon"/>
            </div>
        </div>



    </div>

</div>


<div class="create-visit-form-block form-block flex-column ">
    <div class="create-visit-form form flex-column">

        <div class="create-visit-text-block form-caption flex-row">
            <p class="create-visit-text">Создание посещения</p>
        </div>

        <div class="parameters-container flex-column">
            <div class="date-row flex-row">
                <div class="visit-day-container flex-row form-input-container">
                    <p class="visit-day-text">День: </p>
                    <input type="text" class="visit-day text-input"/>
                </div>
                <div class="visit-month-container flex-row form-input-container">
                    <p class="visit-month-text">Месяц: </p>
                    <input type="text" class="visit-month text-input"/>
                </div>
                <div class="visit-year-container flex-row form-input-container">
                    <p class="visit-year-text">Год: </p>
                    <input type="text" class="visit-year text-input"/>
                </div>
            </div>

            <div class="date-row flex-row">
                <div class="visit-start-hour-container flex-row form-input-container">
                    <p class="visit-start-hour-text">Начало, часов: </p>
                    <input type="text" class="visit-start-hour text-input"/>
                </div>
                <div class="visit-end-hour-container flex-row form-input-container">
                    <p class="visit-end-hour-text">Конец, часов: </p>
                    <input type="text" class="visit-end-hour text-input"/>
                </div>
            </div>

            <div class="buttons-container flex-row">
                <div class="button submit-create-visit-button">Создать</div>
                <div class="button cancel-create-visit-button">Отмена</div>
            </div>
        </div>

    </div>
</div>

<%@include file="menu.jsp"%>
</body>