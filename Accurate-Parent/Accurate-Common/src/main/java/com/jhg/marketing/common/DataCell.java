package com.jhg.marketing.common;

import lombok.Data;

@Data
public class DataCell {
	private DataColumn column;
	private Object value;
	private Class valueType;
	private Object model;
	
	public DataCell(){}
	
	public DataCell(DataColumn column, Object value, Class valueType, Object model) {
		super();
		this.column = column;
		this.value = value;
		this.valueType = valueType;
		this.model = model;
		
	}

	
	public String toString() {
		return value==null?"": value.toString();
	}
}
