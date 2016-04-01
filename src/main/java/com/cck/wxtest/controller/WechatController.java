package com.cck.wxtest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

@RestController
@RequestMapping(value = "/wechat")
public class WechatController {
	@Value("wechat.token")
	private String token;
	@Value("wechat.appId")
	private String appId;
	@Value("wechat.encodingAesKey")
	private String encodingAesKey;

	@RequestMapping(method = RequestMethod.GET)
	public String valid(String signature, String timestamp, String nonce, String echostr) throws AesException {
		WXBizMsgCrypt tool = new WXBizMsgCrypt(token, new String(new char[43]), appId);
		return tool.verifyUrl(signature, timestamp, nonce, echostr);
	}
}
