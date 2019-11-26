package com.jhg.marketing.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class DataRow<T> {
	
	private List<DataCell> cells=new ArrayList<DataCell>();
	
	private Object model;
	public DataRow(Object model){
		this(model, DataColumnUtils.getColumnsByClzz(model.getClass()));
		
	}
	public DataRow(Object model,List<DataColumn> columns){
		this.model=model;
		
		List<Field> fields=DataColumnUtils.getAllFields(model.getClass());
		
		for (DataColumn column : columns) {
			try {
				Field field=fields.stream().filter(it->it.getName().equals(column.getColumnName())).findFirst().get();
				
						//model.getClass().getDeclaredField(column.getColumnName());
				field.setAccessible(true);
				Object value=field.get(model);
				DataCell cell=new DataCell(column, value, field.getType(), model);
				cells.add(cell);
			} catch (Exception e) {
				
			}
			
		}
		
	}
	/**
	 * �����ֶ�����ȡ��Ԫ��
	 * @param columnName �ֶ�����
	 * @return
	 */
	public DataCell get(String columnName) {
		Optional<DataCell>oCell= cells.stream().filter(it->it.getColumn().getColumnName().equals(columnName)).findFirst();
		return oCell.isPresent()?oCell.get():null;
	}
	
	public List<DataCell> getCells() {
		return cells;
	}

	
}
