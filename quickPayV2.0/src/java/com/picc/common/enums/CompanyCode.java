package com.picc.common.enums;

/**
 * 服务错误代码定义
 * 
 * @author qishangzy
 */
public class CompanyCode   {
	/**
	 * 保险公司代码
	 * @author CKY
	 */
	public enum Company {
		COMPANY_CPIC("1", "CPIC", "太保财险"),
		COMPANY_PAIC("3", "PAIC", "平安财险"),
		COMPANY_ABIC("4", "ABIC", "安邦财险"),
		COMPANY_TPIC("5", "TPIC", "太平财险"),
		COMPANY_PICC("6", "PICC", "人保财险"),
		COMPANY_TAIC("7", "TAIC", "天安保险"),
		COMPANY_AICS("8", "AICS", "永诚保险"),
		COMPANY_CCIC("9", "CCIC", "大地保险"),
		COMPANY_HAIC("10", "HAIC", "华安保险"),
		COMPANY_CAIC("11", "CAIC", "长安责任"),
		COMPANY_MACN("13", "MACN", "亚太财险"),
		COMPANY_BPIC("14", "BPIC", "渤海保险"),
		COMPANY_BOCI("15", "BOCI", "中银保险"),
		COMPANY_DBIC("16", "DBIC", "都邦保险"),
		COMPANY_HTIC("17", "HTIC", "华泰财险"),
		COMPANY_YDCX("18", "YDCX", "英大财险"),
		COMPANY_ZKIC("22", "ZKIC", "紫金保险"),
		COMPANY_YGBX("23", "YGBX", "阳光财险"),
		COMPANY_GPIC("24", "GPIC", "国寿财险"),
		COMPANY_CICP("25", "CICP", "中华保险"),
		COMPANY_XDCX("28", "XDCX", "信达财险"),
		COMPANY_UTIC("33", "UTIC", "众诚保险"),
		
//		COMPANY_26("26", "", "无保险"),
//		COMPANY_27("27", "", "浙商财险"),
//		COMPANY_29("29", "", "天平车险"),
//		COMPANY_30("30", "", "大众保险"),
//		COMPANY_31("31", "", "永安保险"),
//		COMPANY_32("32", "", "鼎和保险"),
//		COMPANY_OTHER("ICCO0000000000000015", "ICCO0000000000000015", "其他"),
		;
		
		private String coId;
		private String companyCode;
		private String companyName;
		
		public String getCoId() {
			return coId;
		}
		
		public static String getCompanyCode(String coId) {
			for( Company company : Company.values()){
				if(coId.equals(company.coId)){
					return company.companyCode;
				}
			}
			return null;
		}
		
		public static String getCompanyName(String coId) {
			for( Company company : Company.values()){
				if(coId.equals(company.coId)){
					return company.companyName;
				}
			}
			return null;
		}
		
		private Company(String coId, String companyCode, String companyName){
			this.coId = coId;
			this.companyCode = companyCode;
			this.companyName = companyName;
		}
	}
}
