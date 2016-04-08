package com.cck.wxtest.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("rawtypes")
public class HttpClient {
	private static CloseableHttpClient httpclient = HttpClients.createDefault();

	public static Map get(String url) throws IOException {
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		return new ObjectMapper().readValue(EntityUtils.toString(entity), Map.class);
	}

	public static Map post(String url, Map jsonMap) throws IOException {
		HttpPost httppost = new HttpPost(url);
		if (MapUtils.isNotEmpty(jsonMap)) {
			StringWriter out = new StringWriter();
			new ObjectMapper().writeValue(out, jsonMap);
			httppost.setEntity(new StringEntity(out.toString(), "UTF-8"));
		}
		CloseableHttpResponse response = httpclient.execute(httppost);
		return new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()), Map.class);
	}
}
