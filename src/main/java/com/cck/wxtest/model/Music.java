package com.cck.wxtest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlRootElement(name = "Music")
@XmlAccessorType(XmlAccessType.FIELD)
public class Music {
	@XmlElement(name = "Title")
	private String title;
	@XmlElement(name = "Description")
	private String description;
	@XmlElement(name = "MusicUrl")
	private String musicUrl;
	@XmlElement(name = "HQMusicUrl")
	private String hQMusicUrl;
	@XmlElement(name = "ThumbMediaId")
	private String thumbMediaId;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public String gethQMusicUrl() {
		return hQMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	@Override
	@SuppressWarnings("all")
	public int hashCode() {
		return Objects.hashCode(title, description, musicUrl, hQMusicUrl, thumbMediaId);
	}

	@Override
	@SuppressWarnings("all")
	public boolean equals(Object object) {
		if (object instanceof Music) {
			Music that = (Music) object;
			return Objects.equal(this.title, that.title) && Objects.equal(this.description, that.description) && Objects.equal(this.musicUrl, that.musicUrl)
					&& Objects.equal(this.hQMusicUrl, that.hQMusicUrl) && Objects.equal(this.thumbMediaId, that.thumbMediaId);
		}
		return false;
	}

	@Override
	@SuppressWarnings("all")
	public String toString() {
		return Objects.toStringHelper(this).add("title", title).add("description", description).add("musicUrl", musicUrl).add("hQMusicUrl", hQMusicUrl).add("thumbMediaId", thumbMediaId).toString();
	}

}
