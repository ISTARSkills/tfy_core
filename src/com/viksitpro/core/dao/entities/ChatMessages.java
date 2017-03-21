package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ChatMessages entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "chat_messages", schema = "public")

public class ChatMessages implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private String message;
	private Timestamp createdAt;
	private Integer chatGroupId;
	private Integer receiverId;
	private String type;
	private Boolean sent;

	// Constructors

	/** default constructor */
	public ChatMessages() {
	}

	/** full constructor */
	public ChatMessages(Integer userId, String message, Timestamp createdAt, Integer chatGroupId, Integer receiverId,
			String type, Boolean sent) {
		this.userId = userId;
		this.message = message;
		this.createdAt = createdAt;
		this.chatGroupId = chatGroupId;
		this.receiverId = receiverId;
		this.type = type;
		this.sent = sent;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "user_id")

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "message")

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "chat_group_id")

	public Integer getChatGroupId() {
		return this.chatGroupId;
	}

	public void setChatGroupId(Integer chatGroupId) {
		this.chatGroupId = chatGroupId;
	}

	@Column(name = "receiver_id")

	public Integer getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	@Column(name = "type")

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "sent")

	public Boolean getSent() {
		return this.sent;
	}

	public void setSent(Boolean sent) {
		this.sent = sent;
	}

}