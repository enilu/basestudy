package com.gnst.coal.collector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 信息采集主类
 * <p>
 * 2014年9月23日
 * 
 * @author enilu(82552623@qq.com)
 * 
 */
public class Main {
	String sessionId = "ASP.NET_SessionId";
	String cookie = "";
	String baseurl = "http://mkxkz.chinasafety.gov.cn/";
	String successKey = "煤矿安全生产许可证综合管理数据库";
	static DBUtil db = null;

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		db = new DBUtil();
		if (main.login()) {
			main.collector();
			// main.checkCollector();
			// main.collectorDetail("150206272008１３３１１１１１１１");

		}
		db.close();

	}

	/**
	 * 登录系统
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean login() throws Exception {

		Map<String, String> params = new HashMap<String, String>();
		params.put("__EVENTARGUMENT", "");
		params.put("__EVENTTARGET", "btnLogin");
		params.put("__VIEWSTATE",
				"dDw0MjQwNDg5MzM7Oz77XfUMdy4fGehBEMDXaFjn7caDDw==");
		params.put("txtName", "mtxh");
		params.put("txtPwd", "123456");
		params.put("btn.x", "56");
		params.put("btn.y", "19");
		Response response = Jsoup.connect(baseurl + "Login.aspx").data(params)
				.method(Method.POST).timeout(200000).execute();
		Document doc = response.parse();
		String msg = doc.title();
		if (!"登录".equals(msg)) {
			cookie = response.cookie(sessionId);
			return true;
		}
		if (successKey.equals(doc.title().trim())) {
			cookie = response.cookie(sessionId);
			return true;
		}
		return false;

	}

	/**
	 * 采集全部煤矿数据：共12073
	 * 
	 * @throws Exception
	 */

	private void collector() throws Exception {
		if (cookie == null || "".equals(cookie)) {
			return;
		}
		System.out.println("start collector");

		Map<String, String> params = new HashMap<String, String>();
		params.put("cjdy", "");
		params.put("ck", "");
		params.put("ck01_01", "");
		params.put("ck02_01", "");
		params.put("ck03_01", "");
		params.put("ck04_01", "");
		params.put("ck05_01", "");

		params.put("ck06_01", "");
		params.put("ck06_02", "");
		params.put("ck07_01", "");
		params.put("ck07_02", "");

		params.put("ck08_01", "");
		params.put("ck08_02", "");
		params.put("ck09_01", "");
		params.put("ck10_01", "");

		params.put("ck11_01", "");
		params.put("ck12_01", "");
		params.put("ck13_01", "");
		params.put("ck14_01", "");

		params.put("ck14_02", "");
		params.put("ck15_01", "");
		params.put("ck16_01", "");
		params.put("ck17_01", "");
		params.put("ck18_01", "");
		params.put("ck19_01", "");
		params.put("ck20_01", "");
		params.put("ck21_01", "");
		params.put("ck21_02", "");
		params.put("ck22_01", "");
		params.put("ck22_02", "");
		params.put("ck23_01", "");

		params.put("ckStr", "11111111111111111111111");
		params.put("coalfire", "");
		params.put("dqbm", "00000000");
		params.put("gg", "");

		params.put("hd_equal", "");
		params.put("hd_Output", "");
		params.put("mineTraffic", "");
		params.put("mine_jjlx", "");
		params.put("mine_opening", "");
		params.put("miningTech", "");
		params.put("product_time", "");
		params.put("r_Reserves", "");
		params.put("r_equal", "");

		params.put("sbt", "");
		params.put("sbt0", "");
		params.put("setime", "");
		params.put("setime0", "");
		params.put("sj_equal", "");
		params.put("sj_Output", "");
		params.put("sl", "");
		params.put("t_equal", "");
		params.put("t_worker", "");
		Response response = Jsoup.connect(baseurl + "infoQuery/InfoList.aspx")
				.data(params).cookie(sessionId, cookie).method(Method.POST)
				.timeout(100000).execute();

		Document doc = response.parse();
		for (int i = 2; i <= 404; i++) {
			System.out.print("page:" + i + "\r\n");
			Elements elements = doc.getElementById("Table1")
					.getElementsByTag("tbody").get(0).children();
			for (Element element : elements) {
				collectorDetail(element.child(0).text().trim());
			}
			params = new HashMap<String, String>();
			params.put("__EVENTARGUMENT", "");
			params.put("__EVENTTARGET", "lbtnMNext");
			String param3 = doc.getElementById("Form1")
					.getElementsByTag("input").get(2).val();
			System.out.println(param3);
			params.put("__VIEWSTATE", param3);
			response = Jsoup.connect(baseurl + "infoQuery/InfoList.aspx")
					.data(params).cookie(sessionId, cookie).timeout(100000)
					.method(Method.POST).execute();
			doc = response.parse();
		}

	}

	/**
	 * 检查数据库中非法的数据，重新采集更新之
	 * 
	 * @throws Exception
	 */
	private void checkCollector() throws Exception {
		List<String> idList = db.queryIds();
		for (int i = 0; i < idList.size(); i++) {
			String id = idList.get(i);
			db.deleteById(id);
			collectorDetail(id);
		}
	}

	/**
	 * 根据id采集煤矿具体信息
	 * 
	 * @param id
	 * @throws Exception
	 */
	private void collectorDetail(String id) throws Exception {
		String url = baseurl + "infoQuery/MineInfo.aspx?mineid=" + id;
		Document doc = Jsoup.connect(url).cookie(sessionId, cookie)
				.timeout(100000).get();
		String mc = doc.getElementById("td_MineName").text();// 煤矿名称';
		String zuobiao = doc.getElementById("td_MineXYZ").text();// 主井口坐标';
		String fuzerenl = doc.getElementById("td_MineFrdb").text();// 主要负责人';
		String leibie = doc.getElementById("td_MineJjlx").text();// 煤矿类别';
		String dianhua = doc.getElementById("td_MineTel").text();// 联系电话';
		String youbian = doc.getElementById("td_MinePostalCode").text();// 邮政编码';
		String dizhi = doc.getElementById("td_MineAddress").text();// 详细地址';

		String zhuangtai = doc.getElementById("td_MineState").text();// 煤矿状态';
		String mksjscnl = doc.getElementById("td_MineSj").text();// 煤矿设计生产能力';
		String mkhdscnl = doc.getElementById("td_MineHd").text();// 煤矿核定生产能力';
		String mksykccl = doc.getElementById("td_MineRemain").text();// 煤矿剩余可采储量';
		String mktcsj = doc.getElementById("td_MinePTime").text();// 煤矿投产时间';
		String ktfs = doc.getElementById("td_MineOpening").text();// 开拓方式';
		String kcmc = doc.getElementById("td_MineCoalSeam").text();// 开采煤层';
		String cmgy = doc.getElementById("td_MiningTech").text();// 采煤工艺';
		String ysfs = doc.getElementById("td_MineTraffic").text();// 运输方式';
		String jxdyrs = doc.getElementById("td_MineWorker").text();// 井下定员人数';
		String zymz = doc.getElementById("td_MineCoalGrade").text();// 主要煤种';
		String mkgdfs = doc.getElementById("td_MineGdMethod").text();// 煤矿供电方式';

		String aqjkxt = doc.getElementById("td_GasJIANKONG").text();// 安全监控系统';
		String wscfxt = doc.getElementById("td_GasCHOUFANG").text();// 瓦斯抽放系统';
		String wsdj = doc.getElementById("td_GasGrade").text();// 瓦斯等级';
		String mcdzrqxx = doc.getElementById("td_CoalFire").text();// 煤层的自燃倾向性';
		String mcbzwxx = doc.getElementById("td_CoalBlast").text();// 煤层的爆炸危险性';
		String cjdy = doc.getElementById("td_MineCjdy").text();// 冲击地压';
		String mcddbyx = doc.getElementById("td_Mineddb").text();// 煤层顶底板岩性';
		String kjswdzlx = doc.getElementById("td_HydroGeoType").text();// 矿井水文地质类型';

		String aqbh = doc.getElementById("td_SafeLicenseCode").text();// 安全生产许可证-编号';
		String aqzt = doc.getElementById("td_SafeLicenseState").text();// 安全生产许可证-状态';
		String aqbzjg = doc.getElementById("td_SafeLicenseOrg").text();// 安全生产许可证-颁证机构';
		String aqyxq = doc.getElementById("td_SafeLicenseTime").text();// 安全生产许可证-有效期';start-end
																		// start就是颁证日期

		String gszch = doc.getElementById("td_gcCode").text();// 工商营业执照-注册号';
		String gsbzjg = doc.getElementById("td_gcOrg").text();// 工商营业执照-颁证机构';
		String gsbzrq = "";// doc.getElementById("td_gcEndTime").text();//
							// 工商营业执照-颁证日期';
		String gsyxq = doc.getElementById("td_gcEndTime").text();// 工商营业执照-有效期';

		String ckzh = doc.getElementById("td_ckCode").text();// 采矿许可证-证号';
		String ckbzjg = doc.getElementById("td_ckOrg").text();// 采矿许可证-颁证机构';
		String ckbzrq = "";// doc.getElementById("aaaaaa").text();//
							// 采矿许可证-颁证日期';
		String ckyxq = doc.getElementById("td_ckEndTime").text();// 采矿许可证-有效期';

		String mtzh = doc.getElementById("td_coalCode").text();// 煤炭生产许可证-证号';
		String mtbzjg = doc.getElementById("td_coalOrg").text();// 煤炭生产许可证-颁证机构';
		String mtbzrq = "";// doc.getElementById("aaaaaa").text();//
							// 煤炭生产许可证-颁证日期';
		String mtyxq = doc.getElementById("td_coalEndTime").text();// 煤炭生产许可证-有效期';

		String kzbh = doc.getElementById("kzzgID").text();// 矿长资格证-编号';
		String kzbzjg = doc.getElementById("kzzgOrg").text();// 矿长资格证-颁证机构';
		String kzxm = doc.getElementById("kzzgName").text();// 矿长资格证-姓名';
		String kzyxq = doc.getElementById("kzzgEndTime").text();// 矿长资格证-有效期';

		String kzaqbh = doc.getElementById("td_kzCode").text();// 矿长安全资格证-编号';
		String kzaqbzjg = doc.getElementById("td_kzOrg").text();// 矿长安全资格证-颁证机构';
		String kzaqxm = doc.getElementById("td_kzName").text();// 矿长安全资格证-姓名';
		String kzaqyxq = doc.getElementById("td_kzEndTime").text();// 矿长安全资格证-有效期';
		String sql = "INSERT INTO `mk` VALUES ('" + id + "','" + mc + "','"
				+ zuobiao + "','" + fuzerenl + "','" + leibie + "','" + dianhua
				+ "','" + youbian + "','" + dizhi + "','" + zhuangtai + "','"
				+ mksjscnl + "','" + mkhdscnl + "','" + mksykccl + "','"
				+ mktcsj + "','" + ktfs + "','" + kcmc + "','" + cmgy + "','"
				+ ysfs + "','" + jxdyrs + "','" + zymz + "','" + mkgdfs + "','"
				+ aqjkxt + "','" + wscfxt + "','" + wsdj + "','" + mcdzrqxx
				+ "','" + mcbzwxx + "','" + cjdy + "','" + mcddbyx + "','"
				+ kjswdzlx + "','" + aqbh + "','" + aqzt + "','" + aqbzjg
				+ "','" + aqyxq + "','" + gszch + "','" + gsbzjg + "','"
				+ gsbzrq + "','" + gsyxq + "','" + ckzh + "','" + ckbzjg
				+ "','" + ckbzrq + "','" + ckyxq + "','" + mtzh + "','"
				+ mtbzjg + "','" + mtbzrq + "','" + mtyxq + "','" + kzbh
				+ "','" + kzbzjg + "','" + kzxm + "','" + kzyxq + "','"
				+ kzaqbh + "','" + kzaqbzjg + "','" + kzaqxm + "','" + kzaqyxq
				+ "')";
		db.execute(sql);
		System.out.print(id + " ");
	}
}
