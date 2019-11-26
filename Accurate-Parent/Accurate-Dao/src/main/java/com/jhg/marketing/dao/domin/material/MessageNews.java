package com.jhg.marketing.dao.domin.material;

import com.alibaba.fastjson.annotation.JSONField;
import com.jhg.marketing.common.Explain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 图文消息
 */
@Data
@Table(name="message_news")
public class MessageNews implements Serializable {

	@Explain(value = "id", isEdit = false)
	@Id
	@Column(name = "id", insertable = false,updatable = false)
	@JSONField(serialize = false)
	private Integer id;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 封面的media_id
	 */
	@Column(name = "thumb_media_id")
	private String thumb_media_id;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 摘要
	 */
	private String digest;

	/**
	 * 内容是否显示封面,0不显示，1显示
	 */
	@Column(name = "show_cover_pic")
	private Integer show_cover_pic;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 阅读原文的地址
	 */
	@Column(name = "content_source_url")
	private String content_source_url;

	/**
	 * 是否打开评论,0不打开，1打开
	 */
	@Column(name = "need_open_comment")
	private Integer need_open_comment;

	/**
	 * 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
	 */
	@Column(name = "only_fans_can_comment")
	private Integer only_fans_can_comment;

	/**
	 * 微信返回的图文页地址
	 */
//	@JSONField(serialize = false)
//	private String url;

	/**
	 * 封面的本地路径
	 */
	@JSONField(serialize = false)
	@Column(name = "thumb_url")
	private String thumbUrl;

	@JSONField(serialize = false)
	@Transient
	private Boolean enabled;
}
