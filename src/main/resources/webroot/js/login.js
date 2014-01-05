

function show_new_user_dialog() {
  $("#new-user-dialog").css({"visibility": "visible"});
  $("#background-div").fadeTo("slow",0.1);
  $("#login-dialog").fadeTo("slow",1.0);

  title = "Create New User"

  $('#new-user-dialog').dialog({
    autoOpen: true,
    modal: true,
    width: 600,
    height: 280,
    title: title,
  });

}


function show_login_dialog() {

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
          "New User": function() {
            $("#login-dialog").css({"visibility": "hidden"});
            show_new_user_dialog()
          },
          "Login": function () {
              login_success()
              $(this).dialog("close");
          }

      }
  });

}

function login_success() {
  $("#background-div").fadeTo("slow",1.0);
  $("#login-dialog").fadeTo("slow",0.0);
  $("#login-dialog").css({"visibility": "hidden"});

}
