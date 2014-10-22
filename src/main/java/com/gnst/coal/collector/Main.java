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
 * ��Ϣ�ɼ�����
 * <p>
 * 2014��9��23��
 * 
 * @author enilu(82552623@qq.com)
 * 
 */
public class Main {
	String sessionId = "ASP.NET_SessionId";
	String cookie = "";
	String baseurl = "http://mkxkz.chinasafety.gov.cn/";
	String successKey = "ú��ȫ�������֤�ۺϹ������ݿ�";
	static DBUtil db = null;

	public static void main(String[] args) throws Exception {
		Main main = new Main();
		db = new DBUtil();
		if (main.login()) {
			main.collector();
			// main.checkCollector();
			// main.collectorDetail("150206272008��������������������");

		}
		db.close();

	}

	/**
	 * ��¼ϵͳ
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
		if (!"��¼".equals(msg)) {
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
	 * �ɼ�ȫ��ú�����ݣ���12073
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
	 * ������ݿ��зǷ������ݣ����²ɼ�����֮
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
	 * ����id�ɼ�ú�������Ϣ
	 * 
	 * @param id
	 * @throws Exception
	 */
	private void collectorDetail(String id) throws Exception {
		String url = baseurl + "infoQuery/MineInfo.aspx?mineid=" + id;
		Document doc = Jsoup.connect(url).cookie(sessionId, cookie)
				.timeout(100000).get();
		String mc = doc.getElementById("td_MineName").text();// ú������';
		String zuobiao = doc.getElementById("td_MineXYZ").text();// ����������';
		String fuzerenl = doc.getElementById("td_MineFrdb").text();// ��Ҫ������';
		String leibie = doc.getElementById("td_MineJjlx").text();// ú�����';
		String dianhua = doc.getElementById("td_MineTel").text();// ��ϵ�绰';
		String youbian = doc.getElementById("td_MinePostalCode").text();// ��������';
		String dizhi = doc.getElementById("td_MineAddress").text();// ��ϸ��ַ';

		String zhuangtai = doc.getElementById("td_MineState").text();// ú��״̬';
		String mksjscnl = doc.getElementById("td_MineSj").text();// ú�������������';
		String mkhdscnl = doc.getElementById("td_MineHd").text();// ú��˶���������';
		String mksykccl = doc.getElementById("td_MineRemain").text();// ú��ʣ��ɲɴ���';
		String mktcsj = doc.getElementById("td_MinePTime").text();// ú��Ͷ��ʱ��';
		String ktfs = doc.getElementById("td_MineOpening").text();// ���ط�ʽ';
		String kcmc = doc.getElementById("td_MineCoalSeam").text();// ����ú��';
		String cmgy = doc.getElementById("td_MiningTech").text();// ��ú����';
		String ysfs = doc.getElementById("td_MineTraffic").text();// ���䷽ʽ';
		String jxdyrs = doc.getElementById("td_MineWorker").text();// ���¶�Ա����';
		String zymz = doc.getElementById("td_MineCoalGrade").text();// ��Ҫú��';
		String mkgdfs = doc.getElementById("td_MineGdMethod").text();// ú�󹩵緽ʽ';

		String aqjkxt = doc.getElementById("td_GasJIANKONG").text();// ��ȫ���ϵͳ';
		String wscfxt = doc.getElementById("td_GasCHOUFANG").text();// ��˹���ϵͳ';
		String wsdj = doc.getElementById("td_GasGrade").text();// ��˹�ȼ�';
		String mcdzrqxx = doc.getElementById("td_CoalFire").text();// ú�����ȼ������';
		String mcbzwxx = doc.getElementById("td_CoalBlast").text();// ú��ı�ըΣ����';
		String cjdy = doc.getElementById("td_MineCjdy").text();// �����ѹ';
		String mcddbyx = doc.getElementById("td_Mineddb").text();// ú�㶥�װ�����';
		String kjswdzlx = doc.getElementById("td_HydroGeoType").text();// ��ˮ�ĵ�������';

		String aqbh = doc.getElementById("td_SafeLicenseCode").text();// ��ȫ�������֤-���';
		String aqzt = doc.getElementById("td_SafeLicenseState").text();// ��ȫ�������֤-״̬';
		String aqbzjg = doc.getElementById("td_SafeLicenseOrg").text();// ��ȫ�������֤-��֤����';
		String aqyxq = doc.getElementById("td_SafeLicenseTime").text();// ��ȫ�������֤-��Ч��';start-end
																		// start���ǰ�֤����

		String gszch = doc.getElementById("td_gcCode").text();// ����Ӫҵִ��-ע���';
		String gsbzjg = doc.getElementById("td_gcOrg").text();// ����Ӫҵִ��-��֤����';
		String gsbzrq = "";// doc.getElementById("td_gcEndTime").text();//
							// ����Ӫҵִ��-��֤����';
		String gsyxq = doc.getElementById("td_gcEndTime").text();// ����Ӫҵִ��-��Ч��';

		String ckzh = doc.getElementById("td_ckCode").text();// �ɿ����֤-֤��';
		String ckbzjg = doc.getElementById("td_ckOrg").text();// �ɿ����֤-��֤����';
		String ckbzrq = "";// doc.getElementById("aaaaaa").text();//
							// �ɿ����֤-��֤����';
		String ckyxq = doc.getElementById("td_ckEndTime").text();// �ɿ����֤-��Ч��';

		String mtzh = doc.getElementById("td_coalCode").text();// ú̿�������֤-֤��';
		String mtbzjg = doc.getElementById("td_coalOrg").text();// ú̿�������֤-��֤����';
		String mtbzrq = "";// doc.getElementById("aaaaaa").text();//
							// ú̿�������֤-��֤����';
		String mtyxq = doc.getElementById("td_coalEndTime").text();// ú̿�������֤-��Ч��';

		String kzbh = doc.getElementById("kzzgID").text();// ���ʸ�֤-���';
		String kzbzjg = doc.getElementById("kzzgOrg").text();// ���ʸ�֤-��֤����';
		String kzxm = doc.getElementById("kzzgName").text();// ���ʸ�֤-����';
		String kzyxq = doc.getElementById("kzzgEndTime").text();// ���ʸ�֤-��Ч��';

		String kzaqbh = doc.getElementById("td_kzCode").text();// �󳤰�ȫ�ʸ�֤-���';
		String kzaqbzjg = doc.getElementById("td_kzOrg").text();// �󳤰�ȫ�ʸ�֤-��֤����';
		String kzaqxm = doc.getElementById("td_kzName").text();// �󳤰�ȫ�ʸ�֤-����';
		String kzaqyxq = doc.getElementById("td_kzEndTime").text();// �󳤰�ȫ�ʸ�֤-��Ч��';
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
