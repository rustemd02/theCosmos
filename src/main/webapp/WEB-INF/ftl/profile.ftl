<!DOCTYPE html>
<html>
<head>
    <title>Профиль</title>
    <link rel="stylesheet" href="../../resources/css/Main.css">
</head>
<body>

<header class="site-header">
    <a href="/">
        <img class="logo" src="../../resources/Assets/cinema.png" alt="Космос">
    </a>
    <div class="buttons">
        <li style="float: left"><a href="/">Главная</a></li>
        <li style="float: left"><a href="/schedule">Афиша</a></li>
        <li style="align-content: center"><a href="/cosmostar">«Космостар»</a></li>
        <li style="float: right"><a href="/logout">Выйти</a></li>
        <li style="float: right" class="active"><a href="/profile">Профиль</a></li>
        <li style="float: right"><a href="/movieChat">Чат</a></li>

    </div>
</header>

<div id="userId" hidden>${user.id}</div>

<h1>Добро пожаловать, ${name} </h1>

<div style="width: 100%; margin: auto; text-align: center;">
    <img src="${user.profilePic!"/resources/Assets/defaultProfilePic.jpeg"}" id="profilePic" alt="" style="width: 15%">
    <form action="/profile/set_pic" id="file-upload-form" enctype="multipart/form-data" method="post">
        <input type="file" id="profilePic" name="profilePic" title="Выбрать файл для аватарки" style="width: 30%">
        <button type="submit">Сохранить</button>
    </form>
</div>

<h3>Номер Вашей карты лояльности «Космостар»: ${hasCosmostar}</h3>
<h3>${cosmostarBalance}</h3>

<h3>Баланс Вашей карты MasterCard-4151: ${cardBalance}</h3>

<table>
    <h3>Ваши сеансы: </h3>
    <#list seances as seance>
        <form class="menu-option" method="get" action="/profile/get_user_seance?id=${seance.getId()}">
            <h3> ${seance.movie.getTitle()}</h3>
        </form>
    </#list>
</table>


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
<script src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

</body>
</html>
