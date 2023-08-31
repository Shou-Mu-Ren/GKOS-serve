package com.linxi.gkos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linxi.gkos.common.util.JsonVos;
import com.linxi.gkos.mapper.MessageMapper;
import com.linxi.gkos.mapper.ProvinceMapper;
import com.linxi.gkos.pojo.dto.MessageDto;
import com.linxi.gkos.pojo.po.Message;
import com.linxi.gkos.pojo.po.Province;
import com.linxi.gkos.pojo.vo.json.JsonVo;
import com.linxi.gkos.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.linxi.gkos.pojo.result.CodeMsg.REQUEST_OK;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    @Autowired
    private MessageMapper mapper;

    @Override
    public JsonVo fromUser(Message message) {
        mapper.insertMessage(message);
        return JsonVos.ok(REQUEST_OK);
    }

    @Override
    public JsonVo fromResult(Message message) {
        mapper.insertMessage(message);
        return JsonVos.ok(REQUEST_OK);
    }

    @Override
    public List<MessageDto> userList(Message message) {
        try {
            return mapper.findMessageByToAndFrom(message);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        return null;
    }

    @Override
    public List<MessageDto> resultList(Message message) {
        return mapper.findMessageByToAndFrom(message);
    }
}
