package com.cck.wxtest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
	@XmlElement(name = "ToUserName")
	private String toUserName;
	@XmlElement(name = "FromUserName")
	private String fromUserName;
	@XmlElement(name = "CreateTime")
	private Long createTime;
	@XmlElement(name = "MsgType")
	private MessageType msgType;
	@XmlElement(name = "Content")
	private String content;
	@XmlElement(name = "MsgId")
	private Long msgId;

	@XmlElement(name = "Image")
	private Image image;
	@XmlElement(name = "Voice")
	private Voice voice;
	@XmlElement(name = "Video")
	private Video video;
	@XmlElement(name = "Music")
	private Music music;

	@XmlElement(name = "ArticleCount")
	private String articleCount;
	@XmlElementWrapper(name = "Articles")
	@XmlElement(name = "item")
	private List<Article> articles;

	@XmlElement(name = "Format")
	private String format;
	@XmlElement(name = "Recognition")
	private String recognition;

	public String getToUserName() {
		return toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public MessageType getMsgType() {
		return msgType;
	}

	public String getContent() {
		return content;
	}

	public Long getMsgId() {
		return msgId;
	}

	public Image getImage() {
		return image;
	}

	public Voice getVoice() {
		return voice;
	}

	public Video getVideo() {
		return video;
	}

	public Music getMusic() {
		return music;
	}

	public String getArticleCount() {
		return articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public String getFormat() {
		return format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	@Override
	@SuppressWarnings("all")
	public int hashCode() {
		return Objects.hashCode(toUserName, fromUserName, createTime, msgType, content, msgId, image, voice, video, music, articleCount, articles, format, recognition);
	}

	@Override
	@SuppressWarnings("all")
	public boolean equals(Object object) {
		if (object instanceof Message) {
			Message that = (Message) object;
			return Objects.equal(this.toUserName, that.toUserName) && Objects.equal(this.fromUserName, that.fromUserName) && Objects.equal(this.createTime, that.createTime)
					&& Objects.equal(this.msgType, that.msgType) && Objects.equal(this.content, that.content) && Objects.equal(this.msgId, that.msgId) && Objects.equal(this.image, that.image)
					&& Objects.equal(this.voice, that.voice) && Objects.equal(this.video, that.video) && Objects.equal(this.music, that.music) && Objects.equal(this.articleCount, that.articleCount)
					&& Objects.equal(this.articles, that.articles) && Objects.equal(this.format, that.format) && Objects.equal(this.recognition, that.recognition);
		}
		return false;
	}

	@Override
	@SuppressWarnings("all")
	public String toString() {
		return Objects.toStringHelper(this).add("toUserName", toUserName).add("fromUserName", fromUserName).add("createTime", createTime).add("msgType", msgType).add("content", content)
				.add("msgId", msgId).add("image", image).add("voice", voice).add("video", video).add("music", music).add("articleCount", articleCount).add("articles", articles).add("format", format)
				.add("recognition", recognition).toString();
	}

}
