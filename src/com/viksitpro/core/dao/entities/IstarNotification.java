package com.viksitpro.core.dao.entities;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * IstarNotification entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="istar_notification"
    ,schema="public"
)

public class IstarNotification  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer senderId;
     private Integer receiverId;
     private String title;
     private String details;
     private String status;
     private String action;
     private String type;
     private Boolean isEventBased;
     private Timestamp createdAt;
     private Integer taskId;
     private String groupCode;
     private Boolean readByAdmin;	


    // Constructors

    /** default constructor */
    public IstarNotification() {
    }

    
    /** full constructor */
    public IstarNotification(Integer senderId, Integer receiverId, String title, String details, String status, String action, String type, Boolean isEventBased, Timestamp createdAt, Integer taskId, String groupCode) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.title = title;
        this.details = details;
        this.status = status;
        this.action = action;
        this.type = type;
        this.isEventBased = isEventBased;
        this.createdAt = createdAt;
        this.taskId = taskId;
        this.groupCode = groupCode;
    }

   
    // Property accessors
    @GenericGenerator(name="generator", strategy="increment")@Id @GeneratedValue(generator="generator")
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="sender_id")

    public Integer getSenderId() {
        return this.senderId;
    }
    
    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }
    
    @Column(name="receiver_id")

    public Integer getReceiverId() {
        return this.receiverId;
    }
    
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
    
    @Column(name="title")

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Column(name="details")

    public String getDetails() {
        return this.details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }
    
    @Column(name="status")

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="action")

    public String getAction() {
        return this.action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    @Column(name="type")

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="is_event_based")

    public Boolean getIsEventBased() {
        return this.isEventBased;
    }
    
    public void setIsEventBased(Boolean isEventBased) {
        this.isEventBased = isEventBased;
    }
    
    @Column(name="created_at", length=29)

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    @Column(name="task_id")

    public Integer getTaskId() {
        return this.taskId;
    }
    
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    
    @Column(name="group_code")

    public String getGroupCode() {
        return this.groupCode;
    }
    
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    
    @Column(name="read_by_admin")
	public Boolean getReadByAdmin() {
		return readByAdmin;
	}


	public void setReadByAdmin(Boolean readByAdmin) {
		this.readByAdmin = readByAdmin;
	}
   








}