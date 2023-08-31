package com.linxi.gkos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linxi.gkos.pojo.dto.MessageDto;
import com.linxi.gkos.pojo.po.Message;
import com.linxi.gkos.pojo.vo.json.JsonVo;

import java.util.List;

public interface MessageService extends IService<Message> {
    JsonVo fromUser(Message message);
    JsonVo fromResult(Message message);
    List<MessageDto> userList(Message message);
    List<MessageDto> resultList(Message message);
}
