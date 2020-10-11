function openLoginModal() {
  $('.ui.modal').modal('show');

}

function check() {
    alert("submit has been pressed");
}

function tempButton() {
  alert("This feature has not been implemented yet!");
}

function loginCheck() {
      const getParams = window.location.search;
      const params = new URLSearchParams(getParams);

      let isError = params.get("error");
      let isLoggedOut = params.get("logout");

      if(isError != null){
          // change this to be more user friendly upon error
          alert("deny")
          document.getElementById("confirm").style.display = "none";
          document.getElementById("deny").style.display = "block";
      }
      else {
        document.getElementById("deny").style.display = "none";
        document.getElementById("confirm").style.display = "block";
        document.getElementById("loginButton").style.display = "none";
        document.getElementById("logoutButton").style.display = "block";
      }

      if(isLoggedOut != null){
          alert("You have been logged out.");
          document.getElementById("logoutButton").style.display = "none";
          document.getElementById("loginButton").style.display = "block";
      }
}