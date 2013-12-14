


function show_login_dialog() {
  login_success()

  $("#login-dialog").css({"visibility": "visible"});
  $("#background-div").fadeTo("slow",0.1);
  $("#login-dialog").fadeTo("slow",1.0);
  
  title = "Login"

  $('#login-dialog').dialog({
      autoOpen: true,
      modal: true,
      width: 600,
      height: 280,
      title: title,
      buttons: {
          "Login": function () {
	      login_success()
              $(this).dialog("close");
          },
      }
  });

}

function login_success() {
  $("#background-div").fadeTo("slow",1.0);
  $("#login-dialog").fadeTo("slow",0.0);
  $("#login-dialog").css({"visibility": "hidden"});

}
