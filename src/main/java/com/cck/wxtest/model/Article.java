package com.cck.wxtest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlRootElement(name = "Article")
@XmlAccessorType(XmlAccessType.FIELD)
public class Article {
	@XmlElement(name = "Title")
	private String title;
	@XmlElement(name = "Description")
	private String description;
	@XmlElement(name = "PicUrl")
	private String picUrl;
	@XmlElement(name = "Url")
	private String url;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	@SuppressWarnings("all")
	public int hashCode() {
		return Objects.hashCode(title, description, picUrl, url);
	}

	@Override
	@SuppressWarnings("all")
	public boolean equals(Object object) {
		if (object instanceof Article) {
			Article that = (Article) object;
			return Objects.equal(this.title, that.title) && Objects.equal(this.description, that.description) && Objects.equal(this.picUrl, that.picUrl) && Objects.equal(this.url, that.url);
		}
		return false;
	}

	@Override
	@SuppressWarnings("all")
	public String toString() {
		return Objects.toStringHelper(this).add("title", title).add("description", description).add("picUrl", picUrl).add("url", url).toString();
	}

}
