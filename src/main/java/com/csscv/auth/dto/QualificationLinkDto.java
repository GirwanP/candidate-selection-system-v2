package com.csscv.auth.dto;

public class QualificationLinkDto {
	private Long Id;
	private String qulificationName;
	private Long qualificationId;
	
	private Long points;
//	private SelectionProcess selectionProcess;
	
	private String sPname;
	private String sPuniqueId;
	private Long spid;
	
	
	
	
	
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
	public String getQulificationName() {
		return qulificationName;
	}
	public void setQulificationName(String qulificationName) {
		this.qulificationName = qulificationName;
	}
	public Long getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(Long qualificationId) {
		this.qualificationId = qualificationId;
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
