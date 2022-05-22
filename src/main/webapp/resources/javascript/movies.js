function addMovie() {
    let title = document.getElementById('title').value
    let description = document.getElementById('description').value
    let ageRestriction = document.getElementById('ageRestriction').value
    let director = document.getElementById('director').value
    let genre = document.getElementById('genre').value
    $.ajax({
        url: '/movies/add_movie',
        method: 'post',
        dataType: 'json',
        data: {
            "title": title,
            "description": description,
            "ageRestriction": ageRestriction,
            "director": director,
            "genre": genre
        },
        success: function () {
            alert("Фильм добавлен")
        },
        error: function (response) {
        }
    })
}

function editMovie(id) {
    let title = document.getElementById('title').value
    let description = document.getElementById('description').value
    let ageRestriction = document.getElementById('ageRestriction').value
    let director = document.getElementById('director').value
    let genre = document.getElementById('genre').value
    $.ajax({
        url: '/movies/edit_movie',
        method: 'post',
        dataType: 'json',
        data: {
            "id": id,
            "title": title,
            "description": description,
            "ageRestriction": ageRestriction,
            "director": director,
            "genre": genre
        },
        success: function () {
            alert("Информация о фильме обновлена")
        },
        error: function (response) {
        }
    })
}

function deleteMovie(id) {
    $.ajax({
        url: '/movies/' + id + '/delete_movie',
        method: 'delete',
        data: {
            id: id
        },
        success: function () {
            alert("Фильм удалён")
        },
        error: function (response) {
        }
    })
}