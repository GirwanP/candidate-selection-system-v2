package com.csscv.auth.dto;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
public class SelectionProcessDto {

	
	Long id;
	
	private Long creatorUserId;
	
	private LocalDate openDate;
	private LocalDate closingDate;
	
	private String name;
	private String uniqueId;
	
	private boolean disabled;
	private boolean deleted;
	
	
	private long totalCandidateCount;

	
//	@OneToMany(fetch=FetchType.EAGER)
//	private Set<QualificationLink> evaluationdata;

	public long getTotalCandidateCount() {
		return totalCandidateCount;
	}



	public void setTotalCandidateCount(long totalCandidateCount) {
		this.totalCandidateCount = totalCandidateCount;
	}



	public boolean isDisabled() {
		return disabled;
	}

	

	public Long getCreatorUserId() {
		return creatorUserId;
	}



	public void setCreatorUserId(Long creatorUserId) {
		this.creatorUserId = creatorUserId;
	}



	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

//	public Set<QualificationLink> getEvaluationdata() {
//		return evaluationdata;
//	}
//
//	public void setEvaluationdata(Set<QualificationLink> evaluationdata) {
//		this.evaluationdata = evaluationdata;
//	}
	
	
	
	
	
}
