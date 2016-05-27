//Establish the WebSocket connection and set up event handlers
var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat/");
webSocket.onmessage = function (msg) {
    updateChat(msg);
};
webSocket.onclose = function () {
    //alert("WebSocket connection closed")
};

//Update the chat-panel, and the list of connected users
function updateChat(msg) {
    var data = JSON.parse(msg.data);
    var pos = data.pos;
    if (data.type == "stop") {
        updateValid(pos);
        //if (pos == "leftup") {
        //    new Audio('https://dl.dropboxusercontent.com/u/27040096/music/ume/kecak1.wav').play();
        //} else if (pos == "rightup") {
        //    new Audio('https://dl.dropboxusercontent.com/u/27040096/music/ume/kecak2.wav').play();
        //} else if (pos == "rightdown") {
        //    new Audio('https://dl.dropboxusercontent.com/u/27040096/music/ume/kecak3.wav').play();
        //} else if (pos == "leftdown") {
        //    new Audio('https://dl.dropboxusercontent.com/u/27040096/music/ume/kecak4.wav').play();
        //}
    } else {
        document.getElementsByClassName("eye")[0].style.top = pos.split(":")[0];
        document.getElementsByClassName("eye")[0].style.left = pos.split(":")[1];
    }

}

function updateValid(pos) {
    document.getElementsByClassName('leftup')[0].style.border = '0px dashed white';
    document.getElementsByClassName('centerup')[0].style.border = '0px dashed white';
    document.getElementsByClassName('rightup')[0].style.border = '0px dashed white';
    document.getElementsByClassName('leftmiddle')[0].style.border = '0px dashed white';
    document.getElementsByClassName('centermiddle')[0].style.border = '0px dashed white';
    document.getElementsByClassName('rightmiddle')[0].style.border = '0px dashed white';
    document.getElementsByClassName('rightdown')[0].style.border = '0px dashed white';
    document.getElementsByClassName('centerdown')[0].style.border = '0px dashed white';
    document.getElementsByClassName('leftdown')[0].style.border = '0px dashed white';

    if (pos == "leftup") {
        document.getElementsByClassName('leftup')[0].style.border = '15px dashed white';
    } else if (pos == "centerup") {
        document.getElementsByClassName('centerup')[0].style.border = '15px dashed white';
    } else if (pos == "rightup") {
        document.getElementsByClassName('rightup')[0].style.border = '15px dashed white';
    } else if (pos == "leftmiddle") {
        document.getElementsByClassName('leftmiddle')[0].style.border = '15px dashed white';
    } else if (pos == "centermiddle") {
        document.getElementsByClassName('centermiddle')[0].style.border = '15px dashed white';
    } else if (pos == "rightmiddle") {
        document.getElementsByClassName('rightmiddle')[0].style.border = '15px dashed white';
    } else if (pos == "leftdown") {
        document.getElementsByClassName('leftdown')[0].style.border = '15px dashed white';
    } else if (pos == "centerdown") {
        document.getElementsByClassName('centerdown')[0].style.border = '15px dashed white';
    } else if (pos == "rightdown") {
        document.getElementsByClassName('rightdown')[0].style.border = '15px dashed white';
    }
}