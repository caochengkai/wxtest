package com.cck.wxtest.controller;

import io.github.elkan1788.mpsdk4j.repo.com.qq.weixin.mp.aes.AesException;
import io.github.elkan1788.mpsdk4j.repo.com.qq.weixin.mp.aes.SHA1;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cck.wxtest.model.Message;
import com.cck.wxtest.service.WechatService;
import com.cck.wxtest.utils.HttpClient;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@RestController
@RequestMapping(value = "/wechat")
public class WechatController {
	@Value("${wechat.token}")
	private String token;
	@Value("${wechat.appId}")
	private String appId;
	@Value("${wechat.encodingAesKey}")
	private String encodingAesKey;
	@Value("${wechat.appsecret}")
	private String appsecret;
	@Value("${wechat.url}")
	private String url;

	private Logger logger = LoggerFactory.getLogger(WechatController.class);

	private Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(7200, TimeUnit.SECONDS).build();

	@Autowired
	private WechatService wechatService;

	@RequestMapping(method = RequestMethod.GET)
	public String valid(String signature, String timestamp, String nonce, String echostr) throws AesException {
		String[] array = new String[] { token, timestamp, nonce };
		Arrays.sort(array);
		String result = SHA1.calculate(array[0] + array[1] + array[2]);
		if (result.equals(signature)) {
			return echostr;
		} else {
			throw new AesException(AesException.ValidateSignatureError);
		}
	}

	public String getAccessToken() throws ExecutionException {
		return cache.get("access_token", new Callable<String>() {
			@Override
			public String call() throws Exception {
				Map map = HttpClient.get(url + "/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appsecret);
				return map.get("access_token").toString();
			}
		});
	}

	@RequestMapping(method = RequestMethod.POST)
	public String receive(String signature, String timestamp, String nonce, @RequestBody String request) {
		logger.info("into method:receive,params: signature ->{},timestamp ->{},nonce ->{},request ->{}", signature, timestamp, nonce, request);
		try {
			Message message = (Message) JAXBContext.newInstance(Message.class).createUnmarshaller().unmarshal(new StringReader(request));
			return wechatService.receive(message);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return "success";
	}

}
