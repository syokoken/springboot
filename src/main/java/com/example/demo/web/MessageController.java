package com.example.demo.web;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;
import com.example.demo.swagger.BaseResult;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "消息", description = "消息操作 API", protocols = "http")
@RestController
@RequestMapping(value = "rest")
public class MessageController {

    @Autowired
    @Qualifier("messageRepository")
    private MessageRepository messageRepository;

    @ApiOperation(
            value = "消息列表",
            notes = "完整的消息内容列表",
            produces= MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes="application/json, application/xml, charset=UTF-8",
            response = List.class)
    @GetMapping(value = "messages")
    public List<Message> getAll(){
        return messageRepository.findAll();
    }

    @GetMapping(value = "message/{id}")
    public Message getById(@PathVariable Long id){
        return messageRepository.findMessage(id);
    }

    @ApiOperation(value = "添加消息", notes = "根据参数创建消息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "消息 ID", required = true, dataType = "Long", paramType = "query"),
        @ApiImplicitParam(name = "text", value = "正文", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "summary", value = "摘要", required = false, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "message")
    public Message create(Message message){
        return messageRepository.save(message);
    }

    @ApiOperation(value = "修改消息", notes = "根据参数修改消息")
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数有误"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "禁止访问"),
            @ApiResponse(code = 404, message = "请求路径不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    @PutMapping(value = "message")
    public Message update(Message message){
        return messageRepository.update(message);
    }

    @DeleteMapping(value = "message/{id}")
    public void delete(@PathVariable Long id){
        messageRepository.deleteMessage(id);
    }

    @PatchMapping(value = "message/text", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResult<Message> updateText(Message message){
        Message msg = messageRepository.updateText(message);
        return BaseResult.successWithData(msg);
    }

    @GetMapping(value = "messageTest", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Message test(Message message){
        Message msg = new Message();
        msg.setId(1L);
        msg.setText("中文");
        msg.setSummary("sum");
        System.out.println(message);
        return msg;
    }
}
