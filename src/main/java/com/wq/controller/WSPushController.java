package com.wq.controller;

import com.wq.enums.WSActionEnum;
import com.wq.netty.NettyServerManager;
import com.wq.util.JsonUtil;
import com.wq.vo.ProblemVO;
import com.wq.vo.WSResp;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by wqquan.wang on 2018/3/14.
 */
@Controller
public class WSPushController {
    @RequestMapping("/push")
    @ResponseBody
    public String push(String msg) {
        ProblemVO problemVO = new ProblemVO();
        problemVO.setProblemId(100);
        problemVO.setQuestion("今天吃什么");
        ArrayList<ProblemVO.Answer> answers = new ArrayList<>();
        problemVO.setAnswers(answers);
        answers.add(new ProblemVO.Answer("1111", "米饭"));
        answers.add(new ProblemVO.Answer("2222", "外卖"));
        answers.add(new ProblemVO.Answer("3333", "麦当劳"));
        answers.add(new ProblemVO.Answer("4444", "沙县"));

        WSResp wsResp = new WSResp();
        wsResp.setAction(WSActionEnum.ASSIGN.getCode());
        wsResp.setData(problemVO);
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(JsonUtil.toJsonString(wsResp));
        NettyServerManager.CLIENT_GROUP.writeAndFlush(textWebSocketFrame);
        return "success";
    }
}
