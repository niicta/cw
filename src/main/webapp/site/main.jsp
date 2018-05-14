<%@ page import="cw.auth.UserContainer" %>
<%@ page import="cw.model.User" %>
<%@ page import="javax.enterprise.inject.spi.CDI" %>
<%@ page contentType="text/html;charset=utf-8" %>
<% User user = CDI.current().select(User.class).get(); %>
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
<div class="entire-page flex-column">
    <div class="header-container">
        <div class="header flex-row">
            <div class="filler"></div>
            <div class="user-container">
                <p class="user-name-text"><%= user.getName() %></p>
            </div>
            <div class="info-container">
                <p class="info-text">Справка</p>
            </div>
        </div>
    </div>
    <div class="content flex-row">
        <p>Тарифы:</p>
        <% for (int i = 0; i < 5; i++){ %>
        <div class="template-container flex-column">
            <div class="template-name-container"><p class="template-name-text">Тариф</p></div>
            <div class="template-info-container">
                <p class="template-info-text">Информация о тарифе</p>
                <p class="template-info-text">Информация о тарифе</p>
                <p class="template-info-text">Информация о тарифе</p>
            </div>
            <div class="submit-button-container">
                <div class="button submit-template-button">Подать заявку</div>
            </div>
            <div class="delete-button">
                <img src="site/icons/bin.png" alt="" class="delete-button-icon">
            </div>
        </div>
        <%}%>
        <div class="template-container flex-column">
            <div class="new-template-button button">Новый тариф</div>
        </div>
    </div>
    <div class="menu flex-column">
        <div class="menu-icon">≡</div>
        <ul class="menu-list">
            <li class="menu-item">
                <div class="menu-item-container">
                    <p class="menu-item-text">Пункт меню</p>
                </div>
            </li>
            <li class="menu-item">
                <div class="menu-item-container">
                    <p class="menu-item-text">Пункт меню</p>
                </div>
            </li>
            <li class="menu-item">
                <div class="menu-item-container">
                    <p class="menu-item-text">Пункт меню</p>
                </div>
            </li>
            <li class="menu-item">
                <div class="menu-item-container">
                    <p class="menu-item-text">Пункт меню</p>
                </div>
            </li>
            <li class="menu-item">
                <div class="menu-item-container">
                    <p class="menu-item-text">Пункт меню</p>
                </div>
            </li>
            <li class="menu-item">
                <div class="menu-item-container">
                    <p class="menu-item-text">Пункт меню</p>
                </div>
            </li>
            <li class="menu-item">
                <div class="menu-item-container">
                    <p class="menu-item-text">Пункт меню</p>
                </div>
            </li>
            <li class="menu-item">
                <div class="menu-item-container">
                    <p class="menu-item-text">Пункт меню</p>
                </div>
            </li>
            <li class="menu-item">
                <div class="menu-item-container">
                    <p class="menu-item-text">Пункт меню</p>
                </div>
            </li>
            <li class="menu-item">
                <div class="menu-item-container">
                    <p class="menu-item-text">Пункт меню</p>
                </div>
            </li>
        </ul>
    </div>
</body>
</html>