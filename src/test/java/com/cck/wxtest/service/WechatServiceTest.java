package com.cck.wxtest.service;

import java.io.InputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import com.cck.wxtest.model.Message;

public class WechatServiceTest {
	private WechatService wechatService = null;

	@Before
	public void setUp() {
		wechatService = new WechatService();
	}

	@Test
	public void receive() throws Exception {
		InputStream resourceAsStream = this.getClass().getResourceAsStream("message.xml");
		String request = IOUtils.toString(resourceAsStream, "UTF-8");
		Message message = (Message) JAXBContext.newInstance(Message.class).createUnmarshaller().unmarshal(new StringReader(request));
		String receive = wechatService.receive(message);
		System.out.println(receive);
	}
}
