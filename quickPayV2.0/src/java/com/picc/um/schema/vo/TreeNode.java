package com.picc.um.schema.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {
	
	private String id;
	
	private String text;
	
	private String state;
	
	private Map<String,Object>  attributes;
	
	private List<TreeNode> children;
	
	private String target;
	
	
	public TreeNode(){}
	
	public TreeNode(String id,String text){
		this.id = id;
		this.text = text;
	}
	
	public TreeNode(String id,String text,Map<String,Object> attributes){
		this.id = id;
		this.text = text;
		this.attributes = attributes;
	}
	
	public TreeNode(String id,String text,String state,Map<String,Object> attributes){
		this.id = id;
		this.text = text;
		this.state = state;
		this.attributes = attributes;
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

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	public void putAttributes(String key,Object value) {
		if(this.attributes==null){
			this.attributes = new HashMap<String,Object>();
		}
		this.attributes.put(key, value);
	}
	
	public void addChildNode(TreeNode menuNode) {
		if(this.children==null){
			this.children = new ArrayList<TreeNode>();
		}
		this.children.add(menuNode);
	}
}
