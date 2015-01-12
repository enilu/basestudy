/**
 *Main.java
 *Version1.0
 *2015-1-11
 *Copyright cnendata.com
 *
 */
package com.gnst.coal.collector.huoyun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * <a
 * href="http://hyfw.12306.cn/gateway/DzswNewD2D/Dzsw/page/business-chcx-hwzz"
 * >输入要查询货物的车号和货票号，可查询货物当前报告站</a>
 * <p>
 * <!--<br>
 * 历史记录：<br>
 * --------------------------------------------------------
 * 2015-1-11,enilu(82552623@qq.com)新建文档<br>
 * 
 * -->
 * 
 * @author enilu(82552623@qq.com)
 * 
 *         since1.0
 */
public class Main {
	public static void main(String[] args) {
		Main m = new Main();
		try {
			// m.download();
			m.collector();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void collector() throws Exception {
		String url = "http://hyfw.12306.cn/gateway/DzswNewD2D/Dzsw/action/ChcxAction_queryHwzzInfo";
		// carNo:c62bk
		// hph:4942009
		// QUERY_CAPTCA:cewmj
		Map<String, String> cookies = this.downloadGzip();
		Scanner s = new Scanner(System.in);
		String captca = s.nextLine();

		Document doc = Jsoup.connect(url).data("carNo", "c62bk")
				.data("hph", "4942009").data("QUERY_CAPTCA", captca)
				.cookies(cookies).post();
		System.out.println("---------------------------------");
		System.out.println(doc.text());

	}

	/**
	 * 下载普通图片
	 * 
	 * @throws Exception
	 */
	public void download() throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();

		HttpGet get = new HttpGet(
				"http://www.iteye.com/upload/logo/user/535294/e56c4ee8-5b7e-3f73-b0c1-0a0bdeca6da9.gif?1313124651");
		get.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		File file = new File("/Users/maggie/Pictures/范冰冰.jpg");
		HttpResponse response = client.execute(get);
		// (3) 读取返回结果
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			saveImg(in, file);
		}

	}

	public Map<String, String> downloadGzip() throws Exception {
		Map<String, String> cookies = new HashMap<String, String>();
		DefaultHttpClient client = new DefaultHttpClient();

		client.addResponseInterceptor(new HttpResponseInterceptor() {

			public void process(HttpResponse response, HttpContext arg1)
					throws HttpException, IOException {
				HttpEntity entity = response.getEntity();
				Header ceheader = entity.getContentEncoding();

				if (ceheader != null) {
					for (HeaderElement element : ceheader.getElements()) {
						if (element.getName().equalsIgnoreCase("gzip")) {
							response.setEntity(new GzipDecompressingEntity(
									response.getEntity()));
							return;
						}
					}
				}

			}
		});
		HttpGet get = new HttpGet(
				"http://hyfw.12306.cn/gateway/DzswNewD2D/Dzsw/security/jcaptcha.jpg");
		HttpResponse response = client.execute(get);
		Header[] headers = response.getHeaders("Set-Cookie");
		for (Header header : headers) {
			cookies.put(header.getName(), header.getValue());
		}

		// (3) 读取返回结果
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			saveImg(in,
					new File("/Users/maggie/Pictures/验证码"
							+ new Date().getTime() + ".jpg"));
		}
		return cookies;
	}

	public void saveImg(InputStream in, File file) throws Exception {
		OutputStream out = new FileOutputStream(file);
		int bytesRead = 0;
		int offset = 0;
		byte[] buffer = new byte[18156];
		while ((bytesRead = in.read(buffer, offset, 1024)) != -1) {
			offset += bytesRead;
		}
		out.write(buffer);
		out.close();
		in.close();
	}
}
