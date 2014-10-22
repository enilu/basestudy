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
	private static DBUtil dbUtil;

	public static void main(String[] args) throws Exception {
		dbUtil = new DBUtil();

	}

}
