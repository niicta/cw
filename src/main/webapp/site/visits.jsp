<%@ page import="javax.enterprise.inject.spi.CDI" %>
<%@ page import="cw.data.DAOContainer" %>
<%@ page import="java.util.Collection" %>
<%@ page import="cw.model.*" %>
<%@ page import="cw.data.DAO" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="cw.model.operations.TimeOperatons" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cw.model.operations.VisitByStartDateComparator" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%
    User user = CDI.current().select(User.class).get();
    if (user == null || user.getUserRole() == null)
    {
        response.sendRedirect("index");
    }
    DAOContainer daoContainer = CDI.current().select(DAOContainer.class).get();
    DAO<Order> orderDao = daoContainer.getOrderDao();
    Collection<Order> orders = new LinkedList<>();
    if (user != null && user.getUserRole() != null) {
        if (user.getUserRole().equals(UserRole.ADMIN) || user.getUserRole().equals(UserRole.OPERATOR)) {
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
    <title>Посещения</title>
    <link rel="stylesheet" type="text/css" href="site/css/markup.css">
    <link rel="stylesheet" type="text/css" href="site/css/theme.css">
    <script src="site/script/jquery-3.2.1.js"></script>
    <script src="site/script/scripts.js"></script>
</head>
<body>
<%@include file="header.jsp"%>
<div class="content-backgroung">
    <img class="content-backgroung-image" src="site/images/main.jpg">
</div>
<div class="content visit-content flex-column">

    <div class="visit-block flex-column">
        <p class="visit-header section-name">Посещения: </p>
        <%for (Order order : orders)  {%>
            <p class="visit-template-header">Тариф <%=order.getTemplate().getName()%>: </p>
            <% Collection<Visit> visits = order.getVisits();
                ArrayList<Visit> sortedVisits = new ArrayList<>();
                sortedVisits.addAll(visits);
                sortedVisits.sort(new VisitByStartDateComparator());
                for (Visit visit : sortedVisits) {%>
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
                    <div class="delete-visit-button" data-visit-id="<%=visit.getId()%>">
                        <img src="site/icons/bin.png" alt="" class="delete-button-icon"/>
                    </div>
                </div>
            <%}%>
        <div class="space-container new-visit-button-container flex-row">
            <div class="new-visit-button button" id="<%=order.getId()%>">Новое посещение</div>
        </div>
        <%}%>
    </div>
</div>




<div class="error-form-block form-block flex-column ">
    <div class="error-form form flex-column">

        <div class="error-text-block form-caption flex-row">
            <p class="error-text-caption">Ошибка</p>
        </div>

        <div class="parameters-container flex-column">
            <p class="error-text"></p>

            <div class="buttons-container flex-row">
                <div class="button submit-error-button">Ок</div>
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
                <div class="visit-day-container flex-row form-input-container">
                    <p class="visit-day-text">День: </p>
                    <input type="date" class="visit-day text-input"/>
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