package com.jhg.marketing.web.admin.service;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static Message success(String msg, Object data) {
        return new Message() {
            {
                setCode(1);
                setMsg(msg);
                setData(data);
            }
        };
    }

    public static Message success(String msg) {
        return success(msg, null);
    }

    public static Message fail(String msg, Object data) {
        return new Message() {
            {
                setCode(0);
                setMsg(msg);
                setData(data);
            }
        };
    }

    public static Message fail(String msg) {
        return fail(msg, null);
    }
}
