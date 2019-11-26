package com.jhg.marketing.common;

import java.io.Console;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Data;

@Data
public class DataTable<T> {
	
	private List<DataColumn> columns;
	private List<DataRow> rows;
	private Class<?> modelType;
	private String primaryKey;
	
	public DataTable(Class<?> clzz){
		modelType=clzz;
		columns=DataColumnUtils.getColumnsByClzz(clzz);
		rows=new ArrayList<DataRow>();
	}
	public DataTable(List<T> ls,Class<?> clzz){
		this(clzz);
		
		for (T t : ls) {
			rows.add(new DataRow(t,columns));
		}
	}
	

	public String getPrimaryKey() {
		
		if(primaryKey!=null){
			Optional<DataColumn> pColumn=columns.stream().filter(it->it.isPrimaryKey()).findFirst();
			if(pColumn.isPresent()) primaryKey=pColumn.get().getColumnName();
		}
		return primaryKey;
	}
	
}
