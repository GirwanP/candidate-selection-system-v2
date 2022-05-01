package com.csscv.auth.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class QualificationLink {
	@Id
	@GeneratedValue
	Long Id;
	@OneToOne
	private QualificationEntry qulification;
	private Long points;
	@OneToOne
	private SelectionProcess selectionProcess;
	
	
	
	public SelectionProcess getSelectionProcess() {
		return selectionProcess;
	}
	public void setSelectionProcess(SelectionProcess selectionProcess) {
		this.selectionProcess = selectionProcess;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public QualificationEntry getQulification() {
		return qulification;
	}
	public void setQulification(QualificationEntry qulification) {
		this.qulification = qulification;
	}
	public Long getPoints() {
		return points;
	}
	public void setPoints(Long points) {
		this.points = points;
	}
	
	
	
}
