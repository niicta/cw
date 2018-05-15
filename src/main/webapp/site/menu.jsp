<%@ page import="cw.model.UserRole" %>
<%@ page contentType="text/html;charset=utf-8" %>

<div class="menu flex-column">
    <div class="menu-icon">≡</div>
    <ul class="menu-list">
        <li class="menu-item">
            <div class="menu-item-container">
                <p class="menu-item-text"><a href="main" class="menu-link">Оформить заявку</a></p>
            </div>
        </li>
        <li class="menu-item">
            <div class="menu-item-container">
                <p class="menu-item-text"><a href="visits" class="menu-link">Посещения</a></p>
            </div>
        </li>
        <% if( user != null && user.getUserRole() == UserRole.ADMIN) {%>
        <li class="menu-item">
            <div class="menu-item-container">
                <p class="menu-item-text"><a href="spaces" class="menu-link">Рабочие места</a></p>
            </div>
        </li>
        <%}%>

        <li class="menu-item">
            <div class="menu-item-container">
                <p class="menu-item-text"><a href="help" class="menu-link">Справка</a></p>
            </div>
        </li>
        <li class="menu-item">
            <div class="menu-item-container">
                <p class="menu-item-text"><a href="logOff" class="menu-link">Выход</a></p>
            </div>
        </li>

    </ul>
</div>
