
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
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
        <li style="float: right"><a href="${signOutLink}">${signIn}</a></li>
        <li style="float: right"><a href="${profileLink}">${register}</a></li>
        <li style="float: right"><a href="/movieChat">Чат</a></li>

    </div>
</header>

<h1 style="text-align: center">Регистрация</h1>
<form action="/register" method="post">
    <label>Имя<input name="name" class="field" type="text" required placeholder="Рустем"></label>
    <label>E-mail (используется для входа)<input name="email" class="field" type="email" required placeholder="••••@••.ru"></label>
    <label>Пароль<input name="password" class="field" type="password" required placeholder="•••••••••"></label>
    <label>Введите пароль еще раз<input name="repassword" class="field" type="password" required placeholder="•••••••••"></label>
    <button>Создать аккаунт</button>
</form>
<#if error??>
    <p class="info-text" style="margin-top: 0">${validation}</p>
</#if>

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