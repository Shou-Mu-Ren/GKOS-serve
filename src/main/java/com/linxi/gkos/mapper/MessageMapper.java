package com.linxi.gkos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linxi.gkos.pojo.dto.MessageDto;
import com.linxi.gkos.pojo.po.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
    void insertMessage(Message message);
    List<MessageDto> findMessageByToAndFrom(Message message);
}
