package com.cck.wxtest.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpClient {
	private static CloseableHttpClient httpclient = HttpClients.createDefault();

	public static Map get(String url) throws IOException {
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		return new ObjectMapper().readValue(EntityUtils.toString(entity), Map.class);
	}

	public static Map post(String url, Map<String, String> params) throws IOException {
		HttpPost httppost = new HttpPost(url);
		if (MapUtils.isNotEmpty(params)) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		}
		CloseableHttpResponse response = httpclient.execute(httppost);
		return new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()), Map.class);
	}
}
