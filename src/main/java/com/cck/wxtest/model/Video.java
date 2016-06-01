package com.cck.wxtest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlRootElement(name = "Video")
@XmlAccessorType(XmlAccessType.FIELD)
public class Video {
	@XmlElement(name = "MediaId")
	private String mediaId;
	@XmlElement(name = "Title")
	private String title;
	@XmlElement(name = "Description")
	private String description;

	public String getMediaId() {
		return mediaId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	@SuppressWarnings("all")
	public int hashCode() {
		return Objects.hashCode(mediaId, title, description);
	}

	@Override
	@SuppressWarnings("all")
	public boolean equals(Object object) {
		if (object instanceof Video) {
			Video that = (Video) object;
			return Objects.equal(this.mediaId, that.mediaId) && Objects.equal(this.title, that.title) && Objects.equal(this.description, that.description);
		}
		return false;
	}

	@Override
	@SuppressWarnings("all")
	public String toString() {
		return Objects.toStringHelper(this).add("mediaId", mediaId).add("title", title).add("description", description).toString();
	}

}
