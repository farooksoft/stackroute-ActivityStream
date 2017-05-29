package com.stackroute.activitystreamcircles.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GenerationType;

@Entity
@Table(name="circles")
public class Circle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long Id;
	private String circlename;
	private String circledescr;
	private String status;
	private Long createddate;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCirclename() {
		return circlename;
	}

	public void setCirclename(String circlename) {
		this.circlename = circlename;
	}

	public String getCircledescr() {
		return circledescr;
	}

	public void setCircledescr(String circledescr) {
		this.circledescr = circledescr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Long createddate) {
		this.createddate = createddate;
	}
	
	@Override
	public String toString() {
		return "Circle {id: " + Id + 
				", circlename: " + circlename +
				", circledescr: " + circledescr +
				", status: " + status +
				", createddate: " + createddate 
				+ "}";
	}

}
