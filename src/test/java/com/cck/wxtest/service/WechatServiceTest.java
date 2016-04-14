package com.cck.wxtest.service;

import static org.junit.Assert.*;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.cck.wxtest.model.Message;

public class WechatServiceTest {
	private WechatService wechatService=new WechatService();
	@Test
	public void receive() throws Exception {
		String request = IOUtils.toString(this.getClass().getResourceAsStream("message.xml"), "UTF-8");
		Message message = (Message) JAXBContext.newInstance(Message.class).createUnmarshaller().unmarshal(new StringReader(request));
		String receive = wechatService.receive(message);
		System.out.println(receive);
	}
}
