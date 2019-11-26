package com.jhg.marketing.common;

import java.util.ArrayList;
import java.util.List;

public class TreeList<T> extends ArrayList<T> {
	public TreeList(){}
	public TreeList(List<T>ls){
		super(ls);
	}
}
