package com.jhg.marketing.dao.domin.menu;

import lombok.Data;

/**
 * 菜单匹配规则
 *
 * @author lxl
 * @since 2019/4/8 16:06
 */
@Data
public class Matchrule {

    /**
     * 用户标签的id
     */
    private String tag_id;

    /**
     * 性别：男（1）女（2）
     */
    private Integer sex;

    /**
     * 客户端版本，当前只具体到系统型号：IOS(1), Android(2),Others(3)
     */
    private String client_platform_type;

    private String country;
    private String province;
    private String city;

    private Integer language;

}
