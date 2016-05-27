package com.cck.wxtest.entity.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class DomainObjectSupportInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = -5931947320312061007L;

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
		if (entity instanceof DomainObject) {
			DomainObject domainObject = (DomainObject) entity;
			boolean modified = false;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("lastModifyDate".equals(propertyNames[i])) {
					Date lastModifyDate = new Date();
					currentState[i] = lastModifyDate;
					domainObject.setLastModifyDate(lastModifyDate);
					modified = true;
				}
			}
			return modified;
		}
		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
		if (entity instanceof DomainObject) {
			DomainObject domainObject = (DomainObject) entity;
			boolean modified = false;
			for (int i = 0; i < propertyNames.length; i++) {
				if ("createDate".equals(propertyNames[i])) {
					Date createDate = new Date();
					state[i] = createDate;
					domainObject.setCreateDate(createDate);
					modified = true;
				} else if ("lastModifyDate".equals(propertyNames[i])) {
					Date lastModifyDate = new Date();
					state[i] = lastModifyDate;
					domainObject.setLastModifyDate(lastModifyDate);
					modified = true;
				}
			}
			return modified;
		}
		return false;
	}

}
