package com.cck.wxtest.model;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class MessageTest {
	@Test
	public void unmarshal() throws Exception {
		String request = IOUtils.toString(this.getClass().getResourceAsStream("message.xml"), "UTF-8");
		Message message = (Message) JAXBContext.newInstance(Message.class).createUnmarshaller().unmarshal(new StringReader(request));
		System.out.println(message);
	}
}
