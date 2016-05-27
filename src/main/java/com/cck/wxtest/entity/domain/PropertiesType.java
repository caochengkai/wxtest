package com.cck.wxtest.entity.domain;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.TextType;
import org.hibernate.usertype.UserType;

public class PropertiesType implements UserType {

	private static final int[] SQL_TYPES = { Types.VARCHAR };

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return deepCopy(original);
	}

	@Override
	public Object assemble(Serializable cached, Object object) throws HibernateException {
		return deepCopy(cached);
	}

	@Override
	public Serializable disassemble(Object object) throws HibernateException {
		return (Serializable) deepCopy(object);
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		if (value == null) {
			return null;
		} else if (!(value instanceof Properties)) {
			throw new IllegalArgumentException("the value " + value.getClass().getName() + " must be instance of " + Properties.class.getName());
		} else {
			return clone((Properties) value);
		}
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y)
			return true;
		if (x == null || y == null)
			return false;
		return x.equals(y);
	}

	@Override
	public int hashCode(Object value) throws HibernateException {
		if (value == null) {
			return 31;
		} else {
			return value.hashCode();
		}
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
		String string = (String) TextType.INSTANCE.nullSafeGet(resultSet, names, session, owner);
		if (StringUtils.isBlank(string)) {
			return null;
		}

		SortProperties sortProperties = new SortProperties();
		try {
			sortProperties.load(new StringReader(string));
		} catch (IOException ignored) {
		}
		return sortProperties;
	}

	@Override
	public void nullSafeSet(PreparedStatement statement, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		if (value == null) {
			statement.setNull(index, Types.VARCHAR);
		} else if (!(value instanceof Properties)) {
			throw new IllegalArgumentException("the value " + value.getClass().getName() + " must be instance of " + Properties.class.getName());
		} else {
			SortProperties sortProperties = new SortProperties();
			sortProperties.putAll((Properties) value);
			statement.setString(index, sortProperties.toString());
		}
	}

	private static Properties clone(Properties properties) {
		if (properties == null) {
			return null;
		}
		Properties clone = new Properties();

		Set<String> keys = properties.stringPropertyNames();
		for (String key : keys) {
			clone.setProperty(key, properties.getProperty(key));
		}
		return clone;
	}

	@Override
	public Class<?> returnedClass() {
		return Properties.class;
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

}
