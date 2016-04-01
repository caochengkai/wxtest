package com.cck.wxtest.controller;

import io.github.elkan1788.mpsdk4j.repo.com.qq.weixin.mp.aes.AesException;
import io.github.elkan1788.mpsdk4j.repo.com.qq.weixin.mp.aes.SHA1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wechat")
public class WechatController {
	@Value("${wechat.token}")
	private String token;
	@Value("${wechat.appId}")
	private String appId;
	@Value("${wechat.encodingAesKey}")
	private String encodingAesKey;

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
}
