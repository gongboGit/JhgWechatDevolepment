package com.jhg.marketing.common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.jhg.marketing.delegate.FuncTwo;


public class TreeUtil {

    //	/**
//	 * 父子节点
//	 * @param list 要生成的列表
//	 * @param pkFunc 获取主键
//	 * @param fkFun  获取外键
//	 * @return
//	 */
//	public static<T> TreeList<TreeNode<T>> generTreeByParent(T parentNode,
//			List<? extends T>list,
//			FuncTow<T, String> pkFunc,
//			FuncTow<T, String> fkFun){
//		
//		//String parentId=null;
//		String id=null;
//		//1.获取主键
//		if(parentNode!=null&&pkFunc.accept(parentNode)!=null) id=pkFunc.accept(parentNode);
//		
//		if(StringUtil.isEmpty(id)) id="";
//		String pkId=id;
//		
//		TreeList<TreeNode<T>> treeList=new TreeList<TreeNode<T>>();
//		
//		
//		Predicate<T> filter=(it)->pkId.equals(fkFun.accept(it));
//		
//		if(pkId.length()==0) filter=(it)->StringUtil.isEmpty(fkFun.accept(it));
//		
//		List<T> ls=list.stream().filter(filter).collect(Collectors.toList());
//		for (T node : ls) {
//			TreeNode<T> treeNode=new TreeNode<T>();
//			treeNode.setNode(node);
//			treeNode.setParentNode(parentNode);
//			treeNode.setChildren(generTreeByParent(node, list,pkFunc,fkFun));
//			treeList.add(treeNode);
//		}
//		return treeList;
//	}
    public static <T> TreeList<TreeNode<T>> generTreeList(T parentNode,
                                                          List<? extends T> list,
                                                          FuncTwo<T, String> thunk,
                                                          int step) {

        return generTreeListV2(parentNode, list, thunk, new ArrayList<Integer>() {
            {
                add(step);
            }
        }, 0);
    }

    /**
     * 没能生成的数节点添加进去
     *
     * @param parentNode
     * @param list
     * @param thunk
     * @param steps
     * @param start
     * @return
     */
    public static <T> TreeList<TreeNode<T>> generTreeFullList(T parentNode,
                                                              List<? extends T> list,
                                                              FuncTwo<T, String> thunk,
                                                              List<Integer> steps,
                                                              int start) {

        TreeList<TreeNode<T>> treeList = generTreeListV2(parentNode, list, thunk, steps, start);
        List<String> subKeys = getSubOfList(treeList, thunk);
        int indx = 0;
        for (T it : list) {
            String key = thunk.accept(it);
            if (!subKeys.contains(key)) {
                TreeNode<T> treeNode = new TreeNode<T>();
                treeNode.setNode(it);
                treeList.add(indx++, treeNode);
            }
        }
        return treeList;
    }

    /**
     * 获取tree里面的的部分属性
     *
     * @param treeList
     * @param thunk
     * @return
     */
    public static <T> List<String> getSubOfList(TreeList<TreeNode<T>> treeList, FuncTwo<T, String> thunk) {
        List<String> ls = new ArrayList<String>();
        for (TreeNode<T> node : treeList) {
            ls.add(thunk.accept(node.getNode()));
            ls.addAll(getSubOfList(node.getChildren(), thunk));
        }
        return ls;
    }

    /**
     * 生成树开列表
     *
     * @param parentNode
     * @param list
     * @param thunk
     * @param steps
     * @param start
     * @return
     */
    public static <T> TreeList<TreeNode<T>> generTreeListV2(T parentNode,
                                                            List<? extends T> list,
                                                            FuncTwo<T, String> thunk,
                                                            List<Integer> steps,
                                                            int start) {

        TreeList<TreeNode<T>> treeList = new TreeList<TreeNode<T>>();
        String lev = parentNode == null ? "" : thunk.accept(parentNode);

        int step = 0;
        if (steps.size() <= start) {
            step = steps.get(steps.size() - 1);
        } else {
            step = steps.get(start);
        }

        int takeSize = lev.length() + step;
        //过滤条件
        Predicate<T> filter = (it) -> thunk.accept(it).length() == takeSize && thunk.accept(it).startsWith(lev);
        if (lev.length() == 0) {
            filter = it -> thunk.accept(it).length() == takeSize;
        }

        List<T> ls = list.stream().filter(filter).collect(Collectors.toList());
        //排序
        ls.sort((a, b) -> thunk.accept(a).compareTo(thunk.accept(b)));

        for (T node : ls) {
            TreeNode<T> treeNode = new TreeNode<T>();
            treeNode.setNode(node);
            treeNode.setParentNode(parentNode);
            treeNode.setChildren(generTreeListV2(node, list, thunk, steps, ++step));
            treeList.add(treeNode);
        }

        return treeList;
    }
}
