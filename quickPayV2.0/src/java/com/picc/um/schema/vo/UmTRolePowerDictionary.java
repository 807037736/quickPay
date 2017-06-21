package com.picc.um.schema.vo;

import com.picc.um.schema.model.UmTRolePower;

@SuppressWarnings("serial")
public class UmTRolePowerDictionary extends UmTRolePower {

	private String dictionaryName;

	public void setDictionaryName(String dictionaryName) {
		this.dictionaryName = dictionaryName;
	}

	public String getDictionaryName() {
		return dictionaryName;
	}

	public UmTRolePowerDictionary() {
		// TODO Auto-generated constructor stub
	}

	public UmTRolePowerDictionary(UmTRolePower rolePower) {
		super.setId(rolePower.getId());
		super.setDictionaryId(rolePower.getDictionaryId());
		super.setPowerValue(rolePower.getPowerValue());
	}

}
