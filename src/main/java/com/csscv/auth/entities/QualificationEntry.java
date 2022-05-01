package com.csscv.auth.entities;

import java.time.Period;

import javax.persistence.*;

@Entity
public class QualificationEntry {

	@Id
	@GeneratedValue
	Long Id;
//	@Column(unique=true)
	String name;
	@ManyToOne(fetch=FetchType.EAGER)
	QualificationType type;
	String fieldOfStudy;
	Period length;
	
	private boolean valid;
	
	
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public QualificationType getType() {
		return type;
	}
	public void setType(QualificationType type) {
		this.type = type;
	}
	
	public Period getLength() {
		return length;
	}
	public void setLength(Period length) {
		this.length = length;
	}
	
	
	
}
