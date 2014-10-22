package com.gnst.coal.collector.jiaoyi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author burns
 * 
 */
public class Cankao {

	/**
	 * 解析东北亚煤炭交易信息
	 * 
	 * @param gqmarked
	 *            供求标识，求购：1，供给：0，不限：-1
	 * @param dl
	 *            煤种名称：『动力煤 无烟煤 炼焦煤 褐煤』
	 * @param czbegin
	 *            发热量范围起始值
	 * @param czend
	 *            发热量范围终止值
	 * @param sm
	 *            省份
	 * @param qhadd
	 *            地级市 或者港口名称
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页显示记录数 默认为10条，前台传过来的
	 * @throws IOException
	 */
	public Page getGq(Integer gqmarked, String dl, Integer czbegin,
			Integer czend, String sm, String qhadd, Integer pageNum,
			Integer pageSize) throws IOException {
		Page page = new Page();
		page.setPageNum(pageNum);
		Document doc = Jsoup
				.connect("http://jy.cctd.com.cn/exlm/lmptGq.do")
				.data("gqmarked", String.valueOf(gqmarked))
				.data("dl", dl == null ? "" : dl)
				.data("czbegin", czbegin == null ? "" : String.valueOf(czbegin))
				.data("czend", czend == null ? "" : String.valueOf(czend))
				.data("sm", sm == null ? "" : sm)
				.data("qhadd", qhadd == null ? "" : qhadd)
				.data("baosteel_page_no", String.valueOf(pageNum))
				.data("baosteel_page_no", "10").timeout(60000).post();
		String[] fanye = doc.getElementsByClass("fanye").get(0).html()
				.replaceAll("&nbsp;", "").split("：");
		page.setTotalNum(Integer.valueOf(fanye[1].substring(0,
				fanye[1].indexOf("条")).trim()));
		page.setTotalPages(Integer.valueOf(fanye[2].substring(0,
				fanye[2].indexOf("页")).trim()));
		Element tbody = doc.getElementsByTag("tbody").get(0);
		Elements trs = tbody.getElementsByTag("tr");
		List<Product> list = new ArrayList<Product>();
		for (Element tr : trs) {
			Elements tds = tr.children();
			Product rec = new Product();
			rec.setLeixing(tds.get(0).html());
			rec.setCompanyName(tds.get(1).attr("title"));
			rec.setMz(tds.get(2).html());
			rec.setFrl(tds.get(3).html());
			rec.setShuliang(Float.valueOf(tds.get(4).html().trim()));
			rec.setJiaohuodi(tds.get(5).attr("title"));
			rec.setJiaohuofangshi(tds.get(6).html());
			rec.setJiage(tds.get(7).html());
			rec.setZuidicaigouliang(tds.get(8).html());
			rec.setFbdate(tds.get(9).html());
			rec.setYouxiaoqi(tds.get(10).html());
			String[] urls = tds.get(11).getElementsByTag("a").get(0)
					.attr("href").split("'");

			rec.setId(urls[1]);
			list.add(rec);
		}
		page.setDatas(list);
		return page;
	}

