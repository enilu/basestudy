/**
 *Test01.java
 *Version1.0
 *2015-1-12
 *Copyright cnendata.com
 *
 */
package com.gnst.coal.collector.shisuan.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * <a href="http://ahopedog.iteye.com/blog/1154378">httpclient测试例子</a><br>
 * <!--<br>
 * 历史记录：<br>
 * --------------------------------------------------------
 * 2015-1-12,enilu(82552623@qq.com)新建文档<br>
 * 
 * -->
 * 
 * @author enilu(82552623@qq.com)
 * 
 *         since1.0
 */
public class Test01 {
	public static void readResponse(InputStream in) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}

	public static void main(String[] args) throws Exception {
		testPost();
	}

	public static void testGet() throws Exception {

		// (1) 创建HttpGet实例
		HttpGet get = new HttpGet("http://www.126.com");

		// // 创建查询参数开始-------------
		// List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("name", "ahopedog"));
		// params.add(new BasicNameValuePair("work", "程序员"));
		// String queryString = URLEncodedUtils.format(params, "utf-8");
		// URI uri = URIUtils.createURI("http", "localhost", 8080,
		// "/jsx/servlet", queryString, null);
		// HttpGet get = new HttpGet(uri);
		// 创建查询参数结束-----------------

		// (2) 使用HttpClient发送get请求，获得返回结果HttpResponse
		HttpClient http = new DefaultHttpClient();
		HttpResponse response = http.execute(get);

		// (3) 读取返回结果
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();

			InputStream in = entity.getContent();

			readResponse(in);
		}

	}

	public static void testPost() throws Exception {
		// (1) 创建HttpGet实例
		HttpPost post = new HttpPost("http://www.126.com");

		// 带post参数开始------------------
		// HttpPost post = new HttpPost("http://localhost:8080/jsx/servlet");
		// List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("name", "ahopedog"));
		// params.add(new BasicNameValuePair("work", "程序员"));
		// post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		// HttpClient http = new DefaultHttpClient();
		// HttpResponse response = http.execute(post);
		// 带post参数结束------------------

		// (2) 使用HttpClient发送get请求，获得返回结果HttpResponse
		HttpClient http = new DefaultHttpClient();
		HttpResponse response = http.execute(post);

		// (3) 读取返回结果
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();

			InputStream in = entity.getContent();
			readResponse(in);
		}

	}

}
