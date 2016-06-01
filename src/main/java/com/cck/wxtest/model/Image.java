package com.cck.wxtest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlRootElement(name = "Image")
@XmlAccessorType(XmlAccessType.FIELD)
public class Image {
	@XmlElement(name = "MediaId")
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Override
	@SuppressWarnings("all")
	public int hashCode() {
		return Objects.hashCode(mediaId);
	}

	@Override
	@SuppressWarnings("all")
	public boolean equals(Object object) {
		if (object instanceof Image) {
			Image that = (Image) object;
			return Objects.equal(this.mediaId, that.mediaId);
		}
		return false;
	}

	@Override
	@SuppressWarnings("all")
	public String toString() {
		return Objects.toStringHelper(this).add("mediaId", mediaId).toString();
	}

}
