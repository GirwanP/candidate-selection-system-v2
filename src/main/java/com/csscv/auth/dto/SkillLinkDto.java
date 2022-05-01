package com.csscv.auth.dto;

import javax.persistence.OneToOne;

import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.Skill;

public class SkillLinkDto {
	private Long Id;
	private String skillName;
	private Long skillId;
	
	private Long points;
//	private SelectionProcess selectionProcess;
	
	private String sPname;
	private String sPuniqueId;
	private Long spid;
	
	
	
	
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public Long getSkillId() {
		return skillId;
	}
	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}
	public Long getSpid() {
		return spid;
	}
	public void setSpid(Long spid) {
		this.spid = spid;
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
	public String getsPname() {
		return sPname;
	}
	public void setsPname(String sPname) {
		this.sPname = sPname;
	}
	public String getsPuniqueId() {
		return sPuniqueId;
	}
	public void setsPuniqueId(String sPuniqueId) {
		this.sPuniqueId = sPuniqueId;
	}
	
	
	
	
}
