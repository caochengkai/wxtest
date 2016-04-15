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
			if (message.getRecognition().contains("你好")) {
				parseToTest(message, "你好");
			} else {
				parseToTest(message, "请再说一遍");
			}
		}
		return convertMessage(message);
	}

	private void parseToTest(Message message, String content) {
		message.setMsgType(MessageType.text);
		message.setContent(content);
	}

	private String convertMessage(Message message) throws JAXBException {
		StringWriter writer = new StringWriter();
		JAXBContext.newInstance(Message.class).createMarshaller().marshal(message, writer);
		String fromUserName = message.getFromUserName();
		String toUserName = message.getToUserName();
		message.setFromUserName(toUserName);
		message.setToUserName(fromUserName);
		return writer.toString();
	}
}
