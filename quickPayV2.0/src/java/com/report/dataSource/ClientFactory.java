package com.report.dataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.report.bean.ClientBean;
import com.report.bean.ClientReport;
public class ClientFactory {   
	public static Collection<ClientReport> getClientReports(){  
		Collection<ClientReport> testReports = new Vector<ClientReport>();  
		List<ClientBean> clinetBeans = new ArrayList<ClientBean>();  
		ClientBean tb1 = new ClientBean();  
		tb1.setName("  A:刘伟彬，男，年龄34岁，身份证号：430321198201143572，准驾车型：C1，电话：15073283377，现住址：湘潭县，交通方式：驾驶湘A600NM的小型汽车人，保险情况：大地");  
		clinetBeans.add(tb1);  
		ClientBean tb2 = new ClientBean();  
		tb2.setName("  B:刘伟彬，男，年龄34岁，身份证号：430321198201143572，准驾车型：C1，电话：15073283377，现住址：湘潭县，交通方式：驾驶湘A600NM的小型汽车人，保险情况：大地");  
		clinetBeans.add(tb2);  
		JRBeanCollectionDataSource accClientList = new JRBeanCollectionDataSource(clinetBeans);       
		ClientReport tr = new ClientReport();  
		tr.setAccClientList(accClientList);  
		testReports.add(tr);         
		return testReports;  
	}  
}  