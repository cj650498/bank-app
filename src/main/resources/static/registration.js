// Wait for the DOM content to be fully loaded
document.addEventListener("DOMContentLoaded", function() {
  // Get the registration form element
  var registrationForm = document.getElementById("registrationForm");

  // Add a submit event listener to the registration form
  registrationForm.addEventListener("submit", function(event) {
    // Prevent the default form submission behavior
    event.preventDefault();

    // Create a new FormData object from the registration form
    var formData = new FormData(registrationForm);

    // Create an empty object to store the form data in JSON format
    var jsonData = {};

    // Convert the FormData entries into key-value pairs in the jsonData object
    for (var pair of formData.entries()) {
      jsonData[pair[0]] = pair[1];
    }

    // Create a new XMLHttpRequest object
    var xhr = new XMLHttpRequest();

    // Open a POST request to the "/users" endpoint
    xhr.open("POST", "/users");

    // Set the request header to specify the content type as JSON
    xhr.setRequestHeader("Content-Type", "application/json");

    // Define the callback function to handle the response
    xhr.onload = function() {
      // Check if the response status is 201 (CREATED)
      if (xhr.status === 201) {
        // Display a success message
        alert("User created successfully");

        // Reset the registration form
        registrationForm.reset();
      } else {
        // Display an error message
        alert("An error occurred while creating the user");
      }
    };

    // Send the JSON data as the request payload
    xhr.send(JSON.stringify(jsonData));
  });
});
