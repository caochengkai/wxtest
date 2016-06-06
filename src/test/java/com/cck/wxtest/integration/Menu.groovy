package com.cck.wxtest.integration

import org.apache.commons.io.IOUtils

import com.cck.wxtest.controller.WechatController
import com.cck.wxtest.utils.HttpClient
import com.fasterxml.jackson.databind.ObjectMapper


def token = 'test'
def appId = 'wx932e17f406a7efc3'
def appsecret = 'af9851ad6ab66ead87c1a38c393f368e'
def encodingAesKey
def url= 'https://api.weixin.qq.com'
def wechatController=new WechatController(["token":token,"appId":appId,"encodingAesKey":encodingAesKey,"appsecret":appsecret,"url":url]);

def accessToken=wechatController.getAccessToken();

def response=HttpClient.post(url+"/cgi-bin/menu/create?access_token="+accessToken,new ObjectMapper().readValue(IOUtils.toString(Menu.class.getResourceAsStream("menu.json"), "UTF-8"), Map.class));
print response
