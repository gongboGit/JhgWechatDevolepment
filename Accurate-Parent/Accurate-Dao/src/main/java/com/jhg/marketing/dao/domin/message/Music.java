package com.jhg.marketing.dao.domin.message;

import lombok.Data;

/**
 * @author lxl
 * @since 2019/4/9 10:41
 */
@Data
public class Music {

    private String Title;

    private String Description;

    private String MusicUrl;

    /**
     * 高质量音乐链接
     */
    private String HQMusicUrl;

    private String ThumbMediaId;

}
