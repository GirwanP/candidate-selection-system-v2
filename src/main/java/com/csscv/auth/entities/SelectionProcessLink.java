package com.csscv.auth.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
/**
 * This is owned by candidate to store score and rank of candidate for particular selectionProcess
 * @author GP
 *
 */
@Entity
public class SelectionProcessLink {
	@Id
	@GeneratedValue
	Long id;
	
	@ManyToOne
	private SelectionProcess sprocess;
	private Long score;
	private Integer rank;
	
	@ManyToOne(fetch=FetchType.EAGER)
	Candidate candidate;
	
	
	
	
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
