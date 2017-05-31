package com.stackroute.activitystreammessages.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GenerationType;

@Entity
@Table(name="messages")
public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long Id;
	
	@Column(name="msgtext")
	private String msgtext;
	@Column(name="senderid")
	private Long senderId;
	@Column(name="circleid")
	private Long circleId;
	@Column(name="msgtype")
	private String msgtype;
	@Column(name="createddate")
	private Long createddate;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getMsgtext() {
		return msgtext;
	}

	public void setMsgtext(String msgtext) {
		this.msgtext = msgtext;
	}
	
	public Long getSenderid() {
		return senderId;
	}

	public void setSenderid(Long senderId) {
		this.senderId = senderId;
	}
	
	public Long getCircleid() {
		return circleId;
	}

	public void setCircleid(Long circleId) {
		this.circleId = circleId;
	}
	
	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	public Long getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Long createddate) {
		this.createddate = createddate;
	}
	
	@Override
	public String toString() {
		return "Message {id: " + Id + 
				", msgtext: " + msgtext +
				", senderId: " + senderId +
				", circleid: " + circleId +
				", msgtype: " + msgtype + 
				", createddate: " + createddate 
				+ "}";
	}
}
