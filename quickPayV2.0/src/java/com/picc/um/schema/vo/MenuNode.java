package com.picc.um.schema.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuNode {
	
	private String id;										//菜单ID
	
	private String text;									//菜单显示字段
	
	private String target;
	
	private Map<String,Object> attributes;	//菜单属性信息
	
	private List<MenuNode> children;		//菜单子节点
	
	public MenuNode(){}
	
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
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<MenuNode> getChildren() {
		return children;
	}

	public void setChildren(List<MenuNode> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public void putAttributes(String key,Object value) {
		if(this.attributes==null){
			this.attributes = new HashMap<String,Object>();
		}
		this.attributes.put(key, value);
	}
	
	public void addChildNode(MenuNode menuNode) {
		if(this.children==null){
			this.children = new ArrayList<MenuNode>();
		}
		this.children.add(menuNode);
	}
	
}
