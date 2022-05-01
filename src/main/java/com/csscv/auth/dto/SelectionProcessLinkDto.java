package com.csscv.auth.dto;

import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.SelectionProcess;

/**
 * This is owned by candidate to store score and rank of candidate for particular selectionProcess
 * @author GP
 *
 */
public class SelectionProcessLinkDto {
	
	private Long Id;
	
	private SelectionProcess sprocess;
	private Long score;
	private Integer rank;
	
	Candidate candidate;
	
	private String spName;
	
	private long totalCandidateCount;
	
	
	
	
	public long getTotalCandidateCount() {
		return totalCandidateCount;
	}
	public void setTotalCandidateCount(long totalCandidateCount) {
		this.totalCandidateCount = totalCandidateCount;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public SelectionProcess getSprocess() {
		return sprocess;
	}
	public void setSprocess(SelectionProcess sprocess) {
		this.sprocess = sprocess;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	
	
}
