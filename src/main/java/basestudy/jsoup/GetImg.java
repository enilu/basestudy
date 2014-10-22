package basestudy.jsoup;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * maindescript
 * <p/>
 * </p>
 * 2014年9月22日
 * 
 * @author enilu(82552623@qq.com)
 * 
 */
public class GetImg {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		GetImg gi = new GetImg();
		for (int i = 601844; i <= 6020999; i++) {
			String url = "http://gubit.chaguwang.cn/topview/081231znz/index.jpg";
			url = url.replace("index", i + "");
			gi.downloand(url, "/media/data/getimg/" + i + ".jpg");
		}
	}

	public void downloand(String imgUrl, String fileName) {

		try {
			URL url = new URL(imgUrl);// ：获取的路径
			// :http协议连接对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(6 * 10000);
			if (conn.getResponseCode() < 10000) {
				InputStream inputStream = conn.getInputStream();
				byte[] data = readStream(inputStream);
				// if (data.length > (1024 * 10)) {
				System.out.println("保存图片：" + fileName);
				FileOutputStream outputStream = new FileOutputStream(fileName);
				outputStream.write(data);
				outputStream.close();
				// }
			}
		} catch (Exception e) {
			System.out.println("图片下载失败：" + e.getMessage());
		}

	}

	public static byte[] readStream(InputStream inputStream) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		outputStream.close();
		inputStream.close();
		return outputStream.toByteArray();
	}

}
