/**
 *Main.java
 *Version1.0
 *2015-1-11
 *Copyright cnendata.com
 *
 */
package com.gnst.coal.collector.huoyun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

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
			m.collector();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据车号，或票号验证码查询货运信息<br>
	 * 保证验证码和查询请求使用同一个httpclient对象
	 * <p>
	 * 1,下载验证码保存到指定目录，并返回验证码名称<br>
	 * 2,控制台接收用户输入验证码<测试用，实际项目中接收用户从页面输入的验证码><br>
	 * 3,根据车号，或票号，验证码，提交查询请求<br>
	 * 4,处理请求返回的结果<br>
	 * 4.1,失败结果：{"message":"货车追踪失败，请稍后再试！","object":null,"success":false}<br>
	 * 4.2,验证码错误：{"message":"验证码输入错误！","object":null,"success":false} <br>
	 * {"message":"操作成功","object":[{"bDataVaild":true,"carKind":"敞车","carLE":"L"
	 * ,"carNo":"1702092","carType":"C70","cdyAdm":"北京局","cdyName":"末煤",
	 * "cdyStation"
	 * :"阳泉","conName":"天津阳煤煤炭销售有限公司","destAdm":"济南局","destStation":
	 * "聊城","dz":"",
	 * "dzlc":6,"errorMsg":"","eventAdm":"济南局","eventCity":"聊城市","eventDate"
	 * :"2015- 01-12
	 * 22:24:00","eventProvince":"山东省","eventStation":"聊城北","fz":"","
	 * pm":"","shpName
	 * ":"","trainId":"29401","trainOrder":"27","tyrName":"","wbID":"Q0615
	 * 27","wbNbr":"Q061527","xh":"","xt":""}],"success":true}
	 * </p>
	 * 
	 * </p>
	 * 
	 * @throws Exception
	 */
	public void collector() throws Exception {
		// 1,
		String queryUrl = "http://hyfw.12306.cn/gateway/DzswNewD2D/Dzsw/action/ChcxAction_queryHwzzInfo";

		DefaultHttpClient client = new DefaultHttpClient();
		String captchaPath = this.downloadGzip(client);
		System.out.println("保存的验证码名称：" + captchaPath);

		// 2,
		Scanner s = new Scanner(System.in);
		String captca = s.nextLine();

		// 3,
		/**
		 * 1, 货票号：Q061527 车号：C70E1702092 2, 货票号：Q061528 车号：C701602774
		 **/
		HttpPost post = new HttpPost(queryUrl);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("carNo", "1602774"));// q506,q061527
		params.add(new BasicNameValuePair("hph", "Q061528"));//
		params.add(new BasicNameValuePair("QUERY_CAPTCA", captca));
		post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		HttpResponse response = client.execute(post);

		// 4,
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}
		client.close();

	}

	/**
	 * 下载使用gzip，分段压缩的图片
	 * 
	 * @param client
	 *            httpclient，该请求使用完毕后不能关闭，后续提交查询需要用
	 * @return 保存的验证码图片名称
	 * @throws Exception
	 */
	public String downloadGzip(DefaultHttpClient client) throws Exception {

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
				"http://hyfw.12306.cn/gateway/DzswNewD2D/Dzsw/security/jcaptcha.jpg?update=0."
						+ new Date().getTime());
		HttpResponse response = client.execute(get);
		String captchaPath = new Date().getTime() + ".jpg";
		// 读取返回结果,并将验证码保存到指定目录
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			saveImg(in, new File("/Users/maggie/Pictures/" + captchaPath));
		}
		return captchaPath;
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

	/**
	 * 测试：下载不适用gzip压缩的图片
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

}
