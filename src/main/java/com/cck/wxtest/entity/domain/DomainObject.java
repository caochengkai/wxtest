package com.cck.wxtest.entity.domain;

import java.util.Date;
import java.util.Properties;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.google.common.base.Objects;

public class DomainObject {
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", length = 32)
	@Id
	protected String id;

	@Column(name = "create_date", nullable = false, updatable = false)
	protected Date createDate;

	@Column(name = "last_modify_date", nullable = false)
	protected Date lastModifyDate;

	@Column(name = "properties", nullable = true)
	@Type(type = "com.cck.wxtest.entity.domain.PropertiesType")
	protected Properties properties;

	public String getId() {
		return id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	@SuppressWarnings("all")
	public int hashCode() {
		return Objects.hashCode(id, createDate, lastModifyDate, properties);
	}

	@Override
	@SuppressWarnings("all")
	public boolean equals(Object object) {
		if (object instanceof DomainObject) {
			DomainObject that = (DomainObject) object;
			return Objects.equal(this.id, that.id) && Objects.equal(this.createDate, that.createDate) && Objects.equal(this.lastModifyDate, that.lastModifyDate)
					&& Objects.equal(this.properties, that.properties);
		}
		return false;
	}

	@Override
	@SuppressWarnings("all")
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("createDate", createDate).add("lastModifyDate", lastModifyDate).add("properties", properties).toString();
	}

}
