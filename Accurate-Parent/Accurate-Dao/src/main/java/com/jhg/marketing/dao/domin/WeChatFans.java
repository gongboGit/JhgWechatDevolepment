package com.jhg.marketing.dao.domin;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Data
@Table(name="wechat_fans")
public class WeChatFans {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "open_id")
    private String openId;

    private Integer subscribe;

    @Column(name = "subscribe_time")
    private Date subscribeTime;

    @Column(name = "subscribe_scene")
    private String subscribeScene;

    @Column(name = "nick_name")
    private byte[] nickName;

    private Integer sex;

    private String country;

    private String province;

    private String city;

    private String language;

    @Column(name = "head_img_url")
    private String headImgUrl;

    @Column(name = "union_id")
    private String unionId;

    private String remark;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "qr_scene")
    private String qrScene;

    @Column(name = "qr_scene_str")
    private String qrSceneStr;

    private Date createTime;

    @Transient
    private String nickNameStr;

    public String getNickNameStr() {
        if(this.getNickName() != null){
            this.nickNameStr = new String(this.getNickName(), StandardCharsets.UTF_8);
        }
        return nickNameStr;
    }

    @Transient
    private String tagStr;

    @Transient
    private List<Integer> tagIds;
}
