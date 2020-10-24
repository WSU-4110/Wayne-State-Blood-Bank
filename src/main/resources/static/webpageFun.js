function openLoginModal() {
  $('.ui.modal').modal('show');

}

function check() {
    alert("submit has been pressed");
}

function tempButton() {
  alert("This feature has not been implemented yet!");
}

function loginCheckIndex() {
      const getParams = window.location.search;
      const params = new URLSearchParams(getParams);

      let isError = params.get("error");
      let isLoggedOut = params.get("logout");

      if (isError != null){
          // change this to be more user friendly upon error
          //alert("deny")
          //hide main content on page and show login info
          document.getElementById("mainContainer").style.display = "none";
          document.getElementById("loginContainer").style.display = "block";
          //hide/show login confirmation or error
          document.getElementById("confirm").style.display = "none";
          document.getElementById("deny").style.display = "block";
          //logout button hidden
          document.getElementById("logoutButton").style.display = "none";

      }
      $(document).ready(function() {
        $.get("/loginStatus", function(data, status) {
          if (data.isLoggedIn == true){
            document.getElementById("logoutButton").style.display = "block";
            document.getElementById("loginButton").style.display = "none";
            document.getElementById("Register").style.display = "none";
          }


        });
      });

      if(isLoggedOut != null){
          alert("You have been logged out.");
          document.getElementById("logoutButton").style.display = "none";
          document.getElementById("loginButton").style.display = "block";
      }
}

function loginCheck() {
      const getParams = window.location.search;
      const params = new URLSearchParams(getParams);

      let isError = params.get("error");
      let isLoggedOut = params.get("logout");

      if (isError != null){
          // change this to be more user friendly upon error
          //alert("deny")
          //hide main content on page and show login info
          document.getElementById("mainContainer").style.display = "none";
          document.getElementById("loginContainer").style.display = "block";
          //hide/show login confirmation or error
          document.getElementById("confirm").style.display = "none";
          document.getElementById("deny").style.display = "block";
          //logout button hidden
          document.getElementById("logoutButton").style.display = "none";

      }
      $(document).ready(function() {
        $.get("/loginStatus", function(data, status) {
          if (data.isLoggedIn == true){
            document.getElementById("logoutButton").style.display = "block";
            document.getElementById("loginButton").style.display = "none";
            document.getElementById("Register").style.display = "none";
          }


        });
      });

      if(isLoggedOut != null){
          alert("You have been logged out.");
          document.getElementById("logoutButton").style.display = "none";
          document.getElementById("loginButton").style.display = "block";
      }
}
