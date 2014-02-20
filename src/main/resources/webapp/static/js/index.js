
function initialize()
{

}

function try_login()
{
  var username = $("#jq_username").val();
  var pw = $("#jq_password").val();

  var url = 'localhost:8080/login?ajax=true';

    $.ajax({
        type: "POST",
        url: "login" ,
        data: {"username":username, "password":pw},
        success: function(data)
        {
            console.log(data)

        }
    });


}

$(document).ready(function()
{
    $("#login-btn").click(try_login);
});

