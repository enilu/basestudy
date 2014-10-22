package com.gnst.coal.collector.jiaoyi;

import com.gnst.coal.collector.DBUtil;

/**
 * 采集交易中心数据：http://jy.cctd.com.cn/exlm/lmptGq.do<br>
 * 保存在test.jiaoyi表中
 * <p>
 * 2014年10月22日
 * 
 * @author enilu(82552623@qq.com)
 * 
 */
public class Jiaoyi {

	public static void main(String[] args) throws Exception {
		DBUtil db = new DBUtil();
		JiaoyiService ck = new JiaoyiService(db);
		for (int pageNum = 1; pageNum <= 6; pageNum++) {
			ck.getGq(-1, null, null, null, null, null, pageNum, 10);
		}
		db.close();
	}

}
