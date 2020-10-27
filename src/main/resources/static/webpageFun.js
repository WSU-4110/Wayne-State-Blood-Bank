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
        //alert("You have been logged out.");
        document.getElementById("logoutLabel").style.display = "block";
        setTimeout(function() {
          document.getElementById("logoutLabel").style.display = "none";
        }, 5000)
          document.getElementById("logoutButton").style.display = "none";
          document.getElementById("loginButton").style.display = "block";
      }
}

function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  var expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i = 0; i < ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function checkCookie() {
  var user = getCookie("openModal");
  if (user == "true") {
    openLoginModal();
    setCookie("openModal", "", 1);
  }
}

function signInButton() {
  setCookie("openModal", "true", 1);
  window.location.href= "index.html";
}
