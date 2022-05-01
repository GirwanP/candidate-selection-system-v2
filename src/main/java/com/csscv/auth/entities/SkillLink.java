package com.csscv.auth.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class SkillLink {
	@Id
	@GeneratedValue
	Long Id;
	@OneToOne
	private Skill skill;
	private Long points;
	@OneToOne
	private SelectionProcess selectionProcess;
	
	
	
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
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
	
	public Long getPoints() {
		return points;
	}
	public void setPoints(Long points) {
		this.points = points;
	}
	
	
	
}
