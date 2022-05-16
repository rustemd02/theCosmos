$(document).ready(function () {

    let userId;
    $("#file-upload-form").on("submit", function (e) {
        e.preventDefault();
        setProfilePic()
    })
});


function loadProfilePic(profilePic) {
    if (profilePic != null && profilePic !== "") {
        document.getElementById('profilePic').src = "../uploads/" + profilePic
    } else {
        document.getElementById('profilePic').src = "../resources/Assets/defaultProfilePic.jpeg"
    }
}

function setProfilePic() {
    let data = new FormData()
    let userId = document.getElementById("userId").value
    //let userId = "1"
    data.append('userId', userId)

    var files = $('#profilePicFile')[0].files;
    if (files.length > 0) {
        data.append('profilePicFile', files[0]);
    }

    for (var pair of data.entries()) {
        console.log(pair[0] + ', ' + pair[1]);
    }

    $.ajax({
        url: '/profile/set_pic',
        type: 'post',
        dataType: 'json',
        enctype: 'multipart/form-data',
        data: data,
        processData: false,
        contentType: false,
        success: function (fileSrc) {
            if (fileSrc == null) {
                return;
            }
            document.getElementById('profilePic').src = fileSrc;
        },
        error: function (response) {
        }
    })
}