
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Космостар</title>
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
        <li style="align-content: center" class="active"><a href="/cosmostar">«Космостар»</a></li>
        <li style="float: right"><a href="${signOutLink}">${signIn}</a></li>
        <li style="float: right"><a href="${profileLink}">${register}</a></li>
        <li style="float: right"><a href="/movieChat">Чат</a></li>



    </div>
</header>

<h1 style="margin-left: 15px">Программа лояльности «Космостар»</h1>
<h3>Номер Вашей карты лояльности «Космостар»: ${hasCosmostar}</h3>
<h3>${cosmostarBalance}</h3>

<button type="button">Выпустить карту</button>




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