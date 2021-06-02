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
    if (movies.length > 0) {
        let placeholder = "";
        $.each(movies, (index, movie) => {
            placeholder +=
                `
        <div class="card my-5 w-25 card-card" >
             <img src="https://via.placeholder.com/250" alt="IMG" class="card-img-top">
                <div class="card-body">
                    <input class="movie-id" type='hidden' value='${movie.id}'>
                        <h5 class="card-title">
                            ${movie.name}
                        </h5>
                         <p class="card-text card-film-info">
                            ${movie.description}
                         </p>
                         <p>
                            <span>Budget:</span>
                            ${movie.budget}
                            <span>$</span>
                        </p>
                        <div class="card-body card-buttos">
                               <button class="update-movie edit-film btn btn-primary">Edit</button>
                               <button class="delete-movie info-film btn btn-danger">Delete</button>
                        </div>
                </div>
        </div>
                `;
        });
        $("#movies-placeholder").html(placeholder);
    } else {
        $("#movies-div").html("<p>No movies in the system.</p>");
    }
}

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
            method: "POST",
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


