
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Чат</title>
    <link rel="stylesheet" href="../../resources/css/Main.css">
</head>
<body onload="connect(${user.id})">
<header class="site-header">
    <a href="/">
        <img class="logo" src="../../resources/Assets/cinema.png" alt="Космос">
    </a>
    <div class="buttons">
        <li style="float: left"><a href="/">Главная</a></li>
        <li style="float: left"><a href="/schedule">Афиша</a></li>
        <li style="align-content: center"><a href="/cosmostar">«Космостар»</a></li>
        <li style="float: right"><a href="/logout">Выйти</a></li>
        <li style="float: right"><a href="/profile">Профиль</a></li>
        <li style="float: right" class="active"><a href="/movieChat">Чат</a></li>

    </div>
</header>

<div class="menu"><h2 class="menu-option">Чат:</h2></div>
<div style="padding-right: 5%; padding-left: 5%">
    <div id="chatScrollView" style="overflow:scroll; height:200px;">
        <div id="messagesList"></div>
    </div>
    <div style="text-align: center"><button id="enterChatButton" onclick="enterChat('${user.name}')">Войти в чат</button></div>
    <label for="message"></label><input name="message" id="message" disabled placeholder="Сообщение" hidden>
    <button onclick="sendMessage('${user.name}', $('#message').val())" id="sendMessageButton" hidden>Отправить</button>
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

<script src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="../../resources/javascript/chat.js"></script>

</body>
</html>