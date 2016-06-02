package com.cck.wxtest.integration
import com.cck.wxtest.controller.WechatController
import com.fasterxml.jackson.databind.ObjectMapper


def token = 'test'
def appId = 'wx932e17f406a7efc3'
def appsecret = 'af9851ad6ab66ead87c1a38c393f368e'
def encodingAesKey
def url= 'https://api.weixin.qq.com'
def wechatController=new WechatController(["token":token,"appId":appId,"encodingAesKey":encodingAesKey,"appsecret":appsecret,"url":url]);

def accessToken=wechatController.getAccessToken();

Map result=addMaterialEver(accessToken,new File("E:/SHDNLPCJ.jpg"),"image");

print result;
public Map addMaterialEver(String accessToken, File file, String type) throws Exception {
	String path = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + accessToken + "&type=" + type;
	String result=null;
	if("video".equals(type)){
		result = HttpsUtil.postVideo(path, file,"title","introduction");
	}else{
		result = HttpsUtil.post(path, file);
	}
	result = result.replaceAll("[\\\\]", "");
	return new ObjectMapper().readValue(result, Map.class);
}