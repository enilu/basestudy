package basestudy.jsoup;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * maindescript
 * <p/>
 * </p>
 * 2014年9月22日
 * 
 * @author enilu(82552623@qq.com)
 * 
 */
public class Login {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String url = "http://mkxkz.chinasafety.gov.cn/";
		Map<String, String> params = new HashMap<String, String>();
		params.put("__EVENTARGUMENT", "");
		params.put("__EVENTTARGET", "btnLogin");
		params.put("__VIEWSTATE",
				"dDw0MjQwNDg5MzM7Oz77XfUMdy4fGehBEMDXaFjn7caDDw==");
		params.put("txtName", "mtxh");
		params.put("txtPwd", "123456");
		params.put("btn.x", "56");
		params.put("btn.y", "19");
		Response response = Jsoup
				.connect("http://mkxkz.chinasafety.gov.cn/Login.aspx")
				.data(params)
				.userAgent(
						"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; tRIDENT/4.0)")
				.method(Method.POST).execute();
		System.out.println(response.statusCode());
		System.out.println(response.parse().title());
		// ASP.NET_SessionId:rp03jwri4nuit145puzkozq4
		String sessionid = response.cookie("ASP.NET_SessionId");
		System.out.println(sessionid);
		Document doc = Jsoup.connect(url + "Default.aspx")
				.cookie("ASP.NET_SessionId", sessionid).get();
		System.out.println(doc.title());
	}

}
