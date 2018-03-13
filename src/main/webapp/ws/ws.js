/**
 * Created by wqquan.wang on 2018/3/13.
 */
$(function(){
    WebChat.init();
});

WebChat = function(me) {
    return me = {
        userName:null,
        socket:null,
        init : function() {
            if (!window.WebSocket) {
                alert("你的浏览器不支持WebSocket！");
                return;
            }
            WebChat.userName = '用户' + Math.floor((Math.random() * 1000) + 1);
            WebChat.socket = new WebSocket("ws://127.0.0.1:7000/chat");
            WebChat.socket.onopen = function(event) {
                output('连接开启!');
            };
            WebChat.socket.onclose = function(event) {
                output('连接被关闭!');
            };
            WebChat.socket.onmessage = function(event) {
                output(event.data);
            };

            function output(message) {
                console.log(message);
            }
        },
        send : function (message) {
            if (!window.WebSocket) {
                alert("你的浏览器不支持WebSocket！");
                return;
            }
            if (! (WebChat.socket.readyState == WebSocket.OPEN) ) {
                alert("连接没有开启！");
                return;
            }
            if (message != "") {
                WebChat.socket.send(message);
            }
        }
    };
}();