$(document).ready(() => {
    const id = new URL(document.URL).searchParams.get("uid");
    $.ajax({
        url: `/api/movies/${id}`,
        method: "GET",
        success: users => {
            fillInputs(users);
        },
        error: err => {
            const errorObj = err.responseJSON;
            alert(`ERROR: "${errorObj.message}" \nTIME: ${errorObj.time}`);
        }
    })
});

function fillInputs(data) {
    $("#name-update").val(data.name);
    $("#genre-update").val(data.genre);
    $("#budget-update").val(data.budget);
}

$("#update-movie-submit").on("click", () => {
    const id = new URL(document.URL).searchParams.get("uid");
    $.ajax({
        url: `/api/movies/${id}`,
        method: "PUT",
        data: JSON.stringify(newUserObj()),
        contentType: "application/json",
        success: response => {
            alert(`SUCCESS: ${response}`);
            window.location.href = "/";
        },
        error: err => {
            const errorObj = err.responseJSON;
            alert(`ERROR: "${errorObj.message}" \nTIME: ${errorObj.time}`);
        }
    });
});

const newUserObj = () => {
    return {
        name: $("#name-update").val(),
        genre: $("#genre-update").val(),
        budget: parseInt($("#budget-update").val())
    };
};