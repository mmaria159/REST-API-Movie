$("#add-movie-submit").on("click", () => {
    if (validInput() === true) {
        $.ajax({
            method: "POST",
            url: "api/movies",
            data: JSON.stringify(movieObject()),
            contentType: "application/json",
            success: response => {
                alert(`SUCCESS: ${response}`);
                window.location.href = "/";
            },
            error: err => {
                let errorObj = err.responseJSON;
                alert(`ERROR: "${errorObj.message}" \nTIME: ${errorObj.time}`);
            }
        });
    } else {
        alert("Invalid Input");
    }
});

const movieObject = () => {
    return {
        name: $("#name").val(),
        description: $("#description").val(),
        budget: parseInt($("#budget").val())
    };
};

const validInput = () => {
    return $("#name").val() && $("#description").val() && $("#budget").val()>=0;
};