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
		// fz:TYV
		// fzljm:00016
		// dz:NJH
		// dzljm:00007
		// cx:C80
		// zl:80
		// rq:20150112
		// bjfl:4
		// bjje:0
		// hwzl:0
		// pz:1
		// pm:0110006
		// xx:
		// zxbj:
		// zbbj:
		// csxs:1
		// fsmbj:0
		// fsmlc:0
		// dsmbj:0
		// dsmlc:0
		// fzyxbj:0 ddd
		// fzyxlc:0
		// dzyxbj:0
		// dzyxlc:0
		// fzyxdm:0
		// dzyxdm:
		// dfxcbj:0
		// fsybj:0
		// dsybj:0
		// fzcbj:1
		// dzcbj:1
		// fsmzxbj:0
		// dsmzxbj:0

	}
}
