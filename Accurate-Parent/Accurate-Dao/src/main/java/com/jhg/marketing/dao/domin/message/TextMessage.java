package com.jhg.marketing.dao.domin.message;

import lombok.Data;

/**
 * 消息内容实体
 *
 * @author lxl
 */
@Data
public class TextMessage extends BaseMessage {

    /**
     * 文本消息内容
     */
    private String Content;

}