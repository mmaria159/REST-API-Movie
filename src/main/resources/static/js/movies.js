$(document).ready(() => {
    getAllMovies();
});

function getAllMovies() {
    $.ajax({
        url: "/api/movies",
        method: "GET",
        success: response => {
            displayMovies(response);
        },
        error: err => {
            let responseObj = err.responseJSON;
            alert(`ERROR: " ${responseObj.message} " TIME ${responseObj.time}`);
        }
    })
}

function displayMovies(movies) {
    if (movies.length>0) {
        let placeholder = "";
        $.each(movies, (index, movie) => {
            placeholder +=
                `<tr>
                <input class="movie-id" type='hidden' value='${movie.id}'>
                <td>${(index + 1)}</td>;
                <td>${movie.name}</td>
                <td>${movie.description}</td>
                <td>${movie.budget}</td>
                <td><button class="update-movie">Update</button></td>
                <td><button class="delete-movie">Delete</button></td>
            </tr>`;
        });
        $("#movies-placeholder tbody").html(placeholder);
    } else {
        $("#movies-div").html("<p>No movies in the system.</p>");
    }
}

// function displayMovies(movies) {
//     if (movies.length > 0) {
//         let placeholder = "";
//         $.each(movies, (index, movie) => {
//             placeholder +=
//                 `
//          <input class="movie-id" type='hidden' value='${movie.id}'>
//         <td>${movie.name}</td>
//         <td>${movie.description}</td>
//         <td>${movie.budget}</td>
//                 `;
//         });
//         $("#movies-placeholder tbody").html(placeholder);
//     } else {
//         $("#movies-div").html("<p>No movies in the system.</p>");
//     }
// }

$("#add").on("click", () => {
    window.location.href = "/add";
});

$("#movies-placeholder").on("click", ".update-movie", function () {
    let id = this.parentNode.parentElement.querySelector(".movie-id").value;
    window.location.href = `/update?uid=${id}`;
});


$("#movies-placeholder").on("click", ".delete-movie", function () {
    if (confirm("Click okay to confirm movie delete request")) {
        let id = this.parentNode.parentElement.querySelector(".movie-id").value;
        $.ajax({
            url: `/api/movies/${id}`,
            method: "DELETE",
            success: message => {
                alert(`SUCCESS: ${message}`);
                getAllMovies();
            },
            error: err => {
                let responseObj = err.responseJSON;
                alert(`ERROR: "${responseObj.message}" TIME: ${responseObj.time}`);
            }
        });
    }
});



// $("#movies-placeholder").on("click", ".update-movie", () => {
//     let id = this.parentNode.parentElement.querySelector(".movie-id").value;
//     window.location.href = "/update?uid=${id}";
// });

