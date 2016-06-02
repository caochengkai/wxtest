package com.cck.wxtest.integration;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpsUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpsUtil.class);

	public static String post(String url, File file) throws Exception {
		URL url1 = new URL(url);
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();
		String result = null;
		con.setDoInput(true);

		con.setDoOutput(true);

		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息

		con.setRequestProperty("Connection", "Keep-Alive");

		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界

		String BOUNDARY = "----------" + System.currentTimeMillis();

		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="

		+ BOUNDARY);

		// 请求正文信息

		// 第一部分：

		StringBuilder sb = new StringBuilder();

		sb.append("--"); // 必须多两道线

		sb.append(BOUNDARY);

		sb.append("\r\n");

		sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length() + "\";filename=\""

		+ file.getName() + "\"\r\n");

		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流

		OutputStream out = new DataOutputStream(con.getOutputStream());

		// 输出表头

		out.write(head);

		// 文件正文部分

		// 把文件已流文件的方式 推入到url中

		DataInputStream in = new DataInputStream(new FileInputStream(file));

		int bytes = 0;

		byte[] bufferOut = new byte[1024];

		while ((bytes = in.read(bufferOut)) != -1) {

			out.write(bufferOut, 0, bytes);

		}

		in.close();

		// 结尾部分

		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();

		out.close();

		StringBuffer buffer = new StringBuffer();

		BufferedReader reader = null;

		try {

			// 定义BufferedReader输入流来读取URL的响应

			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String line = null;

			while ((line = reader.readLine()) != null) {

				buffer.append(line);

			}

			if (result == null) {

				result = buffer.toString();

			}

		} catch (IOException e) {

			System.out.println("发送POST请求出现异常！" + e);

			e.printStackTrace();

			throw new IOException("数据读取异常");

		} finally {

			if (reader != null) {

				reader.close();

			}

		}
		return result;
	}

	public static String postVideo(String url, File file, String title, String introduction) throws Exception {
		String result = null;
		try {
			URL url1 = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Cache-Control", "no-cache");
			String boundary = "-----------------------------" + System.currentTimeMillis();
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

			OutputStream output = conn.getOutputStream();
			output.write(("--" + boundary + "\r\n").getBytes());
			output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", file.getName()).getBytes());
			output.write("Content-Type: video/mp4 \r\n\r\n".getBytes());
			byte[] data = new byte[1024];
			int len = 0;
			FileInputStream input = new FileInputStream(file);
			while ((len = input.read(data)) > -1) {
				output.write(data, 0, len);
			}
			output.write(("--" + boundary + "\r\n").getBytes());
			output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
			output.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}", title, introduction).getBytes());
			output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
			output.flush();
			output.close();
			input.close();
			InputStream resp = conn.getInputStream();
			StringBuffer sb = new StringBuffer();
			while ((len = resp.read(data)) > -1)
				sb.append(new String(data, 0, len, "utf-8"));
			resp.close();
			result = sb.toString();
			System.out.println(result);
		} catch (ClientProtocolException e) {
			logger.error("postFile，不支持http协议", e);
		} catch (IOException e) {
			logger.error("postFile数据传输失败", e);
		}
		logger.info("{}: result={}", url, result);
		return result;
	}
}
