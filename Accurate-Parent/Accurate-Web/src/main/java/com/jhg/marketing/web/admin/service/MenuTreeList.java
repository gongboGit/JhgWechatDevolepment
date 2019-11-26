package com.jhg.marketing.web.admin.service;

import java.util.ArrayList;
import java.util.List;

/**
 * �˵�tree list
 *
 * @author Administrator
 */
public class MenuTreeList extends ArrayList<MenuNode> {
    public MenuTreeList() {
        super();
    }

    public MenuTreeList(List<MenuNode> ls) {
        super(ls);
    }
}
