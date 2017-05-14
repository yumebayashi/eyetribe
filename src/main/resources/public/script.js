var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/eyetribe/");

webSocket.onopen = function () {
    // alert("WebSocket connection connected")
}

webSocket.onmessage = function (msg) {
    var data = JSON.parse(msg.data);
    var pos = data.pos;
    update(pos);
};
webSocket.onclose = function () {
    // alert("WebSocket connection closed")
};

function update(pos) {
    $(".leftup").css("border", "");
    $(".centerup").css("border", "");
    $(".rightup").css("border", "");
    $(".leftmiddle").css("border", "");
    $(".centermiddle").css("border", "");
    $(".rightmiddle").css("border", "");
    $(".rightdown").css("border", "");
    $(".centerdown").css("border", "");
    $(".leftdown").css("border", "");
    $("." + pos).css("border", "15px dashed white");
}