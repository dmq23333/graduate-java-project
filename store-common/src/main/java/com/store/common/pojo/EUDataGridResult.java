package com.store.common.pojo;

import java.util.List;



public class EUDataGridResult {
	
	private long total;
	private List<?> rows;//任意类型泛型
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public EUDataGridResult(long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public EUDataGridResult() {
		super();
	}
	
	
	
}
