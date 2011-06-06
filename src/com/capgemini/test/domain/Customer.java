package com.capgemini.test.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(nullable = false)
	private String userid;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	private String zipcode;
	@Enumerated(EnumType.STRING)
	private Provider water;
	@Enumerated(EnumType.STRING)
	private Provider heating;
	@Enumerated(EnumType.STRING)
	private Provider cook;
	private Integer nrOfPersons;
	@Enumerated(EnumType.STRING)
	private BuildType buildType;
	private Integer roomOne;
	private Integer roomTwo;
	private Integer roomThree;
	private Integer roomFour;
	private Integer roomFive;
	@Temporal(TemporalType.DATE)
	private Date startDateElec;
	@Temporal(TemporalType.DATE)
	private Date endDateElec;
	private Float usageElec;
	private Integer percentage;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "HISTORICAL_CONSUMPTIONS", joinColumns = @JoinColumn(name = "cust_id"), inverseJoinColumns = @JoinColumn(name = "cons_id"))
	private List<ConsumptionRecord> historicalConsumptions;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "EXPECTED_CONSUMPTIONS", joinColumns = @JoinColumn(name = "cust_id"), inverseJoinColumns = @JoinColumn(name = "cons_id"))
	private List<ConsumptionRecord> expectedConsumptions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Provider getWater() {
		return water;
	}

	public void setWater(Provider water) {
		this.water = water;
	}

	public Provider getHeating() {
		return heating;
	}

	public void setHeating(Provider heating) {
		this.heating = heating;
	}

	public Provider getCook() {
		return cook;
	}

	public void setCook(Provider cook) {
		this.cook = cook;
	}

	public Integer getNrOfPersons() {
		return nrOfPersons;
	}

	public void setNrOfPersons(Integer nrOfPersons) {
		this.nrOfPersons = nrOfPersons;
	}

	public BuildType getBuildType() {
		return buildType;
	}

	public void setBuildType(BuildType buildType) {
		this.buildType = buildType;
	}

	public Integer getRoomOne() {
		return roomOne;
	}

	public void setRoomOne(Integer roomOne) {
		this.roomOne = roomOne;
	}

	public Integer getRoomTwo() {
		return roomTwo;
	}

	public void setRoomTwo(Integer roomTwo) {
		this.roomTwo = roomTwo;
	}

	public Integer getRoomThree() {
		return roomThree;
	}

	public void setRoomThree(Integer roomThree) {
		this.roomThree = roomThree;
	}

	public Integer getRoomFour() {
		return roomFour;
	}

	public void setRoomFour(Integer roomFour) {
		this.roomFour = roomFour;
	}

	public Integer getRoomFive() {
		return roomFive;
	}

	public void setRoomFive(Integer roomFive) {
		this.roomFive = roomFive;
	}

	public Date getStartDateElec() {
		return startDateElec;
	}

	public void setStartDateElec(Date startDateElec) {
		this.startDateElec = startDateElec;
	}

	public Date getEndDateElec() {
		return endDateElec;
	}

	public void setEndDateElec(Date endDateElec) {
		this.endDateElec = endDateElec;
	}

	public Float getUsageElec() {
		return usageElec;
	}

	public void setUsageElec(Float usageElec) {
		this.usageElec = usageElec;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}

	public List<ConsumptionRecord> getHistoricalConsumptions() {
		return historicalConsumptions;
	}

	public void setHistoricalConsumptions(
			List<ConsumptionRecord> historicalConsumptions) {
		this.historicalConsumptions = historicalConsumptions;
	}

	public List<ConsumptionRecord> getExpectedConsumptions() {
		return expectedConsumptions;
	}

	public void setExpectedConsumptions(
			List<ConsumptionRecord> expectedConsumptions) {
		this.expectedConsumptions = expectedConsumptions;
	}

}
