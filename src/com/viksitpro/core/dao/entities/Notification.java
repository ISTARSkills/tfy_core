package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Notification entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "notification", schema = "public")

public class Notification implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUserBySenderId;
	private IstarUser istarUserByReceiverId;
	private BatchGroup batchGroup;
	private String notificationMessage;
	private String type;
	private Timestamp createdAt;
	private Boolean sent;

	// Constructors

	/** default constructor */
	public Notification() {
	}

	/** full constructor */
	public Notification(IstarUser istarUserBySenderId, IstarUser istarUserByReceiverId, BatchGroup batchGroup,
			String notificationMessage, String type, Timestamp createdAt, Boolean sent) {
		this.istarUserBySenderId = istarUserBySenderId;
		this.istarUserByReceiverId = istarUserByReceiverId;
		this.batchGroup = batchGroup;
		this.notificationMessage = notificationMessage;
		this.type = type;
		this.createdAt = createdAt;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id")

	public IstarUser getIstarUserBySenderId() {
		return this.istarUserBySenderId;
	}

	public void setIstarUserBySenderId(IstarUser istarUserBySenderId) {
		this.istarUserBySenderId = istarUserBySenderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_id")

	public IstarUser getIstarUserByReceiverId() {
		return this.istarUserByReceiverId;
	}

	public void setIstarUserByReceiverId(IstarUser istarUserByReceiverId) {
		this.istarUserByReceiverId = istarUserByReceiverId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "batch_group_id")

	public BatchGroup getBatchGroup() {
		return this.batchGroup;
	}

	public void setBatchGroup(BatchGroup batchGroup) {
		this.batchGroup = batchGroup;
	}

	@Column(name = "notification_message")

	public String getNotificationMessage() {
		return this.notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	@Column(name = "type")

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "sent")

	public Boolean getSent() {
		return this.sent;
	}

	public void setSent(Boolean sent) {
		this.sent = sent;
	}

}