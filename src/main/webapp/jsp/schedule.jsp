<%--
  Created by IntelliJ IDEA.
  User: unterlantas
  Date: 02.11.2021
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Афиша</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <style>
        <%@ include file="../css/Main.css"%>
    </style>

</head>
<body>
<header class="site-header">
    <a href="${pageContext.request.contextPath}/main">
        <img class="logo" src="../Assets/cinema.png" alt="Космос">
    </a>
    <div class="buttons">
        <li style="float: left"><a href="${pageContext.request.contextPath}/main">Главная</a></li>
        <li style="float: left"><a class="active" href="${pageContext.request.contextPath}/schedule">Афиша</a></li>
        <li style="align-content: center"><a href="${pageContext.request.contextPath}/cosmostar">«Космостар»</a></li>
        <li style="float: right"><a href="${signOutLink}">${signIn}</a></li>
        <li style="float: right"><a href="${profileLink}">${register}</a></li>

    </div>
</header>

<h1 style="margin-left: 15px">Афиша на 6 октября</h1>

<div id="schedule">
    <table>
        <c:forEach var="movie" items="${movies}">
        <tr>
            <td>
                <c:out value="${movie.poster_link}"/>
            </td>
            <td>
                <c:out value="${movie.title}"/>
            </td>
        </tr>
        </c:forEach>
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