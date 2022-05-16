
$(document).ready(function () {
    let seanceId = getUrlParameter('id');
    if (seanceId != null) {
        sendSeanceId(seanceId)
    }
});

function buyTicket(id) {
    $.ajax({
        url: '/seance',
        method: 'post',
        dataType: 'json',
        data: {
            "seanceId": id,
            "useCosmostar": false
        },
        success: function () {
            alert("Билет куплен")
        },
        error: function () {
            alert("Ошибка")
        }
    })
}
function buyTicketCosmostar(id) {
    $.ajax({
        url: '/seance',
        method: 'post',
        dataType: 'json',
        data: {
            "seanceId": id,
            "useCosmostar": true
        },
        success: function () {
            alert("Билет куплен")
        },
        error: function () {
            alert("Ошибка")
        }
    })
}


function sendSeanceId(id) {
    $.ajax({
        url: '/schedule/seance',           /* Куда пойдет запрос */
        method: 'post',             /* Метод передачи (post или get) */
        dataType: 'json',          /* Тип данных в ответе (xml, json, script, html). */
        data: {
            "seanceId": id,
        },
        success: function (seance) {
            console.log(seance)
            document.getElementById("movieTitle").innerText = seance.movie.title
            document.getElementById("buyTicketCosmostar").innerHTML = `<button id="buyTicketCosmostar" onclick="buyTicketCosmostar(`+ seance.id +`)">Купить билет за баллы Космостар</button>`
            document.getElementById("buyTicket").innerHTML = `<button id="buyTicket" onclick="buyTicket(`+ seance.id +`)">Купить билет</button>`
        }, error: function (data, exception) {
            console.log("data " +data)
            console.log("exception " +exception)
            document.getElementById("movieTitle").innerText = "Ошибка"
        }
    })
}

function getUrlParameter(sParam) {
    let sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1];
        }
    }
    return false;

}

