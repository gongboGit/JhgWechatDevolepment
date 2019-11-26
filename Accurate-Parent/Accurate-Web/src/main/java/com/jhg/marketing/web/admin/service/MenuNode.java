package com.jhg.marketing.web.admin.service;


public class MenuNode<T> {
    private T Node;

    public MenuNode(T node) {
        this(node, null);
    }

    public MenuNode(T node, MenuTreeList children) {
        super();
        Node = node;
        this.children = children;
    }

    public T getNode() {
        return Node;
    }

    public void setNode(T node) {
        Node = node;
    }

    public MenuTreeList getChildren() {
        return children;
    }

    public void setChildren(MenuTreeList children) {
        this.children = children;
    }

    private MenuTreeList children;
}
