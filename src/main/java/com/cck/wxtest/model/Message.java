package com.cck.wxtest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
	@XmlElement(name = "MediaId")
	private String mediaId;
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

	public String getContent() {
		return content;
	}

	public Long getMsgId() {
		return msgId;
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

	public void setContent(String content) {
		this.content = content;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public MessageType getMsgType() {
		return msgType;
	}

	public void setMsgType(MessageType msgType) {
		this.msgType = msgType;
	}

	public String getMediaId() {
		return mediaId;
	}

	public String getFormat() {
		return format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	@Override
	public String toString() {
		return "Message [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType + ", content=" + content + ", msgId=" + msgId
				+ ", mediaId=" + mediaId + ", format=" + format + ", recognition=" + recognition + "]";
	}

}