	/**
	 * 根据商品交易id查询商品交易详情
	 * 
	 * @param id
	 *            商品交易id
	 * @return
	 * @throws Exception
	 */
	public ProductDetail findById(String id) throws Exception {
		Document doc = Jsoup
				.connect(
						"http://jy.cctd.com.cn/exlm/l-0-b/lmptSpInfo.do?method=docontent&gpls="
								+ id).timeout(60000).get();
		ProductDetail detail = new ProductDetail();
		detail.setId(id);
		Elements tableList = doc.getElementsByClass("dian_right").get(0)
				.children();
		// 解析商品信息
		Element product = tableList.get(0).getElementsByTag("tbody").get(0)
				.getElementsByTag("tr").get(1);
		Elements products = product.children();
		detail.setProductName(products.get(0).html().replace("&nbsp;", "")
				.trim());
		detail.setMz(products.get(1).html().replace("&nbsp;", "").trim());
		detail.setSupplyCount(products.get(2).html().replace("&nbsp;", "")
				.trim());
		detail.setValidityDate(products.get(3).html().replace("&nbsp;", "")
				.trim().length() > 10 ? products.get(3).html()
				.replace("&nbsp;", "").trim().substring(0, 10) : "");
		detail.setFbDate(products.get(4).html().replace("&nbsp;", "").trim()
				.length() > 10 ? products.get(4).html().replace("&nbsp;", "")
				.trim().substring(0, 10) : "");

		// 解析详细信息
		Elements productDetails = tableList.get(1)
				.getElementsByClass("GD_EC_htxx_Div").get(0)
				.getElementsByTag("li");
		detail.setSdjdwfrl(productDetails.size() > 0 ? (productDetails.get(0)
				.html().split("：").length > 1 ? productDetails.get(0).html()
				.split("：")[1].trim() : "") : "");
		detail.setSxfd(productDetails.size() > 1 ? (productDetails.get(1)
				.html().split("：").length > 1 ? productDetails.get(1).html()
				.split("：")[1].trim() : "") : "");
		detail.setHffsdj(productDetails.size() > 2 ? (productDetails.get(2)
				.html().split("：").length > 1 ? productDetails.get(2).html()
				.split("：")[1].trim() : "") : "");
		detail.setQlsdj(productDetails.size() > 3 ? (productDetails.get(3)
				.html().split("：").length > 1 ? productDetails.get(3).html()
				.split("：")[1].trim() : "") : "");
		detail.setHfsdj(productDetails.size() > 4 ? (productDetails.get(4)
				.html().split("：").length > 1 ? productDetails.get(4).html()
				.split("：")[1].trim() : "") : "");
		detail.setQsf(productDetails.size() > 5 ? (productDetails.get(5).html()
				.split("：").length > 1 ? productDetails.get(5).html()
				.split("：")[1].trim() : "") : "");
		detail.setNs(productDetails.size() > 6 ? (productDetails.get(6).html()
				.split("：").length > 1 ? productDetails.get(6).html()
				.split("：")[1].trim() : "") : "");
		detail.setNjzs(productDetails.size() > 7 ? (productDetails.get(7)
				.html().split("：").length > 1 ? productDetails.get(7).html()
				.split("：")[1].trim() : "") : "");
		detail.setJzchd(productDetails.size() > 8 ? (productDetails.get(8)
				.html().split("：").length > 1 ? productDetails.get(8).html()
				.split("：")[1].trim() : "") : "");

		// 解析交易信息
		Elements jiaoyis = tableList.get(2).getElementsByTag("tbody").get(0)
				.getElementsByTag("tr").get(1).getElementsByTag("td");

		detail.setSn(jiaoyis.get(0).html());
		detail.setJiaohuodi(jiaoyis.get(1).html());
		detail.setJiagefangshi(jiaoyis.get(2).html());
		detail.setJiage(jiaoyis.get(3).html());
		detail.setJiaohuofangshi(jiaoyis.get(4).html());
		detail.setJiaohuoqijian(jiaoyis.get(5).html().replaceAll("&nbsp;", ""));

		// 解析其他交易信息
		Elements qitaJiaoyis = tableList.get(3).getElementsByTag("tbody")
				.get(0).getElementsByTag("td");
		detail.setZuididinghuoliang(qitaJiaoyis.get(1).html()
				.replace("&nbsp;", ""));
		detail.setJingyanjigou(qitaJiaoyis.get(3).html().replace("&nbsp;", ""));
		detail.setFukuanfangshi(qitaJiaoyis.get(5).html().replace("&nbsp;", ""));
		detail.setJiesuanfangshi(qitaJiaoyis.get(7).html()
				.replace("&nbsp;", ""));
		detail.setDescript(qitaJiaoyis.get(9).html().replace("&nbsp;", ""));

		// 解析企业介绍信息
		Element company = doc.getElementsByClass("xqlf").get(0);
		Elements companys = company.getElementsByClass("qyname");
		detail.setCompanyName(companys.get(0).getElementsByTag("b").get(0)
				.attr("title"));
		detail.setAddr(companys.get(1).getElementsByTag("b").get(0).html());
		Elements contactsLi = companys.get(2).getElementsByTag("li");
		detail.setContacts(contactsLi.get(0).getElementsByTag("b").get(0)
				.html());
		detail.setTel(contactsLi.get(1).getElementsByTag("b").get(0).html());
		detail.setPhoneNum(contactsLi.get(2).getElementsByTag("b").get(0)
				.html());
		detail.setFax(contactsLi.get(3).getElementsByTag("b").get(0).html());
		detail.setEmail(contactsLi.get(4).getElementsByTag("b").get(0).html());
		return detail;
	}
}
