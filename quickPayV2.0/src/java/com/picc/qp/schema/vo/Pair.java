package com.picc.qp.schema.vo;

/**
 * 数据包裹器 泛型定义包裹
 * 
 * @author obba
 * 
 */
public class Pair<T1, T2> {

	private T1 first;
	private T2 second;

	public T1 getFirst() {
		return first;
	}

	public void setFirst(T1 first) {
		this.first = first;
	}

	public T2 getSecond() {
		return second;
	}

	public void setSecond(T2 second) {
		this.second = second;
	}

}
