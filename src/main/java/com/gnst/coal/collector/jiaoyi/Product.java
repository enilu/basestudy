package com.gnst.coal.collector.jiaoyi;

/**
 * 交易信息
 * 
 * @author zhangtao
 * 
 */
public class Product {
	private String id;
	private String leixing;
	private String companyName;
	private String mz;
	private String frl;
	private Float shuliang;
	private String jiaohuodi;
	private String jiaohuofangshi;
	private String jiage;
	private String zuidicaigouliang;
	private String fbdate;
	private String youxiaoqi;

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getFrl() {
		return frl;
	}

	public void setFrl(String frl) {
		this.frl = frl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Float getShuliang() {
		return shuliang;
	}

	public void setShuliang(Float shuliang) {
		this.shuliang = shuliang;
	}

	public String getJiaohuodi() {
		return jiaohuodi;
	}

	public void setJiaohuodi(String jiaohuodi) {
		this.jiaohuodi = jiaohuodi;
	}

	public String getJiaohuofangshi() {
		return jiaohuofangshi;
	}

	public void setJiaohuofangshi(String jiaohuofangshi) {
		this.jiaohuofangshi = jiaohuofangshi;
	}

	public String getJiage() {
		return jiage;
	}

	public void setJiage(String jiage) {
		this.jiage = jiage;
	}

	public String getZuidicaigouliang() {
		return zuidicaigouliang;
	}

	public void setZuidicaigouliang(String zuidicaigouliang) {
		this.zuidicaigouliang = zuidicaigouliang;
	}

	public String getFbdate() {
		return fbdate;
	}

	public void setFbdate(String fbdate) {
		this.fbdate = fbdate;
	}

	public String getYouxiaoqi() {
		return youxiaoqi;
	}

	public void setYouxiaoqi(String youxiaoqi) {
		this.youxiaoqi = youxiaoqi;
	}

	@Override
	public String toString() {

		return "id:" + id + " 类型:" + leixing + " 公司名称：" + companyName + " 煤种："
				+ mz + " 发热量：" + frl + " 数量：" + shuliang + " 交货地：" + jiaohuodi
				+ " 交货方式：" + jiaohuofangshi + " 价格：" + jiage + " 最低采购量："
				+ zuidicaigouliang + " 发布日期：" + fbdate + " 有效期：" + youxiaoqi;
	}

}