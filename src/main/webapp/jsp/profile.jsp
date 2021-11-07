
<%--
  Created by IntelliJ IDEA.
  User: unterlantas
  Date: 13.10.2021
  Time: 00:14
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Профиль</title>
    <link rel="stylesheet" href="../css/Main.css">
</head>
<body>
<header class="site-header">
    <a href="${pageContext.request.contextPath}/main">
        <img class="logo" src="../Assets/cinema.png" alt="Космос">
    </a>
    <div class="buttons">
        <li style="float: left"><a href="${pageContext.request.contextPath}/main">Главная</a></li>
        <li style="float: left"><a href="${pageContext.request.contextPath}/schedule">Афиша</a></li>
        <li style="align-content: center"><a href="${pageContext.request.contextPath}/cosmostar">«Космостар»</a></li>
        <li style="float: right"><a href="${signOutLink}">${signIn}</a></li>
        <li style="float: right"><a href="${profileLink}">${register}</a></li>
    </div>
</header>
<h1>Добро пожаловать, ${name}</h1>

<h3>Номер Вашей карты лояльности «Космостар»: ${hasCosmostar}</h3>
<h3>${cosmostarBalance}</h3>

<h3>Баланс Вашей карты MasterCard-4151: ${cardBalance}</h3>
<footer>
    <b>Касса:</b>
    <li class="f-text"><a href="tel:55-24-73">☎ 55-24-73</a></li>
    <b>Администрация:</b>
    <li class="f-text"><a href="tel:55-24-72">☎ 55-24-72</a></li>
    <b>Наш адрес:</b>
    <li class="f-text">пос. Вигвамцев, ул. Кинематографа, 1</li>
    <b>Время работы: </b>
    <li class="f-text">с понедельника по пятницу с 10:00 до 20:00</li>
</footer>
</body>
</html>
