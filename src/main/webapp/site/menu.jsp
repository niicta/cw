<%@ page import="cw.model.UserRole" %>
<%@ page contentType="text/html;charset=utf-8" %>

<div class="menu flex-column">
    <div class="menu-icon">≡</div>
    <ul class="menu-list">
        <% if( user != null && user.getUserRole() == UserRole.ADMIN) {%>
        <li class="menu-item">
            <div class="menu-item-container">
                <p class="menu-item-text">Пункт меню</p>
            </div>
        </li>
        <%}%>
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
