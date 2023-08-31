package com.linxi.gkos.conteoller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.common.annotation.ResultLoginToken;
import com.linxi.gkos.common.annotation.UserLoginToken;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.pojo.dto.MessageDto;
import com.linxi.gkos.pojo.po.Message;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.pojo.vo.json.ListJsonVo;
import com.linxi.gkos.pojo.vo.req.MessageReqVo;
import com.linxi.gkos.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/message")
public class MessageController extends BaseController<Message> {
    @Autowired
    private MessageService service;
    @Override
    protected IService<Message> getService() {
        return service;
    }

    @UserLoginToken
    @PostMapping("/fromUser")
    @ResponseBody
    public JsonVo fromUser(@RequestBody MessageReqVo messageReqVo){
        Message message= new Message();
        message.setFromId("u_"+messageReqVo.getFromId());
        message.setToId("r_"+messageReqVo.getToId());
        message.setContent(messageReqVo.getContent());
        message.setCreateTime(new Date());
        return service.fromUser(message);
    }

    @ResultLoginToken
    @PostMapping("/fromResult")
    @ResponseBody
    public JsonVo fromResult(@RequestBody MessageReqVo messageReqVo){
        Message message= new Message();
        message.setFromId("r_"+messageReqVo.getFromId());
        message.setToId("u_"+messageReqVo.getToId());
        message.setContent(messageReqVo.getContent());
        message.setCreateTime(new Date());
        return service.fromResult(message);
    }

    @UserLoginToken
    @PostMapping("/userList")
    @ResponseBody
    public ListJsonVo<MessageDto> userList(@RequestBody MessageReqVo messageReqVo){
        Message message= new Message();
        message.setFromId("u_"+messageReqVo.getFromId());
        message.setToId("r_"+messageReqVo.getToId());
        return JsonVos.ok(service.userList(message));
    }

    @ResultLoginToken
    @PostMapping("/resultList")
    @ResponseBody
    public ListJsonVo<MessageDto> resultList(@RequestBody MessageReqVo messageReqVo){
        Message message= new Message();
        message.setFromId("r_"+messageReqVo.getFromId());
        message.setToId("u_"+messageReqVo.getToId());
        return JsonVos.ok(service.resultList(message));
    }
}
