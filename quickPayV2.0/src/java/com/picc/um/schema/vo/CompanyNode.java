package com.picc.um.schema.vo;

public class CompanyNode {
	
	private String id;
	
	private String text;
	
	private String state = "closed";
	
	public CompanyNode(){}
	
	public CompanyNode(String id,String text){
		this.id = id;
		this.text = text;
	}
	
	public CompanyNode(String id,String text,String state){
		this.id = id;
		this.text = text;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
