package com.cck.wxtest.entity;

import javax.persistence.Entity;

import com.cck.wxtest.entity.domain.DomainObject;

@Entity(name = "topic")
public class Topic extends DomainObject {

	private String title;

	private String content;

}
