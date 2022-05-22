function newCosmostar() {
    $.ajax({
        url: '/cosmostar/card_init',
        method: 'post',
        dataType: 'json',

        success: function () {
            alert("Поздравляем с новой картой лояльности Космостар!")
        },
        error: function () {
            alert("У вас уже есть карта Космостар")
        }
    })
}