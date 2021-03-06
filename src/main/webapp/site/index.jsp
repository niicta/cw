
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать!</title>
    <link rel="stylesheet" type="text/css" href="site/css/markup.css">
    <link rel="stylesheet" type="text/css" href="site/css/theme.css">
    <script src="site/script/jquery-3.2.1.js"></script>
    <script src="site/script/loginScript.js"></script>
</head>
<body>
<div class="main-backgroung">
    <img class="main-backgroung-image"src="site/images/main.jpg">
</div>

<div class="welcome-block flex-column">
    <div class="welcome-form flex-column">
        <div class="welcome-text-block flex-column">
            <p class="welcome-text">Добро пожаловать!</p>
        </div>
        <div class="login-welcome-button button">
            <p class="login-welcome-text">Вход</p>
        </div>
        <div class="sing-up-welcome-button button">
            <p class="sing-up-welcome-text">Регистрация</p>
        </div>
    </div>
</div>

<div class="login-block flex-column hidden">
    <div class="login-form flex-column">
        <div class="login-text-block flex-column">
            <p class="login-text login-text-caption">Введите свои учетные данные:</p>
        </div>
        <p class="login-text">Логин:</p>
        <input type="text" class="login"></input>
        <p class="login-text">Пароль:</p>
        <input type="password" class="password"></input>
        <div class="login-button button">
            <p class="login-text">Вход</p>
        </div>
        <div class="cancel-login-button button">
            <p class="cancel-login-text">Отмена</p>
        </div>
    </div>
</div>
</body>
</html>