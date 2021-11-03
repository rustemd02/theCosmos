<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Кинотеатр «Космос»</title>
    <link rel="stylesheet" href="../css/Main.css"/>

</head>
<body>
<header class="site-header">
    <img class="logo" src="../Assets/cinema.png" alt="Космос">
    <div class="buttons">
        <li style="float: left"><a class="active" href="${pageContext.request.contextPath}/">Главная</a></li>
        <li style="float: left"><a href="${pageContext.request.contextPath}/schedule">Афиша</a></li>
        <li style="align-content: center"><a href="${pageContext.request.contextPath}/cosmostar">«Космостар»</a></li>
        <li style="float: right"><a href="${signOutLink}">${signIn}</a></li>
        <li style="float: right"><a href="${profileLink}">${register}</a></li>
    </div>

</header>

<p style="font-size: 160%">Добро пожаловать на официальный веб-сайт кинотеатра «Космос», расположенного в посёлке Вигвамцев</p>
<p>Обращаем ваше внимание, что в соответствии с рекомендациями Роспотребнадзора, в общих зонах кинотеатра обязательно
    ношение масок. Посетители без них обслуживаться не будут.
    Также, просим вас соблюдать социальную дистанцию не менее 1,5 метра и
    настоятельно рекомендуем покупать билеты онлайн</p>

<div class="promo">
    <h2 style="color: #ff1947">Акции и скидки</h2>
    <div class="cards">
        <img class="promoPhoto" src="../Assets/wp1.jpg" alt="">
        <div class="photosText"><p>Подписчикам группы /vigvamcev предоставляется скидка 5% на все сеансы</p></div>
        <img class="promoPhoto" src="../Assets/cinema.jpeg" alt="">
        <div class="photosText"><p>Приходите!</p></div>
        <img class="promoPhoto" src="../Assets/bday.jpeg" alt="">
        <div class="photosText"><p>В день вашего рождения (+- 3 дня) предоставляется скидка 15% на все сеансы</p></div>
    </div>

</div>


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