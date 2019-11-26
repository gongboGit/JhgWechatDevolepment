package com.jhg.marketing.web.admin.service;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MenuTreeUtil {
    /**
     * 过滤菜单位项
     *
     * @param ls
     * @param predicate
     * @return
     */
    public static MenuTreeList filter(MenuTreeList ls, Predicate<MenuNode> predicate) {
        MenuTreeList retLs = new MenuTreeList();
        for (MenuNode menuNode : ls.stream().filter(predicate).collect(Collectors.toList())) {
            MenuNode node = new MenuNode(menuNode.getNode());
            node.setChildren(filter(menuNode.getChildren(), predicate));
            retLs.add(node);
        }
        return retLs;
    }
    /**
     * 转换成 menuitem ls
     * @param treeList
     * @return
     */
//	public static<T> List<T> toList(MenuTreeList treeList) {
//		List<MenuNode<T>> ls=new ArrayList<MenuItem>();
//		ls.addAll(treeList.stream().map(it->it.getNode()).collect(Collectors.toList()));
//		treeList.forEach(it->{
//			ls.addAll(toList(it.getChildren()));
//		});
//		return ls;
//	}
}
