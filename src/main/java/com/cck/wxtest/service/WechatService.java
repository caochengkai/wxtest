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
			if (message.getRecognition().contains("图片")) {
				parseToImage(message, "Dlrgkn7VaEQftTR3X6oEK8ABkz8VOqDgykXrplk0fk4q3QduZqUPL88j4qaH6-kr");
			}
			if (message.getRecognition().contains("语音")) {
				parseToVoice(message, "YgC6c_31r7EI3uGaja-kJBeY7XzWC9Hsnmc8exsp3ZumxalJ36G_La8X5Dp5m6Ou");
			} if (message.getRecognition().contains("视频")) {
				parseToVideo(message, "jKDrpIwn0Yg9SxCLWUqX2yJpZEAaoWCoWqOXsXm_CZfpS9ptE-UVq_xA2v6eauRR");
			}else {
				parseToText(message, "请再说一遍");
			}
		}
		return convertMessage(message);
	}

	private void parseToText(Message message, String content) {
		message.setMsgType(MessageType.text);
		message.setContent(content);
	}

	private void parseToVoice(Message message, String mediaId) {
		message.setMsgType(MessageType.voice);
		message.setMediaId(mediaId);
	}
	
	private void parseToVideo(Message message, String mediaId) {
		message.setMsgType(MessageType.video);
		message.setMediaId(mediaId);
	}

	private void parseToImage(Message message, String mediaId) {
		message.setMsgType(MessageType.image);
		message.setMediaId(mediaId);
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
