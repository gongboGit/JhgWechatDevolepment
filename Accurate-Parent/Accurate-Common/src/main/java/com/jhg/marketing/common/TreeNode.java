package com.jhg.marketing.common;

import java.util.List;

import lombok.Data;
@Data
public class TreeNode<T> {
	private T node;
	private T parentNode;
	
	public T getParentNode() {
		return parentNode;
	}
	public void setParentNode(T parentNode) {
		this.parentNode = parentNode;
	}
	private TreeList<TreeNode<T>> children;
	public TreeNode(){}
	public TreeNode(T node,TreeList<TreeNode<T>> children){
		this.node=node;
		this.children=children;
	}
	
	
}
