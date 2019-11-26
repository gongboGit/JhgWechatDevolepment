package com.jhg.marketing.dao.domin.message;

import lombok.Data;

/**
 * @author lxl
 * @since 2019/4/9 10:50
 */
@Data
public class ArticlesMessage extends BaseMessage {

    private Integer ArticleCount;

    private Articles Articles;
}
