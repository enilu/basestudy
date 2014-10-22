package com.gnst.coal.collector.jiaoyi;

/**
 * 交易详情
 * 
 * @author zhangtao
 * 
 */
public class ProductDetail {
	// 商品信息
	private String id;// ID
	private String productName;// 商品名称
	private String mz;// 煤种
	private String supplyCount;// 供应数量
	private String validityDate;// 有效期
	private String fbDate;// 发布日期

	// 商品详细信息
	private String sdjdwfrl;// 收到基低位发热量
	private String sxfd;// 上下浮动
	private String hffsdj;// 挥发份收到基
	private String qlsdj;// 全硫收到基
	private String hfsdj;// 灰分收到基
	private String qsf;// 全水分
	private String ns;// 内水
	private String njzs;// 粘结指数
	private String jzchd;// 角质层厚度

	// 交易信息
	private String sn;// 序号
	private String jiaohuodi;// 交货地
	private String jiagefangshi;// 价格方式
	private String jiage;// 价格
	private String jiaohuofangshi;// 交货方式
	private String jiaohuoqijian;// 交货期间

	// 其他交易信息
	private String zuididinghuoliang;// 最低订货量
	private String jingyanjigou;// 经验机构
	private String fukuanfangshi;// 付款方式
	private String jiesuanfangshi;// 结算方式

	private String descript;// 备注

	// 企业信息

	private String companyName;// 企业名称
	private String addr;// 所在地区
	private String contacts;// 联系人
	private String tel;// 电话
	private String phoneNum;// 手机
	private String fax;// 传真
	private String email;// 邮箱

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getSupplyCount() {
		return supplyCount;
	}

	public void setSupplyCount(String supplyCount) {
		this.supplyCount = supplyCount;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public String getFbDate() {
		return fbDate;
	}

	public void setFbDate(String fbDate) {
		this.fbDate = fbDate;
	}

	public String getSdjdwfrl() {
		return sdjdwfrl;
	}

	public void setSdjdwfrl(String sdjdwfrl) {
		this.sdjdwfrl = sdjdwfrl;
	}

	public String getSxfd() {
		return sxfd;
	}

	public void setSxfd(String sxfd) {
		this.sxfd = sxfd;
	}

	public String getHffsdj() {
		return hffsdj;
	}

	public void setHffsdj(String hffsdj) {
		this.hffsdj = hffsdj;
	}

	public String getQlsdj() {
		return qlsdj;
	}

	public void setQlsdj(String qlsdj) {
		this.qlsdj = qlsdj;
	}

	public String getHfsdj() {
		return hfsdj;
	}

	public void setHfsdj(String hfsdj) {
		this.hfsdj = hfsdj;
	}

	public String getQsf() {
		return qsf;
	}

	public void setQsf(String qsf) {
		this.qsf = qsf;
	}

	public String getNs() {
		return ns;
	}

	public void setNs(String ns) {
		this.ns = ns;
	}

	public String getNjzs() {
		return njzs;
	}

	public void setNjzs(String njzs) {
		this.njzs = njzs;
	}

	public String getJzchd() {
		return jzchd;
	}

	public void setJzchd(String jzchd) {
		this.jzchd = jzchd;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getJiaohuodi() {
		return jiaohuodi;
	}

	public void setJiaohuodi(String jiaohuodi) {
		this.jiaohuodi = jiaohuodi;
	}

	public String getJiagefangshi() {
		return jiagefangshi;
	}

	public void setJiagefangshi(String jiagefangshi) {
		this.jiagefangshi = jiagefangshi;
	}

	public String getJiage() {
		return jiage;
	}

	public void setJiage(String jiage) {
		this.jiage = jiage;
	}

	public String getJiaohuofangshi() {
		return jiaohuofangshi;
	}

	public void setJiaohuofangshi(String jiaohuofangshi) {
		this.jiaohuofangshi = jiaohuofangshi;
	}

	public String getJiaohuoqijian() {
		return jiaohuoqijian;
	}

	public void setJiaohuoqijian(String jiaohuoqijian) {
		this.jiaohuoqijian = jiaohuoqijian;
	}

	public String getZuididinghuoliang() {
		return zuididinghuoliang;
	}

	public void setZuididinghuoliang(String zuididinghuoliang) {
		this.zuididinghuoliang = zuididinghuoliang;
	}

	public String getJingyanjigou() {
		return jingyanjigou;
	}

	public void setJingyanjigou(String jingyanjigou) {
		this.jingyanjigou = jingyanjigou;
	}

	public String getFukuanfangshi() {
		return fukuanfangshi;
	}

	public void setFukuanfangshi(String fukuanfangshi) {
		this.fukuanfangshi = fukuanfangshi;
	}

	public String getJiesuanfangshi() {
		return jiesuanfangshi;
	}

	public void setJiesuanfangshi(String jiesuanfangshi) {
		this.jiesuanfangshi = jiesuanfangshi;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}