package com.picc.cxf.schema.model;

import java.util.List;

public class CaseAllData {
	private CaseInfo caseInfo;
	private List<PersonInfo> personInfos;
	public CaseInfo getCaseInfo() {
		return caseInfo;
	}
	public void setCaseInfo(CaseInfo caseInfo) {
		this.caseInfo = caseInfo;
	}
	public List<PersonInfo> getPersonInfos() {
		return personInfos;
	}
	public void setPersonInfos(List<PersonInfo> personInfos) {
		this.personInfos = personInfos;
	}
	

}
