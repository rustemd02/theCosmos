<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${movie.title}</title>
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
        <li style="float: right"><a href="/profile">Профиль</a></li>
        <li style="float: right"><a href="/movieChat">Чат</a></li>
    </div>
</header>

<h1 style="margin-left: 15px">${movie.title}</h1>
<h2>${movie.description}</h2>
<h3>Возрастное ограничение ${movie.ageRestriction}</h3>
<h3></h3>
<h3>Режиссёр: ${movie.director.name}</h3>
<h3>Жанр: ${movie.genre.genreName}</h3>


<button onclick="deleteMovie(${movie.id})">Удалить фильм</button>

<label>Новое название<input id="title" name="title" class="field" type="text" required placeholder="Титаник 2"></label>
<label>Новое описание<input id="description" name="description" class="field" type="text" required
                            placeholder="Новая часть легендарной драмы-катастрофы"></label>
<label>Новое возрастное ограничение<input id="ageRestriction" name="ageRestriction" class="field" type="text" required
                                          placeholder="16+"></label>
<label>Новый режиссёр<input id="director" name="director" class="field" type="text" required
                            placeholder="Стивен Спилберг"></label>
<label>Новый жанр<input id="genre" name="genre" class="field" type="text" required placeholder="Драма"></label>
<button onclick="editMovie(${movie.id})">Обновить информацию</button>

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
<script src="../../resources/javascript/movies.js"></script>

</body>
</html>