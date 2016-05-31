package com.cck.wxtest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cck.wxtest.entity.domain.DomainObject;
import com.google.common.base.Objects;

@Entity
@Table(name = "topic")
public class Topic extends DomainObject {

	@Column(name = "title", nullable = true)
	private String title;

	@Column(name = "content", nullable = true)
	private String content;

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	@SuppressWarnings("all")
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), title, content);
	}

	@Override
	@SuppressWarnings("all")
	public boolean equals(Object object) {
		if (object instanceof Topic) {
			if (!super.equals(object))
				return false;
			Topic that = (Topic) object;
			return Objects.equal(this.title, that.title) && Objects.equal(this.content, that.content);
		}
		return false;
	}

	@Override
	@SuppressWarnings("all")
	public String toString() {
		return Objects.toStringHelper(this).add("super", super.toString()).add("title", title).add("content", content).toString();
	}

}
