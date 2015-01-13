/**
 *Main.java
 *Version1.0
 *2015-1-11
 *Copyright cnendata.com
 *
 */
package com.gnst.coal.collector.shisuan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * <a href="http://www.12306.cn/mormhweb/hyfw/hyckcx/">煤炭试算</a>
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
		try {
			new Main().collector();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 查询参数： <br>
	 * fz:AQP<br>
	 * fzljm:00003<br>
	 * dz:UCK<br>
	 * dzljm:00006<br>
	 * cx:C80<br>
	 * zl:80<br>
	 * rq:20150113<br>
	 * bjfl:4<br>
	 * bjje:0<br>
	 * hwzl:0<br>
	 * pz:1<br>
	 * pm:2113010<br>
	 * xx:<br>
	 * zxbj:<br>
	 * zbbj:<br>
	 * csxs:1<br>
	 * fsmbj:0<br>
	 * fsmlc:0<br>
	 * dsmbj:0<br>
	 * dsmlc:0<br>
	 * fzyxbj:0<br>
	 * fzyxlc:0<br>
	 * dzyxbj:0<br>
	 * dzyxlc:0<br>
	 * fzyxdm:0<br>
	 * dzyxdm:<br>
	 * dfxcbj:0<br>
	 * fsybj:0<br>
	 * dsybj:0<br>
	 * fzcbj:1<br>
	 * dzcbj:1<br>
	 * fsmzxbj:0<br>
	 * dsmzxbj:0<br>
	 * </p>
	 * <p>
	 * 查询结果： <br>
	 * {
	 * 'YDQX':{'Name':'运到期限','Value':'3'},'YSZ':{'Name':'运算至','Value':''},'return':{'Code':'11','Message':'费用查询成功!'},'LCs':{'JLC':{'Name':'','Value':'417'},'DLC':{'Name':'','Value':'417'},'ZLC':{'Name':'','Value':'417'}},'FYs':{KM:[{'Name':'运费','Code':'005','Value':'420900'},{'Name':'发站装卸费','Code':'898','Value':'134400'},{'Name':'全程总计','Code':'998','Value':'829520'},{'Name':'京九分流运费','Code':'312','Value':'7390'},{'Name':'发站费用合计','Code':'999','Value':'829520'},{'Name':'印花税','Code':'009','Value':'230'},{'Name':'到站卸车费','Code':'899','Value':'116480'},{'Name':'铁路建设基金','Code':'020','Value':'110090'},{'Name':'电气化附加费','Code':'010','Value':'40030'
	 * } ] } , }
	 * <p>
	 * <p>
	 * 解析结果<br>
	 * 运到期限：3天 <br>
	 * 铁路里程：417公里<br>
	 * 运费：4209元 <br>
	 * 发站装卸费：1344元<br>
	 * 京九分流运费：73.9元<br>
	 * 印花税：2.3元<br>
	 * 到站卸车费：1164.8<br>
	 * 铁路建设基金：1100.9元 <br>
	 * 电气化附加费：400.3元 <br>
	 * 费用合计：8295.2元
	 * <p>
	 * 
	 * @throws Exception
	 */
	public void collector() throws Exception {
		Document doc = Jsoup.connect("http://dynamic.12306.cn/yjcx/doPthwjf")
				.data("fz", "TYV").data("fzljm", "00016").data("dz", "NJH")
				.data("cx", "C80").data("zl", "80").data("rq", "20150112")
				.data("bjfl", "4").data("bjje", "0").data("hwzl", "0")
				.data("pz", "1").data("pm", "0110006").data("xx", "")
				.data("zxbj", "").data("zbbj", "").data("csxs", "1")
				.data("fsmbj", "0").data("fsmlc", "0").data("dsmbj", "0")
				.data("dsmlc", "0").data("fzyxbj", "0").data("fzyxlc", "0")
				.data("dzyxbj", "0").data("dzyxlc", "0").data("fzyxdm", "0")
				.data("dzyxdm", "0").data("dfxcbj", "0").data("fsybj", "0")
				.data("dsybj", "0").data("fzcbj", "1").data("dzcbj", "1")
				.data("fsmzxbj", "0").data("dsmzxbj", "0").timeout(60000)
				.post();
		System.out.println(doc.text());
	}
}
