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
            WebChat.socket = new WebSocket("ws://"+document.domain+":8888/chat");
            WebChat.socket.onopen = function(event) {
                output('连接开启!');
            };
            WebChat.socket.onclose = function(event) {
                output('连接被关闭!');
                setTimeout(function () {
                    console.info("尝试重连")
                    WebChat.init();
                }, 1000);
            };
            WebChat.socket.onmessage = function(event) {
                output(event.data);
                execPush(event.data);
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

function fillQuestionArea (ProblemVO) {
    $("#questionArea").html("");
    $("#questionArea").text(ProblemVO.question);
    $(ProblemVO.answers).each(function(i,o){
        $($(".answerArea")[i]).val(o.value);
        $($(".answerArea")[i]).parent().next().html(o.text);
    });
}

function execPush(data) {
    WSResp = $.parseJSON(data);
    if (WSResp.action == 'ASSIGN') {
        fillQuestionArea(WSResp.data)
    } else if(WSResp.action == 'TAKEUP') {

    }
}