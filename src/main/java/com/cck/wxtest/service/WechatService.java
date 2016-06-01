package com.cck.wxtest.service;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Service;

import com.cck.wxtest.model.Article;
import com.cck.wxtest.model.Image;
import com.cck.wxtest.model.Message;
import com.cck.wxtest.model.MessageType;
import com.cck.wxtest.model.Music;
import com.cck.wxtest.model.Video;
import com.cck.wxtest.model.Voice;
import com.google.common.collect.Lists;

@Service
public class WechatService {
	public String receive(Message message) throws JAXBException {
		if (message.getMsgType().equals(MessageType.voice)) {
			String recognition = message.getRecognition();
			handleMessage(message, recognition);
		}
		if (message.getMsgType().equals(MessageType.text)) {
			String content = message.getContent();
			handleMessage(message, content);
		}
		return convertMessage(message);
	}

	private void handleMessage(Message message, String content) {
		if (content.contains("图片")) {
			parseToImage(message, "Dlrgkn7VaEQftTR3X6oEK8ABkz8VOqDgykXrplk0fk4q3QduZqUPL88j4qaH6-kr");
		} else if (content.contains("语音")) {
			parseToVoice(message, "YgC6c_31r7EI3uGaja-kJBeY7XzWC9Hsnmc8exsp3ZumxalJ36G_La8X5Dp5m6Ou");
		} else if (content.contains("视频")) {
			parseToVideo(message, "jKDrpIwn0Yg9SxCLWUqX2yJpZEAaoWCoWqOXsXm_CZfpS9ptE-UVq_xA2v6eauRR");
		} else if (content.contains("图文")) {
			parseToArticle(message);
		} else if (content.contains("音乐")) {
			parseToMusic(message, "http://so1.111ttt.com:8282/2016/1/06/01/199011753551.mp3?tflag=1464775651&pin=c496b8764b22f7ba0c3afc87f6b8ffe9&ip=116.246.19.150#.mp3");
		} else {
			parseToText(message, "无此功能");
		}
	}

	private void parseToText(Message message, String content) {
		message.setMsgType(MessageType.text);
		message.setContent(content);
	}

	private void parseToVoice(Message message, String mediaId) {
		message.setMsgType(MessageType.voice);
		Voice voice = new Voice();
		voice.setMediaId(mediaId);
		message.setVoice(voice);
	}

	private void parseToMusic(Message message, String url) {
		message.setMsgType(MessageType.music);
		Music music = new Music();
		music.setTitle("test");
		music.setDescription("test");
		music.setMusicUrl(url);
		music.sethQMusicUrl(url);
		message.setMusic(music);
	}

	private void parseToArticle(Message message) {
		message.setMsgType(MessageType.news);
		String articleCount="2";
		message.setArticleCount(articleCount);
		Article article1=new Article();
		article1.setTitle("观前街");
		article1.setDescription("观前街位于江苏苏州市区，是成街于清朝时期的百年商业老街，街上老店名店云集，名声远播海内外...");
		article1.setPicUrl("http://52.11.163.107/resource/ghrupload/hiltonimages/NYCHS_exterior.jpg");
		article1.setUrl("http://mp.weixin.qq.com/mp/appmsg/show?__biz=MjM5NDM0NTEyMg==&appmsgid=10000052&itemidx=1&sign=90518631fd3e85dd1fde7f77c04e44d5#wechat_redirect");
		Article article2=new Article();
		article2.setTitle("平江路");
		article2.setDescription("平江路位于苏州古城东北，是一条傍河的小路，北接拙政园，南眺双塔，全长1606米，是苏州一条历史攸久的经典水巷。宋元时候苏州又名平江，以此名路...");
		article2.setPicUrl("http://52.11.163.107/resource/hilton/image/NYCMDHF_Hilton_Manhattan_East_A.jpg");
		article2.setUrl("http://mp.weixin.qq.com/mp/appmsg/show?__biz=MjM5NDM0NTEyMg==&appmsgid=10000056&itemidx=1&sign=ef18a26ce78c247f3071fb553484d97a#wechat_redirect");
		message.setArticles(Lists.newArrayList(article1,article2));
	}

	private void parseToVideo(Message message, String mediaId) {
		message.setMsgType(MessageType.video);
		Video video = new Video();
		video.setMediaId(mediaId);
		video.setTitle("test");
		video.setDescription("test");
		message.setVideo(video);
	}

	private void parseToImage(Message message, String mediaId) {
		message.setMsgType(MessageType.image);
		Image image = new Image();
		image.setMediaId(mediaId);
		message.setImage(image);
	}

	private String convertMessage(Message message) throws JAXBException {
		String fromUserName = message.getFromUserName();
		String toUserName = message.getToUserName();
		message.setFromUserName(toUserName);
		message.setToUserName(fromUserName);
		message.setCreateTime(new Date().getTime());
		StringWriter writer = new StringWriter();
		JAXBContext.newInstance(Message.class).createMarshaller().marshal(message, writer);
		return writer.toString();
	}
}
