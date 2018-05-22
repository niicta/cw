<%@ page import="cw.model.User" %>
<%@ page import="javax.enterprise.inject.spi.CDI" %>
<%@ page import="cw.data.DAOContainer" %>
<%@ page import="java.util.Collection" %>
<%@ page import="cw.model.Space" %>
<%@ page import="cw.model.SpaceType" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%
    User user = CDI.current().select(User.class).get();
    DAOContainer daoContainer = CDI.current().select(DAOContainer.class).get();
    Collection<Space> spaces = daoContainer.getSpaceDao().findAll();
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Рабочие места</title>
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
<div class="content flex-row">
<div class="spaces-block flex-column">
    <p class="spaces-header section-name">Рабочие места</p>

    <div class="space-container new-space-button-container flex-row">
        <div class="new-space-button button">Новое место</div>
    </div>

    <%for (Space space : spaces) {
        if(space.getSpaceType().equals(SpaceType.WORK_PLACE)){%>

    <div class="space-container flex-row">
        <p class="space-header">Рабочее место № <%=space.getId()%></p>
        <div class="delete-space-button">
            <img src="site/icons/bin.png" alt="" class="delete-button-icon">
        </div>
    </div>

        <%}%>
    <%}%>

</div>

<div class="rooms-block flex-column">
    <p class="rooms-header">Переговорные комнаты</p>


    <div class="space-container new-space-button-container flex-row">
        <div class="new-room-button button">Новая комната</div>

    </div>

    <%for (Space space : spaces) {
        if(space.getSpaceType().equals(SpaceType.MEETING_ROOM)){%>

    <div class="space-container flex-row">
        <p class="room-header">Переговорная № <%=space.getId()%></p>
        <p class="room-count-of-places-header">Количество мест: <%=space.getCountOfSeats()%></p>
        <div class="delete-space-button">
            <img src="site/icons/bin.png" alt="" class="delete-button-icon">
        </div>
    </div>

        <%}%>
    <%}%>

</div>
</div>


<div class="create-room-form-block form-block flex-column ">
    <div class="create-room-form form flex-column">

        <div class="create-room-text-block form-caption flex-row">
            <p class="create-room-text">Создание переговорной</p>
        </div>

        <div class="parameters-container flex-column">
            <div class="room-count-container flex-row form-input-container">
                <p class="room-count-text">Количество мест: </p>
                <input type="text" class="room-count text-input"/>
            </div>



            <div class="buttons-container flex-row">
                <div class="button submit-create-room-button">Создать</div>
                <div class="button cancel-create-room-button">Отмена</div>
            </div>
        </div>

    </div>
</div>

<%@include file="menu.jsp"%>
</body>