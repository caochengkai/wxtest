package com.cck.wxtest.service;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Service;

import com.cck.wxtest.model.Message;
import com.cck.wxtest.model.MessageType;

@Service
public class WechatService {
	public String receive(Message message) throws JAXBException {
		if (message.getMsgType().equals(MessageType.voice)) {
			message.setMsgType(MessageType.text);
			if (message.getRecognition().equals("你好")) {
				message.setContent("你好");
			}else{
				message.setContent("你说什么，请再说一遍");
			}
		}
		StringWriter writer = new StringWriter();
		JAXBContext.newInstance(Message.class).createMarshaller().marshal(message, writer);
		return writer.toString();
	}
}
