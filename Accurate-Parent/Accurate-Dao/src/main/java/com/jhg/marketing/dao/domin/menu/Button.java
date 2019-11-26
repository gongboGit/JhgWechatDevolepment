package com.jhg.marketing.dao.domin.menu;

import lombok.Data;

/**
 * @author lxl
 * @since 2019/4/8 13:10
 */
@Data
public class Button {

    /**
     * 菜单名
     */
    private String name;

    /**
     * 菜单的响应类型
     */
    private String type;

    private Button[] sub_button;

}
