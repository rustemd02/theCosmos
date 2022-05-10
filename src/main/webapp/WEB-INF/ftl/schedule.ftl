
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Афиша</title>
    <link rel="stylesheet" href="../../resources/css/Main.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>


</head>
<body>
<header class="site-header">
    <a href="/">
        <img class="logo" src="../../resources/Assets/cinema.png" alt="Космос">
    </a>
    <div class="buttons">
        <li style="float: left"><a href="/">Главная</a></li>
        <li style="float: left"><a class="active" href="/schedule">Афиша</a></li>
        <li style="align-content: center"><a href="/cosmostar">«Космостар»</a></li>
        <li style="float: right"><a href="${signOutLink}">${signIn}</a></li>
        <li style="float: right"><a href="${profileLink}">${register}</a></li>
        <li style="float: right"><a href="/movieChat">Чат</a></li>


    </div>
</header>

<h1 style="margin-left: 15px">Афиша на завтра</h1>

<div id="schedule">
    <table>
        <#list seances as seance>
            <form class="menu-option" method="post" action="/movie">
                <h3><button type="submit" name="seance" value="${seance.getId()}">${seance.movie.getTitle()}</button></h3>
            </form>
        </#list>
    </table>

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