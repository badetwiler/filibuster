
function insert_message(words) {
  msg_container = '<p class="msg-container">' + words + '</p>'
  formatted_msg_container  = $(msg_container).css( {
    "margin" : "10px 2px",
    "background" : "#eeeeee",
    "border" : "1px solid #aaaaaa",
  })

  var pane = $('#chat-container')
  pane.jScrollPane({showArrows:true,horizontalGutter:10});
  var api = pane.data('jsp');
  api.getContentPane().append(formatted_msg_container);
  api.reinitialise();
  api.scrollToBottom();
}

function on_talk_btn_click() {
  words = $("#words-input").val()
  if (words != "")
    insert_message(words)
    words = $("#words-input").val("")
    
}

function initialize() {
  $('#chat-container').jScrollPane({showArrows:true});
  $("#say-words-btn").on("click",on_talk_btn_click)
  $("#words-input").on( "keydown", function(e) {
    if(e.which == 13) {on_talk_btn_click()}
  });
}

$(document).ready(function() {
  $("body").css({"overflow-y":"hidden"})
  initialize()
  show_login_dialog()

});

