package com.capgemini.test.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="CONSUMPTIONRECORD")
public class ConsumptionRecord {
	@Id 
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	private Float peak=0.0f;
	private Float offpeak=0.0f;
	private Float xnight=0.0f;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date date;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getPeak() {
		return peak;
	}
	public void setPeak(Float peak) {
		this.peak = peak;
	}
	public Float getOffpeak() {
		return offpeak;
	}
	public void setOffpeak(Float offpeak) {
		this.offpeak = offpeak;
	}
	public Float getXnight() {
		return xnight;
	}
	public void setXnight(Float xnight) {
		this.xnight = xnight;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
