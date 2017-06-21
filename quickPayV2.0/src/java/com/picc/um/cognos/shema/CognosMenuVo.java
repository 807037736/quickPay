package com.picc.um.cognos.shema;
/**
 * Cognos菜单定义的实体VO类
 * @author shenyichan
 */

import com.cognos.developer.schemas.bibus._3.BaseClass;

public class CognosMenuVo {
	private BaseClass menu;
	private String upperMenuId;
	
	
	public BaseClass getMenu() {
		return menu;
	}
	public void setMenu(BaseClass menu) {
		this.menu = menu;
	}
	public String getUpperMenuId() {
		return upperMenuId;
	}
	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if(obj instanceof CognosMenuVo){
			CognosMenuVo cmv = (CognosMenuVo)obj;
			return cmv.getMenu().getStoreID().getValue().get_value().equals(this.getMenu().getStoreID().getValue().get_value());
		}else{
			return false;
		}
	}
}
